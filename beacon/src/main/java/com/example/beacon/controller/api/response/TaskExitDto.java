package com.example.beacon.controller.api.response;

import java.util.Date;

import lombok.Data;

@Data
public class TaskExitDto {
    
    private String title;
    private Boolean completed;
    private Date dueDate;
    private Date createdDate;
}