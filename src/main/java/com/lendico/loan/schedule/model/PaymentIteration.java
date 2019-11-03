package com.lendico.loan.schedule.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class PaymentIteration {

    private final Date date;
    private final double borrowerPaymentAmount;
    private final double interest;
    private final double principal;
    private final double initialOutstandingPrincipal;
    private final double remainingOutstandingPricipal;

    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'").format(date);
    }

    public String getBorrowerPaymentAmount() {
        return formatted(borrowerPaymentAmount);
    }

    public String getInterest() {
        return formatted(interest);
    }

    public String getPrincipal() {
        return formatted(principal);
    }

    public String getInitialOutstandingPrincipal() {
        return formatted(initialOutstandingPrincipal);
    }

    public String getRemainingOutstandingPricipal() {
        return formatted(remainingOutstandingPricipal);
    }

    private String formatted(double val) {
        return new DecimalFormat("##.##").format(val);
    }


    private PaymentIteration(Date date, double annuity, double interest, double principal, double initialOutstanding, double remainingOutstandingPricipal) {
        this.date = date;
        this.borrowerPaymentAmount = annuity;
        this.interest = interest;
        this.principal = principal;
        this.initialOutstandingPrincipal = initialOutstanding;
        this.remainingOutstandingPricipal = remainingOutstandingPricipal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, borrowerPaymentAmount, interest, principal, initialOutstandingPrincipal, remainingOutstandingPricipal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentIteration that = (PaymentIteration) o;
        return Double.compare(that.borrowerPaymentAmount, borrowerPaymentAmount) == 0 &&
                Double.compare(that.interest, interest) == 0 &&
                Double.compare(that.principal, principal) == 0 &&
                Double.compare(that.initialOutstandingPrincipal, initialOutstandingPrincipal) == 0 &&
                Double.compare(that.remainingOutstandingPricipal, remainingOutstandingPricipal) == 0 &&
                date.equals(that.date);
    }

    @Override
    public String toString() {
        return "model.PaymentIteration{" +
                "date=" + date +
                ", annuity=" + borrowerPaymentAmount +
                ", interest=" + interest +
                ", principal=" + principal +
                ", initialOutstanding=" + initialOutstandingPrincipal +
                ", remainingOutstandingPricipal=" + remainingOutstandingPricipal +
                '}';
    }

    public static class IterationBuilder {

        private Date date;
        private double annuity;
        private double interest;
        private double initialOutstanding;
        private double remainingOutstanding;
        private double principal;

        public IterationBuilder() {
        }

        public IterationBuilder setRemainingOutstanding(double remainingOutstanding) {
            this.remainingOutstanding = remainingOutstanding;
            return this;
        }

        public IterationBuilder setInitialOutstanding(double initialOutstanding) {
            this.initialOutstanding = initialOutstanding;
            return this;
        }

        public IterationBuilder setInterest(double interest) {
            this.interest = interest;
            return this;
        }

        public IterationBuilder setAnnuity(double annuity) {
            this.annuity = annuity;
            return this;
        }

        public IterationBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        public IterationBuilder setPrincipal(double principal) {
            this.principal = principal;
            return this;
        }

        public PaymentIteration build() {
            return new PaymentIteration(date, annuity, interest, principal, initialOutstanding, remainingOutstanding);
        }

    }
}


