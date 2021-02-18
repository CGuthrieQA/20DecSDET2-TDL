package com.qa.tdl.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.tdl.dto.ItemDto;
import com.qa.tdl.persistance.domain.Item;
import com.qa.tdl.service.ItemService;

@SpringBootTest
@ActiveProfiles("dev")
public class ItemControllerTest {
	
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
}
