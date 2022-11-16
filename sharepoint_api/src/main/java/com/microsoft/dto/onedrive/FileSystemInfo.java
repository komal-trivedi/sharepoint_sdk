package com.microsoft.dto.onedrive;

import java.util.Date;

import lombok.Data;

@Data
public class FileSystemInfo {
	
	private Date createdDateTime;
	
	private Date lastModifiedDateTime;

}
