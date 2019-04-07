package com.plangenerator.init;

import com.plangenerator.entity.RepaymentDO;
import org.springframework.stereotype.Component;
import com.plangenerator.dataAccessObject.RepaymentDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.lang.Math.pow;


//@Component

public class DataInit {

    private RepaymentDAO repaymentDAO;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private Calendar calendar;
    private double longAmount;
    private double debitInterest;
    private int duration;
    private Date startDate;
    private double initialOutstandingPrincipal;
    private double annuity;
    private boolean firstInitial;
    private Date date;


    public DataInit(RepaymentDAO repaymentDAO, double longAmount, double debitInterest, int duration, Date startDate) {
        this.repaymentDAO = repaymentDAO;
        this.longAmount = longAmount;
        this.debitInterest = debitInterest;
        this.duration = duration;
        this.startDate = startDate;
    }

        public void run(){
        dropTable(repaymentDAO);
        annuity = calculateAnnuity();
        date = startDate;
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        initialOutstandingPrincipal = 0;

        firstInitial = true;
        for (int month = 0; month < duration; month++){
            tableEntry();
        }
    }

    private void tableEntry() {
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

    private double calculateAnnuity() {
        double annuity = (longAmount * debitInterest/12/100)/(1 - pow((1 + debitInterest/12/100), -1 * duration));
        return Math.round(annuity * 100.0) / 100.0;
    }

    private void dropTable(RepaymentDAO repaymentDAO) {
        repaymentDAO.deleteAll();
    }


    public Date nextMonth (RepaymentDO repaymentDO){
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
      //  calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        repaymentDAO.save(repaymentDO);
        return calendar.getTime();
    }
}
