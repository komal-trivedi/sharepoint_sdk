package com.microsoft.dto.onedrive;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DriveFileResponse {
	
	@JsonProperty(value = "@odata.context")
	private String oDataContext;
	
	private List<FilesAndFolder> value;

}
