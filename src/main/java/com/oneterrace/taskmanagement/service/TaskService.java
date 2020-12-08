package com.oneterrace.taskmanagement.service;

import java.util.List;

import com.oneterrace.taskmanagement.model.Task;

public interface TaskService {

	List<Task> findAll();

	List<Task> findTaskByPagination(Integer page, Integer size);

	List<Task> findCompleteTaskByPagination(Integer page, Integer size);

	List<Task> findByCompletedStatusAndDeletedStatus(String completedStatus, String deletedStatus);
}
