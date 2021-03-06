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

import com.qa.tdl.dto.ItemDto;
import com.qa.tdl.persistance.domain.Item;
import com.qa.tdl.persistance.domain.ToDoList;
import com.qa.tdl.persistance.repo.ItemRepo;


@SpringBootTest
class ItemServiceUnitTest {
	
	@Autowired
	private ItemService service;
	
	@MockBean
	private ItemRepo repo;
	
	@Autowired
	private ModelMapper mapper;
	
	private ItemDto mapToDTO(Item item) {
		return this.mapper.map(item, ItemDto.class);
	}
	
	private final Item testItem1 = new Item(1L, "foo", false);
	private final Item testItem2 = new Item(2L, "bar", false);
	private final Item testItem3 = new Item(3L, "apples", false);
	private final Item testItem4 = new Item(4L, "dusting", false);
	
	private final List<Item> listOfItems = List.of(testItem1, testItem2, testItem3, testItem4);
	
	@Test
	void createTest() throws Exception {
		Long listId = 1L;
		testItem1.setToDoList(new ToDoList(listId));
		ItemDto newDto = this.mapToDTO(testItem1);
		
		when(this.repo.save(testItem1)).thenReturn(testItem1);
		
		assertEquals( newDto , (this.service.create(newDto, listId)) );
		
		verify(this.repo, atLeastOnce()).save(testItem1);
	}
	
	@Test
	void readAllTest() throws Exception {
		List<ItemDto> newDtoList = listOfItems.stream().map(this::mapToDTO).collect(Collectors.toList());
		
		when(this.repo.findAll()).thenReturn(listOfItems);
		assertEquals( newDtoList , (this.service.readAll()) );
		
		verify(this.repo, atLeastOnce()).findAll();
	}
	
	@Test
	void readByIdTest() throws Exception {
		Long id = 2L;
		ItemDto newDto = this.mapToDTO(testItem2);
		
		when(this.repo.findById(id)).thenReturn(Optional.of(testItem2));
		assertEquals( newDto , (this.service.readById(id)) );
		
		verify(this.repo, atLeastOnce()).findById(id);
	}
	
	@Test
	void updateTest() throws Exception {
		Long id = 3L;
		ItemDto newDto = this.mapToDTO(testItem3);
		
		when(this.repo.findById(id)).thenReturn(Optional.of(testItem3));
		when(this.repo.save(testItem3)).thenReturn(testItem3);
		assertEquals( newDto , (this.service.update(newDto, id)) );
		
		verify(this.repo, atLeastOnce()).findById(id);
		verify(this.repo, atLeastOnce()).save(testItem3);
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
