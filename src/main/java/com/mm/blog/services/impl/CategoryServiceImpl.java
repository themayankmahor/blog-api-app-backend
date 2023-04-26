package com.mm.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.blog.entity.Category;
import com.mm.blog.exceptions.ResourceNotFoundException;
import com.mm.blog.payloads.CategoryDto;
import com.mm.blog.repository.CategoryRepository;
import com.mm.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		//
		Category category = modelMapper.map(categoryDto, Category.class);
		Category addedCategory = categoryRepository.save(category);

		return modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatedCategory = categoryRepository.save(category);

		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		categoryRepository.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {

		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> categoryDto = categories.stream().map(cat -> categoryToDto(cat)).collect(Collectors.toList());

		return categoryDto;
	}

	public Category dtoToCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);

		return category;
	}

	public CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);

		return categoryDto;
	}

}
