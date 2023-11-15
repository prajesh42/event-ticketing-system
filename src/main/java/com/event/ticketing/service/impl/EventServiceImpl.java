package com.event.ticketing.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.ticketing.dto.EventDTO;
import com.event.ticketing.entity.Event;
import com.event.ticketing.mapper.EntityMapper;
import com.event.ticketing.repository.EventRepository;
import com.event.ticketing.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	private EventRepository eventRepository;
	private EntityMapper eventMapper;
	
	@Autowired
	public void setUp(EventRepository eventRepository, EntityMapper eventMapper) {
		this.eventRepository = eventRepository;
		this.eventMapper = eventMapper;
	}
	
	@Override
	public List<EventDTO> getAllEvents() {
		List<Event> events = eventRepository.findAll();
		return events.stream()
				.map(eventMapper::eventToEventDTO)
				.collect(Collectors.toList());
	}

	@Override
	public EventDTO getEventById(Long id) {
		Event event = eventRepository.getReferenceById(id);
		return eventMapper.eventToEventDTO(event);
	}

}
