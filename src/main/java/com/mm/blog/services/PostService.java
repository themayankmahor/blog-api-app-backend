package com.mm.blog.services;

import java.util.List;

import com.mm.blog.entity.Post;
import com.mm.blog.entity.User;
import com.mm.blog.payloads.PostDto;
import com.mm.blog.payloads.PostResponse;
import com.mm.blog.payloads.UserDto;

public interface PostService {

	//create
	PostDto createPost(PostDto postDto, int userId, int categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, int postId);
	
	//delete
	void deletePost(int postId);
	
	//get all post
	PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);
	
	//get single post
	PostDto getPostById(int postId);
	
	//get all posts by category
	List<PostDto> getPostsByCategory(int category);
	
	//get all posts by user
	List<PostDto> getPostsByUser(int userId);
	
	//search post
	List<PostDto> searchPost(String keyword);
	
}
