package com.event.ticketing.service;

import java.util.List;

import com.event.ticketing.dto.EventDTO;

public interface EventService {
    List<EventDTO> getAllEvents();
    EventDTO getEventById(Long id);
}
