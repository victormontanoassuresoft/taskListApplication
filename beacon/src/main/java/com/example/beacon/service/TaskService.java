package com.example.beacon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beacon.controller.api.request.CreateTaskRequest;
import com.example.beacon.integration.timezone.TimezoneRepository;
import com.example.beacon.persistence.Task;
import com.example.beacon.persistence.Timezone;
import com.example.beacon.persistence.repository.TaskRepository;
import com.example.beacon.controller.api.response.TaskResponse;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TimezoneRepository timezoneRepository;
	
	public Task createTask (CreateTaskRequest request) {
		Task task = new Task();
		BeanUtils.copyProperties(request, task);
		taskRepository.save( task );				
		return taskRepository.save(task);
	}
	
	public List<Task> listAllTask (){
		return taskRepository.findAll();
	}
	
	public void deleteTask (Long taskId) {
		taskRepository.deleteById(taskId);;
	}
	
	public TaskResponse findTaskByID (Long taskId) {
		Task task = taskRepository.findById(taskId).get();
		TaskResponse taskExit = new TaskResponse();
		BeanUtils.copyProperties(task, taskExit);
		return taskExit;
	}
	
	public Task updateTask (Long taskId, Task task) {
		task.setTaskId(taskId);
		return taskRepository.save(task);
	}
	
	public Task updateComplete (Long taskId) {
		Optional<Task> optionalTaskToUpdate = taskRepository.findById(taskId);
		Task taskToUpdate = optionalTaskToUpdate.get();
		taskToUpdate.setCompleted(!taskToUpdate.getCompleted());
		return taskRepository.save(taskToUpdate);
	}
	
	public Task updateTitle (Long taskId, String title) {
		Optional<Task> optionalTaskToUpdate = taskRepository.findById(taskId);
		Task taskToUpdate = optionalTaskToUpdate.get();
		taskToUpdate.setTitle(title);
		return taskRepository.save(taskToUpdate);
	}
	
	public Timezone getTimeZone (String coordinate){
		String[] coordinates = coordinate.split(",");
		return timezoneRepository.getTimezone(coordinates[0], coordinates[1]);
	}

}
