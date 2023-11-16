package com.event.ticketing.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.event.ticketing.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	List<Ticket> findByUserId(Long userId);
	Ticket getByUserIdAndEventId(Long userId, Long eventId);
}
