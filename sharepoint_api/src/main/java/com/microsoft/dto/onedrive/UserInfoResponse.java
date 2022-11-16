package com.microsoft.dto.onedrive;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserInfoResponse {

	@JsonProperty(value = "@odata.context")
	private  String oDataContext;
	
	private List<String> businessPhones;
	
	private String displayName;
	
	private String givenName;
	
	private String jobTitle;
	
	private String mail;
	
	private String mobilePhone;
	
	private String officeLocation;
	
	private String preferredLanguage;
	
	private String surname;
	
	private String userPrincipalName;
	
	private String id;
}
