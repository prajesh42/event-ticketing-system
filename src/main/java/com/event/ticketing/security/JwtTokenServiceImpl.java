package com.event.ticketing.security;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.event.ticketing.config.JwtConfig;
import com.event.ticketing.request.AuthRequest;
import com.event.ticketing.response.TokenResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;

@Component
public class JwtTokenServiceImpl implements JwtTokenService {

	@Autowired
    private JwtConfig jwtConfig;
	
	@Override
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims= extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
    	return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	@Override
	public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    @Override
    public TokenResponse generateToken(AuthRequest authRequest) {
    	Map<String, Object> claims=new HashMap<>();
    	claims.put("grant-type",authRequest.getGrantType());
    	claims.put("profile-type", authRequest.getProfileType());
    	return createToken(claims, authRequest.getUsername());
    }
    
    private TokenResponse createToken(Map<String, Object> claims, String  username) {
    	Instant now = Instant.now();
        Instant expirationInstant = now.plusMillis(Long.parseLong(jwtConfig.getExpiration()));

        String token = Jwts.builder()
        		.setClaims(claims)
                .setSubject(username)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expirationInstant))
                .signWith(getSignKey())
                .compact();
        return new TokenResponse(token, Date.from(now), Date.from(expirationInstant));
    }
    
    private Key getSignKey() {
    	byte secrets[]=Decoders.BASE64.decode(jwtConfig.getSecret());
        return new SecretKeySpec(secrets, SignatureAlgorithm.HS256.getJcaName());
    }
}
