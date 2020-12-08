package com.oneterrace.taskmanagement.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.oneterrace.taskmanagement.model.Task;
import com.oneterrace.taskmanagement.repository.TaskRepository;
import com.oneterrace.taskmanagement.service.TaskService;

@RequestMapping("terrace")
@RestController
public class TaskController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskService taskService;

	@PostMapping("/task/create")
	public Task createTask(@RequestBody Task task) {

		LOGGER.info("Inside Create Task...");
		// Gson gson = new Gson();
		// Task newTask = gson.fromJson(taskData, Task.class);
		Task newTask = new Task();
		newTask.setTitle(task.getTitle());
		newTask.setCompletedStatus("Progress");
		newTask.setExpectedDate(task.getExpectedDate());
		newTask.setPriority(task.getPriority());
		newTask.setCompletedStatus("No");
		newTask.setDeletedStatus("No");

		Task _task = taskRepository.save(newTask);

		return _task;
	}

	@GetMapping("/tasks/pagination/{page}/{size}")
	public ResponseEntity<?> getTaskForPagination(@PathVariable("page") Integer page,
			@PathVariable("size") Integer size) {
		LOGGER.info("Inside getTaskForPagination");

		List<Task> tasks = taskService.findTaskByPagination(page, size);
		LOGGER.info("Tasks..." + tasks.size());
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/tasks/complete/pagination/{page}/{size}")
	public ResponseEntity<?> getCompleteTaskForPagination(@PathVariable("page") Integer page,
			@PathVariable("size") Integer size) {
		LOGGER.info("Inside getTaskForPagination");

		List<Task> tasks = taskService.findCompleteTaskByPagination(page, size);
		LOGGER.info("Complete Tasks..." + tasks.size());
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}


	@GetMapping("/tasks/count")
	public Map<String, Long> getTasksCount() {
		List<Task> tasks = taskService.findByCompletedStatusAndDeletedStatus("No", "No");

		Map<String, Long> resultMap = new HashMap<>();
		resultMap.put("taskCount", (long) tasks.size());
		return resultMap;
	}
	
	@GetMapping("/tasks/complete/count")
	public Map<String, Long> getCompleteTasksCount() {
		List<Task> tasks = taskService.findByCompletedStatusAndDeletedStatus("Yes", "No");

		Map<String, Long> resultMap = new HashMap<>();
		resultMap.put("taskCount", (long) tasks.size());
		return resultMap;
	}

	@PutMapping("/tasks/approve/selected")
	public ResponseEntity<Map<String, List<String>>> approveSelectedTasks(
			@RequestParam(value = "ids", required = true) String ids) {

		SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
		dtf.setTimeZone(TimeZone.getTimeZone("Asia/Rangoon"));
		String approvedDate = dtf.format(new Date());

		Map<String, List<String>> resMap = new HashMap<>();
		List<String> taskSucceses = new ArrayList<>();
		List<String> taskFails = new ArrayList<>();
		for (String id : ids.split(",")) {
			Optional<Task> existTasks = taskRepository.findById(Long.parseLong(id.trim()));
			if (existTasks.isPresent()) {
				Task existTask = existTasks.get();
				existTask.setCompletedDate(approvedDate);
				existTask.setCompletedStatus("Yes");
				Task _task = taskRepository.save(existTask);
				if (_task != null) {
					taskSucceses.add(String.valueOf(_task.getId()));
				} else {
					taskFails.add(id.trim());
				}
			}
		}

		resMap.put("success", taskSucceses);
		resMap.put("error", taskFails);
		return new ResponseEntity<>(resMap, HttpStatus.OK);
	}

	@PutMapping("/tasks/delete/selected")
	public ResponseEntity<Map<String, List<String>>> deleteSelectedTasks(
			@RequestParam(value = "ids", required = true) String ids) {
		Map<String, List<String>> resMap = new HashMap<>();
		List<String> taskSucceses = new ArrayList<>();
		List<String> taskFails = new ArrayList<>();
		for (String id : ids.split(",")) {
			Optional<Task> existTasks = taskRepository.findById(Long.parseLong(id.trim()));
			if (existTasks.isPresent()) {
				Task existTask = existTasks.get();
				existTask.setDeletedStatus("Yes");
				Task _task = taskRepository.save(existTask);
				if (_task != null) {
					taskSucceses.add(String.valueOf(_task.getId()));
				} else {
					taskFails.add(id.trim());
				}
			}
		}

		resMap.put("success", taskSucceses);
		resMap.put("error", taskFails);
		return new ResponseEntity<>(resMap, HttpStatus.OK);
	}
	
	@PutMapping("/task/update")
	public ResponseEntity<?> updateTask(@RequestParam(value = "task", required = true) String taskData) {
		Gson gson = new Gson();
		Task task = gson.fromJson(taskData, Task.class);
		try {
			Optional<Task> exitTasks = this.taskRepository.findById(task.getId());
			if(exitTasks.isPresent()){
				Task existTask = exitTasks.get();
				existTask.setTitle(task.getTitle());
				existTask.setExpectedDate(task.getExpectedDate());
				existTask.setPriority(task.getPriority());
				existTask.setCompletedStatus(task.getCompletedStatus());
				existTask.setCompletedDate(task.getCompletedDate());
				this.taskRepository.save(existTask);
			}
		} catch (Exception e) {
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
