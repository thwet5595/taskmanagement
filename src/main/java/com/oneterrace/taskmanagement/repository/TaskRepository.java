package com.oneterrace.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.oneterrace.taskmanagement.model.Task;

@Repository
public interface TaskRepository
		extends CrudRepository<Task, Long>, PagingAndSortingRepository<Task, Long>, JpaRepository<Task, Long> {

	Task findById(String id);
}
