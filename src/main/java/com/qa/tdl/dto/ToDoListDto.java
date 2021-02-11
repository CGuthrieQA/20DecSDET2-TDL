package com.qa.tdl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToDoListDto {
	
	private Long id;
	private String name;
	private boolean complete;
	//private List<ItemDto> items = new ArrayList<>();
	
}
