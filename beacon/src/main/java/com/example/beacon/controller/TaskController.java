package com.example.beacon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.beacon.controller.api.request.CreateTaskRequest;
import com.example.beacon.controller.api.response.TaskResponse;
import com.example.beacon.persistence.Task;
import com.example.beacon.persistence.Timezone;
import com.example.beacon.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH})
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	
	@PostMapping("/create")
	private ResponseEntity<TaskResponse> createTask(@RequestBody CreateTaskRequest request) {
		TaskResponse response = taskService.createTask(request);
		return ResponseEntity.status(HttpStatus.CREATED).body( response );
	}
	
	@GetMapping("/list")
	private ResponseEntity<List<TaskResponse>> listAllTask() {
		return ResponseEntity.ok(taskService.listAllTask());
	}
	
	@GetMapping("/timezone/{coordinate}")
	private ResponseEntity<Timezone> getTimezone(@PathVariable String coordinate) {
		return ResponseEntity.ok(taskService.getTimeZone(coordinate));
	}
	
	@DeleteMapping("/{taskId}")
	private ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
		taskService.deleteTask(taskId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{taskId}")
	private ResponseEntity<TaskResponse> findTaskByID(@PathVariable Long taskId) { 
		TaskResponse task = taskService.findTaskByID(taskId);
		return ResponseEntity.ok(task);
	}
	
	@PatchMapping("/{taskId}")
	private ResponseEntity<TaskResponse> updateTask(@PathVariable Long taskId, @RequestBody Task task) { 	
		return ResponseEntity.ok(taskService.updateTask(taskId, task));
	}
	
	@PatchMapping("/{taskId}/title")
	private ResponseEntity<TaskResponse> updateTitle(@PathVariable Long taskId, @RequestBody String title) {
		return ResponseEntity.ok(taskService.updateTitle(taskId, title));
	}
	
	@PatchMapping("/{taskId}/complete")
	private ResponseEntity<TaskResponse> updateComplete(@PathVariable Long taskId) {
		return ResponseEntity.ok(taskService.updateComplete(taskId));
	}
}
