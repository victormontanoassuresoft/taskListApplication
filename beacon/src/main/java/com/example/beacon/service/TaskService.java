package com.example.beacon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.beacon.persistence.Task;
import com.example.beacon.persistence.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Task createTask (Task task) {
		return taskRepository.save(task);
	}
	
	public List<Task> listAllTask (){
		return taskRepository.findAll();
	}
	
	public void deleteTask (Long taskId) {
		taskRepository.deleteById(taskId);;
	}
	
	public Optional<Task> findTaskByID (Long taskId) {
		return taskRepository.findById(taskId);
	}
	
	public Task updateTask (Task task) {
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

}
