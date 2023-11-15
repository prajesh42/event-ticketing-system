package com.event.ticketing.service;

import java.util.List;
import com.event.ticketing.entity.Event;

public interface EventService {
    List<Event> getAllEvents();
    Event getEventById(Long id);
}
