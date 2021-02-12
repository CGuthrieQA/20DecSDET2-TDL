package com.qa.tdl.persistance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@NotNull
	@ManyToOne()
	private ToDoList toDoList;

	public Item( @NotNull String name, @NotNull boolean complete) {
		super();
		this.name = name;
		this.complete = complete;
	}

	public Item(Long id, @NotNull String name, @NotNull boolean complete) {
		super();
		this.id = id;
		this.name = name;
		this.complete = complete;
	}
	
}
