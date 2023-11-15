package com.event.ticketing.service;

import org.springframework.security.core.userdetails.UserDetails;
import com.event.ticketing.entity.User;

public interface UserService {
    User save(User user);
    UserDetails loadUserByUsername(String username);
}
