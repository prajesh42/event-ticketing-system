package com.event.ticketing.controlleradvice;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

import com.event.ticketing.exception.EventNotFoundException;
import com.event.ticketing.exception.EventDataException;
import com.event.ticketing.exception.EventServiceException;
import com.event.ticketing.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class EventControllerAdvice {

	Logger log = LoggerFactory.getLogger(EventControllerAdvice.class);

	@ExceptionHandler(EventNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleEventNotFoundException(EventNotFoundException ex,
			HttpServletRequest request) {
		return createErrorResponse(ex, request, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EventDataException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleEventRepositoryException(EventDataException ex,
			HttpServletRequest request) {
		return createErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EventServiceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleEventServiceException(EventServiceException ex,
			HttpServletRequest request) {
		return createErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
		return createErrorResponse(ex, request, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request) {
		return createErrorResponse(ex, request, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleUnknownException(Exception ex, HttpServletRequest request) {
		return createErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<ErrorResponse> createErrorResponse(Exception ex, HttpServletRequest request,
			HttpStatus status) {
		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), getRequestPath(request), status.value(),
				status.getReasonPhrase(), ex.getMessage());
		log.error(ex.getLocalizedMessage(), ex);
		return new ResponseEntity<>(errorResponse, status);
	}

	private String getRequestPath(HttpServletRequest request) {
		return new ServletWebRequest(request).getRequest().getRequestURI();
	}
}
