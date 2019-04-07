package com.plangenerator.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PLANEGENERATOR")
public class RepaymentDO {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "borrowerPaymentAmount", nullable = false)
    private double annuity;

    @Column(name = "principal", nullable = false)
    private double principal;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public void setInitialOutstandingPrincipal(double initialOutstandingPrincipal) {
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

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

    public RepaymentDO(LocalDate date, double annuity, double principal, double interest, double initialOutstandingPrincipal,
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


