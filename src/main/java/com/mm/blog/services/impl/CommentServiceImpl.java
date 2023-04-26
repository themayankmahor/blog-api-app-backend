package com.mm.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.blog.entity.Comment;
import com.mm.blog.entity.Post;
import com.mm.blog.exceptions.ResourceNotFoundException;
import com.mm.blog.payloads.CommentDto;
import com.mm.blog.repository.CommentRepository;
import com.mm.blog.repository.PostRepository;
import com.mm.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		
		Comment savedComment = commentRepository.save(comment);
		
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
		
		commentRepository.delete(comment);
		
	}

}
