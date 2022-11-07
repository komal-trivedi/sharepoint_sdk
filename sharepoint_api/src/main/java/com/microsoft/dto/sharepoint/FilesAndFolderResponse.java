package com.microsoft.dto.sharepoint;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FilesAndFolderResponse {
	
	@JsonProperty(value = "@odata.context")
	private String oDataContext;

	private List<FilesAndFolder> value;

}
