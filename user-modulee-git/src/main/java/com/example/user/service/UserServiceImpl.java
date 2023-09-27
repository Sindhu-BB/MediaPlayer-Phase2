package com.example.user.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.user.dao.IUserDao;
import com.example.user.dto.UserDto;
import com.example.user.entity.User;
import com.example.user.exception.DuplicateRecordException;
import com.example.user.exception.RecordNotFoundException;


//@Validated
@Service
public class UserServiceImpl implements IUserService {

private IUserDao userDao;
	
	private ModelMapper mapper;

    public UserServiceImpl(IUserDao userDao, ModelMapper mapper) {
          this.userDao = userDao;
          this.mapper = mapper;
    }
	
	@Override
	public List<UserDto> addUser(UserDto userDto) throws DuplicateRecordException,RecordNotFoundException{
		User user = mapToEntity(userDto);
		User existingUser = userDao.findByUserName(user.getUserName());
	if(existingUser != null) {
		throw new DuplicateRecordException("User already registered!!");
	}
	userDao.save(user);
	return viewAllUsers();
	}

	@Override
	public List<UserDto> updateUser(UserDto userDto) throws DuplicateRecordException,RecordNotFoundException {
		User user = mapToEntity(userDto);
		userDao.save(user);
		return viewAllUsers();
		
	}

	@Override
	public List<UserDto> deleteUser(Integer userLoginId) throws RecordNotFoundException{
		if(userDao.findById(userLoginId).isPresent()) {
			userDao.deleteById(userLoginId);
			return viewAllUsers();
		}
		else throw new RecordNotFoundException("User Not Found with ID : "+userLoginId+" to Delete");
		
	  }
	
	@Override
	public List<UserDto> viewAllUsers() throws RecordNotFoundException {
		List<User> users = userDao.findAll();
		if (users.isEmpty()) {
			throw new RecordNotFoundException("No users found!!");
		}
		return users.stream().map(this :: mapToDTO).toList();
	  }


	@Override
	public UserDto getUserByUsername(String userName) throws RecordNotFoundException {
		User user = userDao.findByUserName(userName);
        if (user == null) {
            throw new RecordNotFoundException("Could not find user");
        }
        return mapToDTO(user);
         
    }

	@Override
	public List<UserDto> save(User bean) throws RecordNotFoundException {
		userDao.save(bean);
		return viewAllUsers();
	}
	
	@Override
    public Optional<UserDto> findById(Integer id) throws RecordNotFoundException {
        Optional<User> user = userDao.findById(id);
        if (!user.isPresent()) {
            throw new RecordNotFoundException("User not found with id: " + id);
        }
        return Optional.of(mapToDTO(user.get()));
    }
	
	private UserDto mapToDTO(User user){
		return mapper.map(user, UserDto.class);
    }

    private User mapToEntity(UserDto userDto){
    	return mapper.map(userDto, User.class);
    }

}