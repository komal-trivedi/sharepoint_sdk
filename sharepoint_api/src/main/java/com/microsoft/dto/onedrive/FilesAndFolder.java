package com.microsoft.dto.onedrive;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilesAndFolder {
	
	@JsonProperty(value = "@microsoft.graph.downloadUrl")
	private String downloadUrl;
	
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
	
	private File file;
	
	private Shared shared;
	
	
	
	
}
