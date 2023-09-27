package com.example.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.user.dao.IUserDao;
import com.example.user.dto.UserDto;
import com.example.user.entity.User;
import com.example.user.exception.RecordNotFoundException;
import com.example.user.service.UserServiceImpl;




@SpringBootTest
@ContextConfiguration(classes = UserApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class UserServiceTest {
	
	private IUserDao userDao;
    private ModelMapper mapper;
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        userDao = mock(IUserDao.class);
        mapper = new ModelMapper();
        userService = new UserServiceImpl(userDao, mapper);
        UserDto userDto = new UserDto(1, "firstName", "lastName", "userName", "janu@gmail.com", "janu@123","user");
        List<UserDto> users = new ArrayList<>();
        users.add(userDto);
    }

 

    @Test
     void testDeleteUser_RecordNotFoundException() throws RecordNotFoundException {
        // Arrange
        Integer userLoginId = 1;
        when(userDao.findById(userLoginId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RecordNotFoundException.class, () -> userService.deleteUser(userLoginId));
        verify(userDao, times(1)).findById(userLoginId);
        verify(userDao, never()).deleteById(userLoginId);
    }

    @Test
     void testViewAllUsers_Success() throws RecordNotFoundException {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userDao.findAll()).thenReturn(users);

        // Act
        List<UserDto> result = userService.viewAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(users.size(), result.size());
        verify(userDao, times(1)).findAll();
    }

    @Test
     void testViewAllUsers_RecordNotFoundException() throws RecordNotFoundException {
        // Arrange
        when(userDao.findAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(RecordNotFoundException.class, () -> userService.viewAllUsers());
        verify(userDao, times(1)).findAll();
    }

    @Test
     void testGetUserByUsername_Success() throws RecordNotFoundException {
        // Arrange
        String userName = "john";
        User user = new User();
        when(userDao.findByUserName(userName)).thenReturn(user);

        // Act
        UserDto result = userService.getUserByUsername(userName);

        // Assert
        assertNotNull(result);
        verify(userDao, times(1)).findByUserName(userName);
    }

    @Test
     void testGetUserByUsername_RecordNotFoundException() throws RecordNotFoundException {
        // Arrange
        String userName = "john";
        when(userDao.findByUserName(userName)).thenReturn(null);

        // Act & Assert
        assertThrows(RecordNotFoundException.class, () -> userService.getUserByUsername(userName));
        verify(userDao, times(1)).findByUserName(userName);
    }

    @Test
     void testFindById_Success() throws RecordNotFoundException {
        // Arrange
        Integer id = 1;
        User user = new User();
        when(userDao.findById(id)).thenReturn(Optional.of(user));

        // Act
        Optional<UserDto> result = userService.findById(id);

        // Assert
        assertTrue(result.isPresent());
        verify(userDao, times(1)).findById(id);
    }

    @Test
     void testFindById_RecordNotFoundException() throws RecordNotFoundException {
        // Arrange
        Integer id = 1;
        when(userDao.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RecordNotFoundException.class, () -> userService.findById(id));
        verify(userDao, times(1)).findById(id);
    }
}
