package com.oneterrace.taskmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Override
	public List<Task> findTaskByPagination(Integer page, Integer size) {
		Task filterBy = new Task();
		filterBy.setCompletedStatus("No");
		filterBy.setDeletedStatus("No");

		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.EXACT).withIgnoreCase();

		Example<Task> example = Example.of(filterBy, matcher);
		Pageable firstPageWithTwoElements = PageRequest.of(page, size, Sort.by("expectedDate").ascending());

		return this.taskRepository.findAll(example, firstPageWithTwoElements).getContent();
	}

	@Override
	public List<Task> findByCompletedStatusAndDeletedStatus(String completedStatus, String deletedStatus) {
		Task filterBy = new Task();
		filterBy.setCompletedStatus(completedStatus);
		filterBy.setDeletedStatus(deletedStatus);

		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.EXACT).withIgnoreCase();

		Example<Task> example = Example.of(filterBy, matcher);

		return this.taskRepository.findAll(example);
	}

	@Override
	public List<Task> findCompleteTaskByPagination(Integer page, Integer size) {
		Task filterBy = new Task();
		filterBy.setCompletedStatus("Yes");
		filterBy.setDeletedStatus("No");

		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.EXACT).withIgnoreCase();

		Example<Task> example = Example.of(filterBy, matcher);
		Pageable firstPageWithTwoElements = PageRequest.of(page, size, Sort.by("completedDate").ascending());

		return this.taskRepository.findAll(example, firstPageWithTwoElements).getContent();
	}
}
