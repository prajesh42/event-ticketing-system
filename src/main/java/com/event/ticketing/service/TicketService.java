package com.event.ticketing.service;

import java.util.List;
import com.event.ticketing.entity.Ticket;

public interface TicketService {
    Ticket bookTicket(Long userId, Long eventId);
    List<Ticket> getUserTickets(Long userId);
}
