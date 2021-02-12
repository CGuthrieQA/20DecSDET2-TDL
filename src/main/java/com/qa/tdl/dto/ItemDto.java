package com.qa.tdl.dto;

import com.qa.tdl.persistance.domain.DummyToDo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDto {
	
	private Long id;
	private String name;
	private boolean complete;
	private DummyToDo toDoList;
	
}
