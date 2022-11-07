package com.microsoft.dto.onedrive;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DriveIdResponse {
	
	@JsonProperty(value="@odata.context")
	private String oDataContext;
	
	private Date createdDateTime;	
	
	private String description;
	
	private String id;
	
	private Date lastModifiedDateTime;
	
	private String name;
	
	private String webUrl;
	
	private String driveType;
	
	private UserDetails createdBy;
	
	private UserDetails lastModifiedBy;
	
	private UserDetails owner;
	
	private Quota quota;
	

}
