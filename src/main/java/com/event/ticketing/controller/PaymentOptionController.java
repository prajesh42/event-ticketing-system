package com.event.ticketing.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.event.ticketing.entity.PaymentOption;
import com.event.ticketing.service.PaymentOptionService;

@RestController
@RequestMapping("/payment-options")
public class PaymentOptionController {

    @Autowired
    private PaymentOptionService paymentOptionService;

    @GetMapping
    public List<PaymentOption> getAllPaymentOptions() {
        return paymentOptionService.getAllPaymentOptions();
    }
}
