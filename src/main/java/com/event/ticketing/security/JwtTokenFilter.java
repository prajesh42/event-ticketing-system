package com.event.ticketing.security;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    
    private JwtTokenService jwtTokenService;
    private UserDetailsService userDetailsService;

    @Autowired
    public void setUpFilter(JwtTokenService jwtTokenService,UserDetailsService userDetailsService) {
        this.jwtTokenService=jwtTokenService;
        this.userDetailsService=userDetailsService;	
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractToken(request);
        
        try {
            if (token != null) {
            	String username = jwtTokenService.extractUsername(token);
            	
            	if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
            		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            	   
            		if(jwtTokenService.validateToken(token, userDetails)) {
            			UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            			SecurityContextHolder.getContext().setAuthentication(authToken);
            		}
            	}
            }
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return header;
    }
}
