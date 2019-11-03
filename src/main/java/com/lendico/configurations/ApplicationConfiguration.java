package com.lendico.configurations;

import com.lendico.loan.schedule.service.AmortizationScheduleService;
import com.lendico.loan.schedule.service.AmortizationScheduleServiceImpl;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AmortizationScheduleService amortizationScheduleService() {
        return new AmortizationScheduleServiceImpl();
    }

}