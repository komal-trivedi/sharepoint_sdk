package com.microsoft.dto.sharepoint;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Site {
	
	private String id;
	
	private Date createdDateTime;
	
	private Date lastModifiedDateTime;
	
	private String name;
	
	private String webUrl;
	
	private String displayName;
	
	private SiteCollection siteCollection;

}
