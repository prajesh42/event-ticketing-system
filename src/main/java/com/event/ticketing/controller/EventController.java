package com.event.ticketing.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.event.ticketing.exception.EventDataException;
import com.event.ticketing.exception.EventNotFoundException;
import com.event.ticketing.exception.EventServiceException;
import com.event.ticketing.request.EventRequest;
import com.event.ticketing.service.EventService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllEvents() throws EventDataException, EventServiceException {
		return ResponseEntity.ok(eventService.getAllEvents());
	}

	@GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEventById(@PathVariable Long id) throws EventNotFoundException, EventDataException, EventServiceException {
		return ResponseEntity.ok(eventService.getEventById(id));
	}

	@PostMapping(path="/create",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createEvent(@RequestBody EventRequest eventRequest) throws EventDataException, EventServiceException {
		Long eventId=eventService.creatEvent(eventRequest);
		String uri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/events/{id}")
				.buildAndExpand(eventId)
				.toUriString();
		return ResponseEntity.created(URI.create(uri)).build();
	}

	@PutMapping(path="/{id}",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody EventRequest eventRequest) throws EventNotFoundException, EventDataException, EventServiceException {
		return ResponseEntity.ok(eventService.updateEvent(id,eventRequest));
	}

	@DeleteMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteEvent(@PathVariable Long id) throws EventNotFoundException, EventDataException, EventServiceException{
		eventService.deleteEventById(id);
		return ResponseEntity.ok("Deleted");	
	}
}
