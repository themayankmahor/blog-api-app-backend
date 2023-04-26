package com.mm.blog.services;

import com.mm.blog.payloads.CommentDto;

public interface CommentService {

	 CommentDto createComment(CommentDto commentDto, int postId);
	 
	 void deleteComment(int commentId);
	
}
