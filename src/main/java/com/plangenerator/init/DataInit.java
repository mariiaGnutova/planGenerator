package com.plangenerator.init;

import com.plangenerator.entity.RepaymentDO;
import com.plangenerator.dataAccessObject.RepaymentDAO;

import java.time.LocalDate;
import static java.lang.Math.pow;

public class DataInit {

    private RepaymentDAO repaymentDAO;
    private double longAmount;
    private double debitInterest;
    private int duration;
    private LocalDate startDate;
    private double initialOutstandingPrincipal;
    private double annuity;
    private boolean firstInitial;
    private LocalDate date;


    public DataInit(RepaymentDAO repaymentDAO, double longAmount, double debitInterest, int duration, LocalDate startDate) {
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
        date = date.plusMonths(1);
    }

    private double calculateAnnuity() {
        double annuity = (longAmount * debitInterest/12/100)/(1 - pow((1 + debitInterest/12/100), -1 * duration));
        return Math.round(annuity * 100.0) / 100.0;
    }

    private void dropTable(RepaymentDAO repaymentDAO) {
        repaymentDAO.deleteAll();
    }

}
