package com.event.ticketing.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {

	private String username;
	private String password;
	@JsonProperty("grant_type")
	private String grantType;
	@JsonProperty("profile_type")
	private String profileType;
}
