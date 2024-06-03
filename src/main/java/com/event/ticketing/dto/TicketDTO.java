package com.event.ticketing.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
	private Long id;
	private String ticketCode;
	private String ticketStatus;
	private Integer ticketPrice;
	private Date creationDate;
}