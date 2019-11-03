package com.lendico.loan.schedule.service;

import com.lendico.loan.schedule.model.Borrowing;
import com.lendico.loan.schedule.model.PaymentIteration;

import java.util.List;

public interface AmortizationScheduleService {

    List<PaymentIteration> generate(Borrowing borrowing);

}
