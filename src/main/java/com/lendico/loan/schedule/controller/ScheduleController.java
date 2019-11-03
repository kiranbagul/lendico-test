package com.lendico.loan.schedule.controller;

import com.lendico.loan.schedule.model.Borrowing;
import com.lendico.loan.schedule.model.PaymentIteration;
import com.lendico.loan.schedule.service.AmortizationScheduleService;
import com.lendico.loan.schedule.service.AmortizationScheduleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Generate Amortization Schedule API")
public class ScheduleController {

    private Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    private final AmortizationScheduleService amortizationScheduleService;

    public ScheduleController(AmortizationScheduleService amortizationScheduleService) {
        this.amortizationScheduleService = amortizationScheduleService;
    }

    @PostMapping("/generate-plan")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Generate Amortization Schedule")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Plan generated successfully")})
    public ResponseEntity<List<PaymentIteration>> generatePlan(@RequestBody Borrowing borrowing) {
        logger.info("Request for plan generation : {}", borrowing);
        return ok(new AmortizationScheduleServiceImpl().generate(borrowing));
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error in generating plan")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Error in generating plan")})
    public void handleInputError(Exception ex) {
        logger.error("Error in generating plan", ex);
    }
}
