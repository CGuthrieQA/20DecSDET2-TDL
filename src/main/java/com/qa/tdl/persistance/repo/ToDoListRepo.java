package com.qa.tdl.persistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.tdl.persistance.domain.ToDoList;

@Repository
public interface ToDoListRepo extends JpaRepository<ToDoList, Long>{

}
