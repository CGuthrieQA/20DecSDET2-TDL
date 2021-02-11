package com.qa.tdl.persistance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private boolean complete;
	
	@ManyToOne
	@JoinColumn(name = "todolist_id", nullable = false)
	private ToDoList toDoList = null;

	public Item( @NotNull String name, @NotNull boolean complete, ToDoList toDoList) {
		super();
		this.name = name;
		this.complete = complete;
		this.toDoList = toDoList;
	}

	public Item(Long id, @NotNull String name, @NotNull boolean complete, ToDoList toDoList) {
		super();
		this.id = id;
		this.name = name;
		this.complete = complete;
		this.toDoList = toDoList;
	}
	
}
