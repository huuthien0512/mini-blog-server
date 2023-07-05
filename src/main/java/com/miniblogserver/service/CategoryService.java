package com.miniblogserver.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miniblogserver.dto.CategoryDTO;
import com.miniblogserver.model.Category;
import com.miniblogserver.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<CategoryDTO> getCategories(String keywords) {
		List<Category> categories = null;
		categories = categoryRepository.findAllByName(keywords);
		List<CategoryDTO> categoryDTOs = new ArrayList<>(categories.size());
		categories.forEach(category -> categoryDTOs.add(convertToDTO(category)));
		return categoryDTOs;
	}

	public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
		Category category = convertToEntity(categoryDTO);
		category.setEditTime(new Timestamp(System.currentTimeMillis()));
		return convertToDTO(categoryRepository.save(category));
	}

	public CategoryDTO saveCategory(String name) {
		Category category = new Category();
		category.setName(name);
		category.setCreateTime(new Timestamp(System.currentTimeMillis()));
		category.setEditTime(new Timestamp(System.currentTimeMillis()));
		Category savedCategory = categoryRepository.save(category);
		return convertToDTO(savedCategory);
	}

	public void deleteCategoryByIds(List<Long> ids) {
		categoryRepository.deleteAllById(ids);
	}

	public CategoryDTO convertToDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setCreateTime(category.getCreateTime());
		categoryDTO.setEditTime(category.getEditTime());
		return categoryDTO;
	}

	public Category convertToEntity(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setCreateTime(categoryDTO.getCreateTime());
		category.setEditTime(categoryDTO.getEditTime());
		return category;
	}
}
