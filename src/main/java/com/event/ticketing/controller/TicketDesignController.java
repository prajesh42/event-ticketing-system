package com.event.ticketing.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.event.ticketing.entity.TicketDesign;
import com.event.ticketing.service.TicketDesignService;

@RestController
@RequestMapping("/designs")
public class TicketDesignController {

	@Autowired
	private TicketDesignService ticketDesignService;

	@GetMapping
	public List<TicketDesign> getAllDesigns() {
		return ticketDesignService.getAllDesigns();
	}

	@GetMapping("/{id}")
	public TicketDesign getDesignById(@PathVariable Long id) {
		return ticketDesignService.getDesignById(id);
	}
}
