package com.example.beacon.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beacon.persistence.Task;
import com.example.beacon.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/create")
	private ResponseEntity<Task> createTask(@RequestBody Task task) {
		Task temporal = taskService.createTask(task);
		try {
			return ResponseEntity.created(new URI("/task"+temporal.getTaskId())).body(temporal);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/list")
	private ResponseEntity<List<Task>> listAllTask() {
		return ResponseEntity.ok(taskService.listAllTask());
	}
	
	@DeleteMapping("/{taskId}")
	private ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
		taskService.deleteTask(taskId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{taskId}")
	private ResponseEntity<Optional<Task>> findTaskByID(@PathVariable Long taskId) { 
		return ResponseEntity.ok(taskService.findTaskByID(taskId));
	}
	
	@PatchMapping("/{taskId}")
	private ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) { 
		task.setTaskId(taskId);
		return ResponseEntity.ok(taskService.updateTask(task));
	}
	
	@PatchMapping("/{taskId}/title")
	private ResponseEntity<Task> updateTitle(@PathVariable Long taskId, @RequestBody String title) {
		return ResponseEntity.ok(taskService.updateTitle(taskId, title));
	}
	
	@PatchMapping("/{taskId}/complete")
	private ResponseEntity<Task> updateComplete(@PathVariable Long taskId, @RequestBody Boolean completed) {
		return ResponseEntity.ok(taskService.updateComplete(taskId, completed));
	}
}
