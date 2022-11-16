package com.microsoft.dto.sharepoint;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilesAndFolder {
	
	private Date createdDateTime;
	
	private String eTag;
	
	private String id;
	
	private Date lastModifiedDateTime;
	
	private String name;
	
	private String webUrl;
	
	private String cTag;
	
	private Long size;
	
	private UserDetails createdBy;
	
	private UserDetails lastModifiedBy;
	
	private ParentReference parentReference;
	
	private FileSystemInfo fileSystemInfo;
	
	private Folder folder;
	
	private Shared shared;
	
}
