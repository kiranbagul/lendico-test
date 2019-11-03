package com.lendico.loan.schedule.service;

import com.lendico.loan.schedule.model.Borrowing;
import com.lendico.loan.schedule.model.PaymentIteration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static java.math.MathContext.DECIMAL32;
import static java.math.RoundingMode.HALF_EVEN;

public class AmortizationScheduleServiceImpl implements AmortizationScheduleService {

    @Override
    public List<PaymentIteration> generate(Borrowing borrowing) {
        BigDecimal principal = new BigDecimal(borrowing.getLoanAmount());
        BigDecimal monthlyInterestRate = new BigDecimal(borrowing.getNominalRate() / 12);
        BigDecimal annuity = monthlyPayment(principal, monthlyInterestRate, borrowing.getDuration());
        Date nextDate = borrowing.getStartDate();

        List<PaymentIteration> iterations = new ArrayList<>();
        for (int month = 0; month < borrowing.getDuration(); month++) {
            BigDecimal interestPaid = scale(principal.multiply(monthlyInterestRate.divide(valueOf(100))));
            BigDecimal principalPaid = scale(annuity.subtract(interestPaid));
            BigDecimal remainingOutstanding = principal.subtract(principalPaid);
            if (remainingOutstanding.compareTo(ZERO) < 0) {
                BigDecimal diff = principalPaid.subtract(principal);
                annuity = annuity.subtract(diff);
                principalPaid = principalPaid.subtract(diff);
                remainingOutstanding = ZERO;
            }
            PaymentIteration iteration = new PaymentIteration.IterationBuilder()
                    .setDate(nextDate)
                    .setAnnuity(doubleOf(annuity))
                    .setInterest(doubleOf(interestPaid))
                    .setPrincipal(doubleOf(principalPaid))
                    .setInitialOutstanding(doubleOf(principalPaid.add(remainingOutstanding)))
                    .setRemainingOutstanding(doubleOf(remainingOutstanding))
                    .build();
            principal = remainingOutstanding;
            nextDate = getNextMonthDate(nextDate);
            iterations.add(iteration);
        }
        return iterations;
    }

    private double doubleOf(BigDecimal dec) {
        return scale(dec).doubleValue();
    }

    private BigDecimal scale(BigDecimal dec) {
        return dec.setScale(2, HALF_EVEN);
    }

    private Date getNextMonthDate(Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    private BigDecimal monthlyPayment(BigDecimal principal, BigDecimal interestRate, double years) {
        interestRate = interestRate.divide(valueOf(100));
        return principal.multiply(
                interestRate.divide(new BigDecimal(1 - 1 / Math.pow(1 + interestRate.doubleValue(), years))
                        , DECIMAL32));
    }

}