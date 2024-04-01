package com.event.ticketing.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long id;
    private String username;
    private String email;
    private String roles;
    private Boolean isLocked;
    private Boolean isActive;
    private Date creationDate;
    private Date updatedOn;
    private List<TicketDTO> tickets;
}
