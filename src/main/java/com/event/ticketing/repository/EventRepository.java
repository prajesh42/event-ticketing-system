package com.event.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.event.ticketing.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
