package com.event.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event.ticketing.entity.PaymentOption;

public interface PaymentOptionRepository extends JpaRepository<PaymentOption, Long> {
}
