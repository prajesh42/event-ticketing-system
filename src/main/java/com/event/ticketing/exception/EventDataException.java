package com.event.ticketing.exception;

public class EventDataException extends Exception {

	private static final long serialVersionUID = 1L;

	public EventDataException(String message) {
		super(message);
	}

	public EventDataException(String message, Throwable cause) {
		super(message, cause);
	}
}
