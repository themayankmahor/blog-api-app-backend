package com.mm.blog.services;

import java.util.List;

import com.mm.blog.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	public void deleteCategory(int categoryId);
	
	//get
	public CategoryDto getCategory(Integer categoryId);
	
	//get all
	List<CategoryDto> getCategories();
	
}
