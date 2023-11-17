package com.event.ticketing.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private Long id;
    private String name;
    private String description;
	private String location;
	private LocalDate eventStartDate;
	private LocalDate eventEndDate;
	private Integer noOfSeats;
}