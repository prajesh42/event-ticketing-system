package com.event.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.event.ticketing.entity.TicketDesign;

public interface TicketDesignRepository extends JpaRepository<TicketDesign, Long> {
}
