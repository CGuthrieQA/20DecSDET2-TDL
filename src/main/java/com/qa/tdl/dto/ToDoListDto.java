package com.qa.tdl.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoListDto {
	
	private Long id;
	private String name;
	private boolean complete;
	private List<ItemDto> items = new ArrayList<>();
	
}
