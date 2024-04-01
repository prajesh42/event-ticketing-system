package com.event.ticketing.service.impl;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.event.ticketing.dto.UserDTO;
import com.event.ticketing.exception.EventDataException;
import com.event.ticketing.exception.EventNotFoundException;
import com.event.ticketing.exception.EventServiceException;
import com.event.ticketing.mapper.EntityMapper;
import com.event.ticketing.repository.UserRepository;
import com.event.ticketing.request.UserRequest;
import com.event.ticketing.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private EntityMapper userMapper;
	private PasswordEncoder encoder;

	@Autowired
	public void setUp(UserRepository userRepository, EntityMapper eventMapper, PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.userMapper = eventMapper;
		this.encoder = encoder;
	}

	@Override
	public Long createUser(UserRequest userRequest) throws EventDataException, EventServiceException {
		try {
			userRequest.setPassword(encoder.encode(userRequest.getPassword()));
			return userRepository.save(userMapper.userRequestToUser(userRequest)).getId();
		}catch(DataAccessException | DataException e) {
			throw new EventDataException("Error occurred while creating user", e);
		}catch(Exception e) {
			throw new EventServiceException("Something went wrong", e);
		}
	}

	@Override
	public UserDTO getUserByUsername(String username) throws EventServiceException, EventNotFoundException, EventDataException {
		try {
			return userMapper.userToUserDTO(userRepository.findByUsername(username).get());
		}catch(EntityNotFoundException e) {
			throw new EventNotFoundException("Data Not Found", e);
		}catch(DataAccessException | DataException e) {
			throw new EventDataException("Error occurred while retrieving data", e);
		}catch(Exception e) {
			throw new EventServiceException("Something went wrong", e);
		}	
	}

}
