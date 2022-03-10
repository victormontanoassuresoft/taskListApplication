package com.example.beacon.persistence;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data

@Entity(name="task")
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
	
	private Boolean completed;
    
    private String title;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;
    
    @CreationTimestamp
    private Date createdDate;
    
}
