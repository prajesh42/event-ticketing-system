package com.event.ticketing.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.ticketing.dto.TicketDTO;
import com.event.ticketing.entity.Event;
import com.event.ticketing.entity.Ticket;
import com.event.ticketing.entity.User;
import com.event.ticketing.mapper.EntityMapper;
import com.event.ticketing.repository.EventRepository;
import com.event.ticketing.repository.TicketRepository;
import com.event.ticketing.repository.UserRepository;
import com.event.ticketing.service.TicketService;
import com.event.ticketing.utils.EventUtils;

@Service
public class TicketServiceImpl implements TicketService {

	private TicketRepository ticketRepository;
	private EventRepository eventRepository;
	private UserRepository userRepository;
	private EntityMapper ticketMapper;

	@Autowired
	public void setUp(TicketRepository ticketRepository, EventRepository eventRepository, UserRepository userRepository,
			EntityMapper ticketMapper) {
		this.ticketRepository = ticketRepository;
		this.eventRepository = eventRepository;
		this.userRepository = userRepository;
		this.ticketMapper = ticketMapper;
	}

	@Override
	public TicketDTO bookTicket(Long userId, Long eventId) {
		Ticket ticket = new Ticket();
		User user = userRepository.getReferenceById(userId); // TO-DO handle db exceptions
		Event event = eventRepository.getReferenceById(eventId);// TO-DO handle db exceptions
		Integer noOfSeats = event.getNoOfSeats();
		if (noOfSeats <= 0) {
			// throw exception with message saying no tickets available
			ticket.setTicketCode("N/A");
			ticket.setTicketStatus("No seats available");	
			return ticketMapper.ticketToTicketDTO(ticket);
		}
		event.setNoOfSeats(noOfSeats - 1);
		ticket.setTicketCode(EventUtils.generateTicketCode());
		ticket.setTicketPrice(1500);
		ticket.setTicketStatus("Booked");
		ticket.setCreationDate(new Date());
		ticket.setUser(user);
		ticket.setEvent(event);
		Ticket savedTicket = ticketRepository.save(ticket);// TO-DO handle db exceptions
		return ticketMapper.ticketToTicketDTO(savedTicket);
	}

	@Override
	public List<TicketDTO> getUserTickets(Long userId) {
		List<Ticket> tickets = ticketRepository.findAll();
		return tickets.stream().map(ticketMapper::ticketToTicketDTO).collect(Collectors.toList());
	}

}
