package com.lendico.loan.schedule.service;

import com.lendico.loan.schedule.model.Borrowing;
import com.lendico.loan.schedule.model.PaymentIteration;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AmortizationScheduleServiceImplTest {

    @Test
    public void generate() throws ParseException {
        AmortizationScheduleServiceImpl scheduleService = new AmortizationScheduleServiceImpl();
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2018");
        Borrowing borrowing = new Borrowing(5000, 5, 24, date);
        List<PaymentIteration> iterations = scheduleService.generate(borrowing);
        PaymentIteration paymentIteration1 = iterations.get(0);
        PaymentIteration iteration1 = new PaymentIteration.IterationBuilder()
                .setDate(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2018"))
                .setAnnuity(219.36)
                .setInterest(20.83)
                .setPrincipal(198.53)
                .setInitialOutstanding(5000.00)
                .setRemainingOutstanding(4801.47)
                .build();
        Assert.assertEquals(iteration1, paymentIteration1);

        PaymentIteration iteration2 = new PaymentIteration.IterationBuilder()
                .setDate(new SimpleDateFormat("dd.MM.yyyy").parse("01.02.2018"))
                .setAnnuity(219.36)
                .setInterest(20.01)
                .setPrincipal(199.35)
                .setInitialOutstanding(4801.47)
                .setRemainingOutstanding(4602.12)
                .build();
        Assert.assertEquals(iteration2, iterations.get(1));

        PaymentIteration lastIteration = new PaymentIteration.IterationBuilder()
                .setDate(new SimpleDateFormat("dd.MM.yyyy").parse("01.12.2019"))
                .setAnnuity(219.28)
                .setInterest(0.91)
                .setPrincipal(218.37)
                .setInitialOutstanding(218.37)
                .setRemainingOutstanding(0)
                .build();
        Assert.assertEquals(lastIteration, iterations.get(iterations.size() - 1));
    }

}