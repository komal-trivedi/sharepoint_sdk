package com.microsoft.dto.onedrive;

import lombok.Data;

@Data
public class Quota {
	
	private int deleted;
	
	private Long remaining;
	
	private String state;
	
	private Long total;
	
	private Long used;

}
