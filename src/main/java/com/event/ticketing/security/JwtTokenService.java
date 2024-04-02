package com.event.ticketing.security;

import org.springframework.security.core.userdetails.UserDetails;

import com.event.ticketing.request.AuthRequest;
import com.event.ticketing.response.TokenResponse;

public interface JwtTokenService {
	public String extractUsername(String token);
	public Boolean validateToken(String token, UserDetails userDetails);
	public TokenResponse generateToken(AuthRequest authRequest);
	
}
