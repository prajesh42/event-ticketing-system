package com.event.ticketing.service;

import java.util.List;
import com.event.ticketing.entity.PaymentOption;

public interface PaymentOptionService {
    List<PaymentOption> getAllPaymentOptions();
}
