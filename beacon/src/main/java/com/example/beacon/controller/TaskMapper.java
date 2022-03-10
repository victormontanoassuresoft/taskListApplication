package com.example.beacon.controller;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.beacon.controller.api.response.TaskExitDto;
import com.example.beacon.controller.api.request.TaskEntryDto;
import com.example.beacon.persistence.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
	
	public TaskExitDto entityToDto(Task task);
	public List<TaskExitDto> entityToDto(List<Task> tasks);
	public Task dtoToEntity(TaskEntryDto dto);
}
