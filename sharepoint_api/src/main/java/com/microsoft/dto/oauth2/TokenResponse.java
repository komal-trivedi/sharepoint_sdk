package com.microsoft.dto.oauth2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponse {
	
	@JsonProperty("token_type")
	private String tokenType;
	
	@JsonProperty("scope")
	private String scope;
	
	@JsonProperty("expires_in")
	private Long expiresIn;
	
	@JsonProperty("ext_expires_in")
	private Long extExpiresIn;
	
	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("refresh_token")
	private String refreshToken;
	
}
