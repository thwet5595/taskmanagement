package com.oneterrace.taskmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneterrace.taskmanagement.model.Task;
import com.oneterrace.taskmanagement.repository.TaskRepository;
import com.oneterrace.taskmanagement.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	TaskRepository taskRepository;

	@Override
	public List<Task> findAll() {
		return (List<Task>) this.taskRepository.findAll();
	}
}
