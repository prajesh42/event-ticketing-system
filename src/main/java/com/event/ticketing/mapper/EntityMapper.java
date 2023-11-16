package com.event.ticketing.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.event.ticketing.dto.EventDTO;
import com.event.ticketing.dto.PaymentOptionDTO;
import com.event.ticketing.dto.TicketDTO;
import com.event.ticketing.dto.TicketDesignDTO;
import com.event.ticketing.dto.UserDTO;
import com.event.ticketing.entity.Event;
import com.event.ticketing.entity.PaymentOption;
import com.event.ticketing.entity.Ticket;
import com.event.ticketing.entity.TicketDesign;
import com.event.ticketing.entity.User;

@Component
@Mapper(componentModel = "spring")
public interface EntityMapper {
    EventDTO eventToEventDTO(Event event);
    TicketDTO ticketToTicketDTO(Ticket ticket);
    UserDTO userToUserDTO(User user);
    PaymentOptionDTO paymentOptionToPaymentOptionDTO(PaymentOption paymentOption);
    TicketDesignDTO ticketDesignToTicketDesignDTO(TicketDesign ticketDesign);
}