package com.event.ticketing.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ticketCode;
	private String ticketStatus;
	private Integer ticketPrice;
	private Date creationDate;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
