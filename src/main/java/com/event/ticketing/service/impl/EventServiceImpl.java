package com.event.ticketing.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.event.ticketing.dto.EventDTO;
import com.event.ticketing.entity.Event;
import com.event.ticketing.exception.EventDataException;
import com.event.ticketing.exception.EventNotFoundException;
import com.event.ticketing.exception.EventServiceException;
import com.event.ticketing.mapper.EntityMapper;
import com.event.ticketing.repository.EventRepository;
import com.event.ticketing.request.EventRequest;
import com.event.ticketing.service.EventService;
import com.event.ticketing.utils.EventUtils;

import jakarta.persistence.EntityNotFoundException;

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
	public List<EventDTO> getAllEvents() throws EventDataException, EventServiceException {
		try {
			return eventRepository.findAll().stream()
					.map(eventMapper::eventToEventDTO)
					.collect(Collectors.toList());
		}catch(DataAccessException | DataException e) {
			throw new EventDataException("Error occurred while retrieving data", e);
		}catch(Exception e) {
			throw new EventServiceException("Something went wrong", e);
		}
	}

	@Override
	public EventDTO getEventById(Long id) throws EventNotFoundException, EventDataException, EventServiceException {
		try {
			return eventMapper.eventToEventDTO(eventRepository.getReferenceById(id));
		}catch(EntityNotFoundException e) {
			throw new EventNotFoundException("Data Not Found", e);
		}catch(DataAccessException | DataException e) {
			throw new EventDataException("Error occurred while retrieving data", e);
		}catch(Exception e) {
			throw new EventServiceException("Something went wrong", e);
		}
	}

	@Override
	public Long creatEvent(EventRequest eventRequest) throws EventDataException, EventServiceException {
		try {
			return eventRepository.save(eventMapper.eventRequestToEvent(eventRequest)).getId();
		}catch(DataAccessException | DataException e) {
			throw new EventDataException("Error occurred while creating event", e);
		}catch(Exception e) {
			throw new EventServiceException("Something went wrong", e);
		}
	}

	@Override
	public EventDTO updateEvent(Long id, EventRequest eventRequest) throws EventNotFoundException, EventDataException, EventServiceException {
		try {
			Event existingData = eventRepository.getReferenceById(id);
			Event dataToUpdate = eventMapper.eventRequestToEvent(eventRequest);
			BeanUtils.copyProperties(dataToUpdate, existingData, EventUtils.getNullPropertyNames(dataToUpdate));
			Event updatedEvent = eventRepository.save(existingData);
			return eventMapper.eventToEventDTO(updatedEvent);
		}catch(EntityNotFoundException e) {
			throw new EventNotFoundException("Data Not Found", e);
		}catch(DataAccessException | DataException e) {
			throw new EventDataException("Error occurred while updating data", e);
		}catch(Exception e) {
			throw new EventServiceException("Something went wrong", e);
		}
	}

	@Override
	public void deleteEventById(Long id) throws EventNotFoundException, EventDataException, EventServiceException {
		try {
			eventRepository.deleteById(id);
		}catch(EntityNotFoundException e) {
			throw new EventNotFoundException("Data Not Found", e);
		}catch(DataAccessException | DataException e) {
			throw new EventDataException("Error occurred while deleting data", e);
		}catch(Exception e) {
			throw new EventServiceException("Something went wrong", e);
		}
	}

}
