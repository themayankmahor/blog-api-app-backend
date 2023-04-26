package com.mm.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mm.blog.config.AppConstants;
import com.mm.blog.entity.Role;
import com.mm.blog.entity.User;
import com.mm.blog.exceptions.ResourceNotFoundException;
import com.mm.blog.payloads.UserDto;
import com.mm.blog.repository.RoleRepository;
import com.mm.blog.repository.UserRepository;
import com.mm.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepository.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = userRepository.save(user);
		UserDto userToDto = userToDto(updatedUser);
		
		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = userRepository.findAll();
		
		List<UserDto> userDtos = users.stream().map(user->userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(int userId) {
		
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		userRepository.delete(user);
	}
	
	public User dtoToUser(UserDto userDto)
	{
		
		User user = modelMapper.map(userDto, User.class);
		
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	public UserDto userToDto(User user)
	{
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		
		User user = modelMapper.map(userDto, User.class);
		
		//encoded the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//set roles to new user
		Role role = roleRepository.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		
		User newUser = userRepository.save(user);
		
		return modelMapper.map(newUser, UserDto.class);
	}

}
