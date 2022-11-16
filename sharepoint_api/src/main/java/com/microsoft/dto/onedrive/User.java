package com.microsoft.dto.onedrive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	private String id;
	
	private String displayName;
	
	private String email;

}
