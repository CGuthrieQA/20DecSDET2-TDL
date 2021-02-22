package com.qa.tdl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.tdl.dto.ToDoListDto;
import com.qa.tdl.persistance.domain.ToDoList;
import com.qa.tdl.persistance.repo.ToDoListRepo;

@SpringBootTest
class ToDoListServiceUnitTest {

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
	
	@Test
	void readAllTest() throws Exception {
		List<ToDoListDto> newDtoList = listOfToDoLists.stream().map(this::mapToDTO).collect(Collectors.toList());
		
		when(this.repo.findAll()).thenReturn(listOfToDoLists);
		assertEquals( newDtoList , (this.service.readAll()) );
		
		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void readByIdTest() throws Exception {
		Long id = 2L;
		ToDoListDto newDto = this.mapToDTO(testToDoList2);
		
		when(this.repo.findById(id)).thenReturn(Optional.of(testToDoList2));
		assertEquals( newDto , (this.service.readById(id)) );
		
		verify(this.repo, atLeastOnce()).findById(id);
	}
	
	@Test
	void updateTest() throws Exception {
		Long id = 3L;
		ToDoListDto newDto = this.mapToDTO(testToDoList3);
		
		when(this.repo.findById(id)).thenReturn(Optional.of(testToDoList3));
		when(this.repo.save(testToDoList3)).thenReturn(testToDoList3);
		assertEquals( newDto , (this.service.update(newDto, id)) );
		
		verify(this.repo, atLeastOnce()).findById(id);
		verify(this.repo, atLeastOnce()).save(testToDoList3);
	}
	
	@Test
	void deleteTest() throws Exception {
		Long id = 4L;
		Long badId = -99999L;
		
		when(this.repo.existsById(id)).thenReturn(false);
		when(this.repo.existsById(badId)).thenReturn(true);
		assertEquals( true , (this.service.delete(id)) );
		assertEquals( false , (this.service.delete(badId)) );
		
		verify(this.repo, atLeastOnce()).existsById(id);
	}
	
}
