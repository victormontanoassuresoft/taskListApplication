package com.example.beacon.persistence;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="task")
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
	
	private Boolean completed;
    
    private String title;
    
    private Date dueDate;
    
    private Date createdDate;

	public Task(Long taskId, Boolean completed, String title, Date dueDate, Date createdDate) {
		this.taskId = taskId;
		this.completed = completed;
		this.title = title;
		this.dueDate = dueDate;
		this.createdDate = createdDate;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    
}
