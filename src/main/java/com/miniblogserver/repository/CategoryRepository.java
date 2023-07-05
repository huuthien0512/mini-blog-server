package com.miniblogserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniblogserver.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	@Query(value = "SELECT c FROM category c WHERE (?1 = '' OR c.name LIKE %?1%)")
	List<Category> findAllByName(String keywords);
}
