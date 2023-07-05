package com.miniblogserver.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miniblogserver.dto.CategoryDTO;
import com.miniblogserver.service.CategoryService;

import jakarta.annotation.Nonnull;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping()
	public List<CategoryDTO> getCategories(@RequestParam(defaultValue = "") String keywords) {
		return categoryService.getCategories(keywords);
	}

	@PostMapping()
	public CategoryDTO addCategory(@RequestBody @Nonnull String name) {
		return categoryService.saveCategory(name);
	}

	@PutMapping()
	public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) {
		return categoryService.updateCategory(categoryDTO);
	}

	@DeleteMapping()
	public void deleteById(@RequestParam("ids") List<Long> ids) {
		categoryService.deleteCategoryByIds(ids);
	}
}
