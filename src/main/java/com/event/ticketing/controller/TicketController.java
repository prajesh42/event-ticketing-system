package com.event.ticketing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.event.ticketing.dto.TicketDTO;
import com.event.ticketing.service.TicketService;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/book")
    public TicketDTO bookTicket(@RequestParam Long userId, @RequestParam Long eventId) {
        return ticketService.bookTicket(userId, eventId);
    }

    @GetMapping("/user/{userId}")
    public List<TicketDTO> getUserTickets(@PathVariable Long userId) {
        return ticketService.getUserTickets(userId);
    }
}
