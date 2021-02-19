package com.qa.tdl.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.tdl.dto.ItemDto;
import com.qa.tdl.persistance.domain.Item;
import com.qa.tdl.service.ItemService;

@SpringBootTest
@ActiveProfiles("dev")
class ItemControllerTest {
	
	@Autowired
	private ItemController controller;
	
	@MockBean
	private ItemService service;
	
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
		ItemDto newDto = this.mapToDTO(testItem1);
		Long listId = 1L;
		
		when(this.service.create(newDto, listId)).thenReturn(newDto);
		assertEquals( new ResponseEntity<ItemDto>(newDto, HttpStatus.CREATED) , (this.controller.create(listId, newDto)) );
		
		verify(this.service, atLeastOnce()).create(newDto, listId);
	}
	
	@Test
	void readAllTest() throws Exception {
		List<ItemDto> newDtoList = listOfItems.stream().map(this::mapToDTO).collect(Collectors.toList());
		
		when(this.service.readAll()).thenReturn(newDtoList);
		assertEquals( ResponseEntity.ok(newDtoList) , this.controller.readAll() );
		
		verify(this.service, atLeastOnce()).readAll();
	}
	
	@Test
	void readByIdTest() throws Exception {
		Long id = 2L;
		ItemDto newDto = this.mapToDTO(testItem2);
		
		when(this.service.readById(id)).thenReturn(newDto);
		assertEquals( ResponseEntity.ok(newDto) , this.controller.readById(id) );
		
		verify(this.service, atLeastOnce()).readById(id);
	}
	
	@Test
	void updateTest() throws Exception {
		Long id = 3L;
		ItemDto newDto = this.mapToDTO(testItem3);
		
		when(this.service.update(newDto, id)).thenReturn(newDto);
		assertEquals(new ResponseEntity<ItemDto>(newDto, HttpStatus.ACCEPTED) , this.controller.update(id, newDto) );
		
		verify(this.service, atLeastOnce()).update(newDto, id);
	}
	
	@Test
	void deleteTest() throws Exception {
		Long id = 4L;
		Long badId = -99999999L;
		
		when(this.service.delete(id)).thenReturn(true);
		assertEquals(new ResponseEntity<>(null, HttpStatus.NO_CONTENT) , this.controller.delete(id) );
		assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) , this.controller.delete(badId) );
		
		verify(this.service, atLeastOnce()).delete(id);
	}
	
}
