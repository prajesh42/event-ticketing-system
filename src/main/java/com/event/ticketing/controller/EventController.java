package com.event.ticketing.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventService;

	@Operation(summary = "Get all events", description = "Get a list of all events.")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllEvents() throws EventDataException, EventServiceException {
		return ResponseEntity.ok(eventService.getAllEvents());
	}

	@Operation(summary = "Get event by ID", description = "Get event details by specifying the event ID.")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEventById(@Parameter(description = "Event ID", example = "1") @PathVariable Long id)
			throws EventNotFoundException, EventDataException, EventServiceException {
		return ResponseEntity.ok(eventService.getEventById(id));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Operation(summary = "Create an event", description = "Create a new event with the provided details.")
	@ApiResponse(responseCode = "201", description = "Event created successfully", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseEntity.class)) })
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createEvent(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Event details", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EventRequest.class))) @RequestBody EventRequest eventRequest)
			throws EventDataException, EventServiceException {
		Long eventId = eventService.creatEvent(eventRequest);
		String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/events/{id}").buildAndExpand(eventId)
				.toUriString();
		return ResponseEntity.created(URI.create(uri)).build();
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Operation(summary = "Update an event", description = "Update an existing event with the provided details.")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEvent(@Parameter(description = "Event ID", example = "1") @PathVariable Long id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated event details", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EventRequest.class))) @RequestBody EventRequest eventRequest)
			throws EventNotFoundException, EventDataException, EventServiceException {
		return ResponseEntity.ok(eventService.updateEvent(id, eventRequest));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Operation(summary = "Delete an event", description = "Delete an existing event by specifying the event ID.")
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteEvent(@Parameter(description = "Event ID", example = "1") @PathVariable Long id)
			throws EventNotFoundException, EventDataException, EventServiceException {
		eventService.deleteEventById(id);
		return ResponseEntity.ok().build();
	}
}
