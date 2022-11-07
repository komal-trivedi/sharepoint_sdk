package com.microsoft.dto.sharepoint;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SitesResponse {
	
	@JsonProperty(value = "@odata.context")
	private String oDataContext;

	private List<Site> value;
}
