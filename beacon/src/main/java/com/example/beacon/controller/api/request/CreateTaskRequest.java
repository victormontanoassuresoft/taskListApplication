package com.example.beacon.controller.api.request;

import java.util.Date;

import lombok.Data;

@Data
public class CreateTaskRequest {
    
    private String title;
    private Boolean completed;
    private Date dueDate;
}
