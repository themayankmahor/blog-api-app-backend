package com.mm.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.blog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
