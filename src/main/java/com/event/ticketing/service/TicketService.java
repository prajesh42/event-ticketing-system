package com.event.ticketing.service;

import java.util.List;

import com.event.ticketing.dto.TicketDTO;

public interface TicketService {
    TicketDTO bookTicket(Long userId, Long eventId);
    List<TicketDTO> getUserTickets(Long userId);
}
