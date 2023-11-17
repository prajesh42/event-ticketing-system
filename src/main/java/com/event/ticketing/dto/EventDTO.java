package com.event.ticketing.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private String name;
    private String description;
	private String location;
	private LocalDate eventStartDate;
	private LocalDate eventEndDate;
	private Integer noOfSeats;
	private LocalDate creationDate;
	private LocalDate updatedOn;
    private List<TicketDTO> tickets;
}