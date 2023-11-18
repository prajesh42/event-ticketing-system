package com.event.ticketing.service;

import java.util.List;

import com.event.ticketing.dto.EventDTO;
import com.event.ticketing.exception.EventDataException;
import com.event.ticketing.exception.EventNotFoundException;
import com.event.ticketing.exception.EventServiceException;
import com.event.ticketing.request.EventRequest;

public interface EventService {
    List<EventDTO> getAllEvents() throws EventDataException, EventServiceException;
    EventDTO getEventById(Long id) throws EventNotFoundException, EventDataException, EventServiceException;
    Long creatEvent(EventRequest eventRequest) throws EventDataException, EventServiceException;
	EventDTO updateEvent(Long id, EventRequest eventRequest) throws EventNotFoundException, EventDataException, EventServiceException;
	void deleteEventById(Long id) throws EventNotFoundException, EventDataException, EventServiceException;
}
