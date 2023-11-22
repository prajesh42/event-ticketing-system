package com.event.ticketing.dto;

import java.util.Date;
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
	private Date eventStartDate;
	private Date eventEndDate;
	private Integer noOfSeats;
	private Date creationDate;
	private Date updatedOn;
    private List<TicketDTO> tickets;
}