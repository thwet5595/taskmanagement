package com.oneterrace.taskmanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASK")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "expected_date")
	private Date expectedDate;

	@Column(name = "completed_date")
	private Date completedDate;

	@Column(name = "priority")
	private String priority;

	@Column(name = "completed_status")
	private String completedStatus;

	@Column(name = "deleted_status")
	private String deletedStatus;
}
