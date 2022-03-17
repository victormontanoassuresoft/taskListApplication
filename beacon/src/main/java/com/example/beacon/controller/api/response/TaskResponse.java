package com.example.beacon.controller.api.response;

import java.util.Date;

import lombok.Data;

@Data
public class TaskResponse {
    
	private Long taskId;
    private String title;
    private Boolean completed;
    private Date dueDate;
    private Date createdDate;
}
