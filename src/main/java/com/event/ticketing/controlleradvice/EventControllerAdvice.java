package com.event.ticketing.controlleradvice;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

import com.event.ticketing.exception.EventNotFoundException;
import com.event.ticketing.exception.EventDataException;
import com.event.ticketing.exception.EventServiceException;
import com.event.ticketing.response.EventErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class EventControllerAdvice {

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<EventErrorResponse> handleEventNotFoundException(EventNotFoundException ex, HttpServletRequest request) {
        return createErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventDataException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<EventErrorResponse> handleEventRepositoryException(EventDataException ex, HttpServletRequest request) {
        return createErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EventServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<EventErrorResponse> handleEventServiceException(EventServiceException ex, HttpServletRequest request) {
        return createErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<EventErrorResponse> createErrorResponse(Exception ex, HttpServletRequest request, HttpStatus status) {
    	EventErrorResponse errorResponse = new EventErrorResponse(
    			LocalDateTime.now(),
                getRequestPath(request),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    private String getRequestPath(HttpServletRequest request) {
        return new ServletWebRequest(request).getRequest().getRequestURI();
    }
}
