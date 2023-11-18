package com.event.ticketing.exception;

public class EventServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public EventServiceException(String message) {
		super(message);
	}

	public EventServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
