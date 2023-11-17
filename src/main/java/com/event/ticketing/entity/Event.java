package com.event.ticketing.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String location;
	private LocalDate eventStartDate;
	private LocalDate eventEndDate;
	private Integer noOfSeats;

	@Column(name = "created_date", nullable = false, updatable = false)
	private LocalDate creationDate;

	@Column(name = "updated_on", nullable = false, updatable = true)
	private LocalDate updatedOn;

	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private List<Ticket> tickets;

	@PrePersist
	protected void onCreate() {
		creationDate = LocalDate.now();
		updatedOn = LocalDate.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedOn = LocalDate.now();
	}
}
