package com.event.ticketing.service;

import java.util.List;

import com.event.ticketing.dto.EventDTO;
import com.event.ticketing.request.EventRequest;

public interface EventService {
    List<EventDTO> getAllEvents();
    EventDTO getEventById(Long id);
    Long creatEvent(EventRequest eventRequest);
	EventDTO updateEvent(Long id, EventRequest eventRequest);
	void deleteEventById(Long id);
}
