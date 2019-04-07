package com.plangenerator.init;

import com.plangenerator.entity.RepaymentDO;
import org.springframework.stereotype.Component;
import com.plangenerator.dataAccessObject.RepaymentDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


@Component

public class DataInit {

    private RepaymentDAO repaymentDAO;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private Calendar calendar;



    public DataInit(RepaymentDAO repaymentDAO) {
        this.repaymentDAO = repaymentDAO;
    }

        public void run( double longAmount, double debitInterest, int duration, Date startDate){
        dropTable(repaymentDAO);
        double annuity = 219.36; //rate
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = startDate;
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        double initialOutstandingPrincipal = 0;

        boolean firstInitial = true;
        for (int month = 0; month < duration; month++){
            RepaymentDO repaymentDO = new RepaymentDO();
            repaymentDO.setDate(date);
            repaymentDO.setAnnuity(annuity);

            if (firstInitial){
                initialOutstandingPrincipal = longAmount;
                firstInitial = false;
            }

            repaymentDO.setInitialOutstandingPrincipal(initialOutstandingPrincipal);
            double interest = Math.round(initialOutstandingPrincipal*debitInterest/100/12 * 100.0) / 100.0;
            repaymentDO.setInterest(interest);
            double principal = Math.round((annuity - interest) * 100.0) / 100.0;
            repaymentDO.setPrincipal(principal);
            double remainingOutstandingPrincipal = Math.round((initialOutstandingPrincipal - principal) * 100.0) / 100.0;

            //if Initial Outstanding Principal < Annuity
            if (remainingOutstandingPrincipal < 0){
                annuity = annuity + remainingOutstandingPrincipal;
                principal = Math.round((principal + remainingOutstandingPrincipal) * 100.0) / 100.0;
                remainingOutstandingPrincipal = 0;
                repaymentDO.setAnnuity(annuity);
                repaymentDO.setPrincipal(principal);

            }

            repaymentDO.setRemainingOutstandingPrincipal(remainingOutstandingPrincipal);
            repaymentDAO.save(repaymentDO);

            initialOutstandingPrincipal = remainingOutstandingPrincipal;
            date = nextMonth(repaymentDO);

        }
    }

    private void dropTable(RepaymentDAO repaymentDAO) {
        repaymentDAO.deleteAll();
    }


    public Date nextMonth (RepaymentDO repaymentDO){
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        repaymentDAO.save(repaymentDO);
        return calendar.getTime();
    }
}
