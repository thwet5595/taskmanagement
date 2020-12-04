package com.oneterrace.taskmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.oneterrace.taskmanagement.model.Task;
import com.oneterrace.taskmanagement.repository.TaskRepository;

@Component
public class AppStartupRunner implements ApplicationRunner{
	
	@Autowired
	TaskRepository taskRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	}
	
	public void createTask(){
		Task task = new Task();
		//task
	}
}
