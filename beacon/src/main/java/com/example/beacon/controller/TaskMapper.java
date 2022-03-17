package com.example.beacon.controller;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.beacon.controller.api.request.CreateTaskRequest;
import com.example.beacon.controller.api.response.TaskResponse;
import com.example.beacon.persistence.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
	
	public TaskResponse entityToDto(Task task);
	public List<TaskResponse> entityToDto(List<Task> tasks);
	public Task dtoToEntity(CreateTaskRequest dto);
}
