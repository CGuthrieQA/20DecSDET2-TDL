package com.qa.tdl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.tdl.dto.ToDoListDto;
import com.qa.tdl.persistance.domain.ToDoList;
import com.qa.tdl.persistance.repo.ToDoListRepo;

@SpringBootTest
@ActiveProfiles("dev")
public class ToDoListServiceTest {

	@Autowired
	private ToDoListService service;
	
	@MockBean
	private ToDoListRepo repo;
	
	@Autowired
	private ModelMapper mapper;
	
	private ToDoListDto mapToDTO(ToDoList toDoList) {
		return this.mapper.map(toDoList, ToDoListDto.class);
	}
	
	private final ToDoList testToDoList1 = new ToDoList(1L, "Foo", false);
	private final ToDoList testToDoList2 = new ToDoList(2L, "Bar", false);
	private final ToDoList testToDoList3 = new ToDoList(3L, "Shopping", false);
	private final ToDoList testToDoList4 = new ToDoList(4L, "Chores", false);
	
	private final List<ToDoList> listOfToDoLists = List.of(testToDoList1, testToDoList2, testToDoList3, testToDoList4);
	
	@Test
	void createTest() throws Exception {
		
		ToDoListDto newDto = this.mapToDTO(testToDoList1);
		
		when(this.repo.save(testToDoList1)).thenReturn(testToDoList1);
		assertEquals( newDto , (this.service.create(newDto)) );
		
		verify(this.repo, atLeastOnce()).save(testToDoList1);
	}
}
