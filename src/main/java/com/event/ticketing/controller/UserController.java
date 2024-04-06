package com.event.ticketing.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.event.ticketing.exception.EventDataException;
import com.event.ticketing.exception.EventNotFoundException;
import com.event.ticketing.exception.EventServiceException;
import com.event.ticketing.request.UserRequest;
import com.event.ticketing.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Operation(summary = "Get User by username", description = "Get User details by specifying the username")
	@GetMapping(path = "/details/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserByUsername(@Parameter(description = "Username", example = "David412 or david..@...com") @PathVariable String username)
			throws EventNotFoundException, EventDataException, EventServiceException {
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@Operation(summary = "Create a User", description = "Create a new user with the provided details.")
	@ApiResponse(responseCode = "201", description = "User created successfully", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseEntity.class)) })
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User details", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserRequest.class))) @RequestBody UserRequest userRequest)
			throws EventDataException, EventServiceException {
		Long eventId = userService.createUser(userRequest);
		String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/{id}").buildAndExpand(eventId)
				.toUriString();
		return ResponseEntity.created(URI.create(uri)).build();
	}
}
