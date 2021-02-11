package com.qa.tdl.persistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.tdl.persistance.domain.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long>{

}
