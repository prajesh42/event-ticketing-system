package com.event.ticketing.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.event.ticketing.dto.EventDTO;
import com.event.ticketing.entity.Event;

@Component
@Mapper(componentModel = "spring")
public interface EntityMapper {
    EventDTO eventToEventDTO(Event event);
}