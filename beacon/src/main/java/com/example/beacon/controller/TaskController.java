package com.example.beacon.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

import com.example.beacon.controller.api.request.TaskEntryDto;
import com.example.beacon.controller.api.request.TimezoneDto;
import com.example.beacon.controller.api.response.TaskExitDto;
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
	private ResponseEntity<Task> createTask(@RequestBody TaskEntryDto taskDTO) {
		Task task = new Task();
		BeanUtils.copyProperties(taskDTO, task);
		Task newTask = taskService.createTask(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
	}
	
	@GetMapping("/list")
	private ResponseEntity<List<Task>> listAllTask() {
		return ResponseEntity.ok(taskService.listAllTask());
	}
	
	@GetMapping("/timezone")
	private ResponseEntity<Timezone> getTimezone(@RequestBody TimezoneDto timezoneDTO) {
		return ResponseEntity.ok(taskService.getTimeZone(timezoneDTO.getLat(), timezoneDTO.getLng()));
	}
	
	@DeleteMapping("/{taskId}")
	private ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
		taskService.deleteTask(taskId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{taskId}")
	private ResponseEntity<TaskExitDto> findTaskByID(@PathVariable Long taskId) { 
		Task task = taskService.findTaskByID(taskId).get();
		TaskExitDto taskExit = new TaskExitDto();
		BeanUtils.copyProperties(task, taskExit);
		return ResponseEntity.ok(taskExit);
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
	private ResponseEntity<Task> updateComplete(@PathVariable Long taskId) {
		return ResponseEntity.ok(taskService.updateComplete(taskId));
	}
}
