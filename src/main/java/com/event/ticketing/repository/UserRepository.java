package com.event.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.event.ticketing.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
