package com.microsoft.dto.sharepoint;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilesAndFolderResponse {
	
	@JsonProperty(value = "@odata.context")
	private String oDataContext;
	
	@JsonProperty(value = "@odata.nextLink")
	private String oDataNextLink; 

	private List<FilesAndFolder> value;

}
