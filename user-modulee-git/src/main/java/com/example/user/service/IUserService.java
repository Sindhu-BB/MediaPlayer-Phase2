package com.example.user.service;

import java.util.List;
import java.util.Optional;

import com.example.user.dto.UserDto;
import com.example.user.entity.User;
import com.example.user.exception.DuplicateRecordException;
import com.example.user.exception.RecordNotFoundException;


public interface IUserService {
	
public List<UserDto> addUser(UserDto userDto) throws DuplicateRecordException, RecordNotFoundException;	
	
	public List<UserDto> updateUser(UserDto userDto)throws DuplicateRecordException, RecordNotFoundException;
	
	public List<UserDto> deleteUser(Integer userLoginId) throws  RecordNotFoundException;
	
	public List<UserDto> viewAllUsers() throws RecordNotFoundException;
	
	
	public UserDto getUserByUsername(String username) throws RecordNotFoundException;
	
	public List<UserDto> save(User bean) throws RecordNotFoundException;

	Optional<UserDto> findById(Integer id) throws RecordNotFoundException;



	
}
