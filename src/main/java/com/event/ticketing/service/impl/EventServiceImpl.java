package com.event.ticketing.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.ticketing.dto.EventDTO;
import com.event.ticketing.entity.Event;
import com.event.ticketing.mapper.EntityMapper;
import com.event.ticketing.repository.EventRepository;
import com.event.ticketing.request.EventRequest;
import com.event.ticketing.service.EventService;
import com.event.ticketing.utils.EventUtils;

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
		return eventRepository.findAll().stream()
				.map(eventMapper::eventToEventDTO)
				.collect(Collectors.toList());
	}

	@Override
	public EventDTO getEventById(Long id) {
		return eventMapper.eventToEventDTO(eventRepository.getReferenceById(id));
	}

	@Override
	public Long creatEvent(EventRequest eventRequest) {
		return eventRepository.save(eventMapper.eventRequestToEvent(eventRequest)).getId();
	}

	@Override
	public EventDTO updateEvent(Long id, EventRequest eventRequest) {
		Event existingData = eventRepository.getReferenceById(id);
		Event dataToUpdate = eventMapper.eventRequestToEvent(eventRequest);
		BeanUtils.copyProperties(dataToUpdate, existingData, EventUtils.getNullPropertyNames(dataToUpdate));
		Event updatedEvent = eventRepository.save(existingData);
		return eventMapper.eventToEventDTO(updatedEvent);
	}

	@Override
	public void deleteEventById(Long id) {
		eventRepository.deleteById(id);
	}

}
