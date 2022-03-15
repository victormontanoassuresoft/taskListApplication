package com.example.beacon.persistence;

import lombok.Data;

@Data
public class Timezone {
	
	private String zoneName;
	
	private String abbreviation;
    
    private Long gmtOffset;
    
    private String dst;
    
    private String formatted;
}
