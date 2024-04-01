package com.event.ticketing.service;

import com.event.ticketing.dto.UserDTO;
import com.event.ticketing.exception.EventDataException;
import com.event.ticketing.exception.EventNotFoundException;
import com.event.ticketing.exception.EventServiceException;
import com.event.ticketing.request.UserRequest;

public interface UserService {
    Long createUser(UserRequest userRequest) throws EventDataException, EventServiceException;
    UserDTO getUserByUsername(String username) throws EventNotFoundException, EventDataException, EventServiceException;
}
