package com.plangenerator.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PLANEGENERATOR")  // TILGUNGSPLAN
public class RepaymentDO {  // TildungDO

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "borrowerPaymentAmount", nullable = false)
    private double annuity;

    @Column(name = "principal", nullable = false)
    private double principal;  // Tildung

    @Column(name = "interest", nullable = false)
    private double interest;

    @Column( name = "initialOutstandingPrincipal", nullable = false)
    private double initialOutstandingPrincipal;

    @Column(name = "remainingOutstandingPrincipal", nullable = false)
    private double remainingOutstandingPrincipal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }  // getDatum

    public void setDate(Date date) {
        this.date = date;
    }  // setDatum

    public double getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public void setInitialOutstandingPrincipal(double initialOutstandingPrincipal) {
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
    }  // setRestschuld

    public double getInterest() {
        return interest;
    }  // getZinsen

    public void setInterest(double interest) {
        this.interest = interest;
    }  // setZinsen

    public double getPrincipal() {
        return principal;
    }  // getTildung

    public void setPrincipal(double principal) {
        this.principal = principal;
    }  // setTildung

    public double getAnnuity() {
        return annuity;
    }

    public void setAnnuity(double annuity) {
        this.annuity = annuity;
    }

    public double getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }

    public void setRemainingOutstandingPrincipal(double remainingOutstandingPrincipal) {
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

    public RepaymentDO(Date date, double annuity, double principal, double interest, double initialOutstandingPrincipal,
                       double remainingOutstandingPrincipal) {
        this.date = date;
        this.annuity = annuity;
        this.principal = principal;
        this.interest = interest;
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

    public RepaymentDO(){}
}


