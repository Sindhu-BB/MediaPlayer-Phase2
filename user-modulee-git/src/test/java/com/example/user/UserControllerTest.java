package com.example.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.request.WebRequest;

import com.example.user.controller.UserController;
import com.example.user.dto.UserDto;
import com.example.user.exception.DuplicateRecordException;
import com.example.user.exception.RecordNotFoundException;
import com.example.user.feignclient.LoginClient;
import com.example.user.service.IUserService;

@SpringBootTest
@ContextConfiguration(classes = UserApplication.class)
class UserControllerTest {

	@Mock
    private IUserService userService;

    @Mock
    private LoginClient loginClient;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser_Success() throws DuplicateRecordException, RecordNotFoundException {
        UserDto userDto = new UserDto();
        userDto.setUserName("john.doe");
        userDto.setPassword("password");
        userDto.setRole("USER");

        when(loginClient.updateLoginRecord(any())).thenReturn(ResponseEntity.ok().build());

        when(userService.addUser(userDto)).thenReturn(getDummyUserList());

        List<UserDto> result = userController.addUser(userDto);

        assertNotNull(result);
        assertEquals(getDummyUserList().size(), result.size());
        //verify(userService, times(1)).insertLoginRecord(any());
        verify(userService, times(1)).addUser(userDto);
    }

    @Test
    void testAddUser_DuplicateRecordException() throws DuplicateRecordException, RecordNotFoundException {
        UserDto userDto = new UserDto();
        userDto.setUserName("john.doe");
        userDto.setPassword("password");
        userDto.setRole("USER");

        when(loginClient.updateLoginRecord(any())).thenReturn(ResponseEntity.ok().build());

        when(userService.addUser(userDto)).thenThrow(DuplicateRecordException.class);

        assertThrows(DuplicateRecordException.class, () -> userController.addUser(userDto));
        //verify(userService, times(1)).insertLoginRecord(any());
        verify(userService, times(1)).addUser(userDto);
    }

    @Test
    void testAddUser_RecordNotFoundException() throws DuplicateRecordException, RecordNotFoundException {
        UserDto userDto = new UserDto();
        userDto.setUserName("john.doe");
        userDto.setPassword("password");
        userDto.setRole("USER");

        when(loginClient.updateLoginRecord(any())).thenReturn(ResponseEntity.ok().build());

        when(userService.addUser(userDto)).thenThrow(RecordNotFoundException.class);

        assertThrows(RecordNotFoundException.class, () -> userController.addUser(userDto));
       // verify(userService, times(1)).insertLoginRecord(any());
        verify(userService, times(1)).addUser(userDto);
    }

    @Test
    void testUpdateUser_Success() throws DuplicateRecordException, RecordNotFoundException {
        UserDto userDto = new UserDto();
        userDto.setUserName("john.doe");
        userDto.setPassword("password");
        userDto.setRole("USER");

        when(loginClient.updateLoginRecord(any())).thenReturn(ResponseEntity.ok().build());

        when(userService.updateUser(userDto)).thenReturn(getDummyUserList());

        List<UserDto> result = userController.updateUser(userDto);

        assertNotNull(result);
        assertEquals(getDummyUserList().size(), result.size());
        verify(loginClient, times(1)).updateLoginRecord(any());
        verify(userService, times(1)).updateUser(userDto);
    }

    @Test
    void testUpdateUser_DuplicateRecordException() throws DuplicateRecordException, RecordNotFoundException {
        UserDto userDto = new UserDto();
        userDto.setUserName("john.doe");
        userDto.setPassword("password");
        userDto.setRole("USER");

        when(loginClient.updateLoginRecord(any())).thenReturn(ResponseEntity.ok().build());

        when(userService.updateUser(userDto)).thenThrow(DuplicateRecordException.class);

        assertThrows(DuplicateRecordException.class, () -> userController.updateUser(userDto));
        verify(loginClient, times(1)).updateLoginRecord(any());
        verify(userService, times(1)).updateUser(userDto);
    }

    @Test
    void testUpdateUser_RecordNotFoundException() throws DuplicateRecordException, RecordNotFoundException {
        UserDto userDto = new UserDto();
        userDto.setUserName("john.doe");
        userDto.setPassword("password");
        userDto.setRole("USER");

        when(loginClient.updateLoginRecord(any())).thenReturn(ResponseEntity.ok().build());

        when(userService.updateUser(userDto)).thenThrow(RecordNotFoundException.class);

        assertThrows(RecordNotFoundException.class, () -> userController.updateUser(userDto));
        verify(loginClient, times(1)).updateLoginRecord(any());
        verify(userService, times(1)).updateUser(userDto);
    }

    @Test
    void testDeleteUser_Success() throws RecordNotFoundException {
        Integer userLoginId = 1;

        when(userService.findById(userLoginId)).thenReturn(Optional.of(getDummyUserDto()));
        when(loginClient.updateLoginRecord(any())).thenReturn(ResponseEntity.ok().build());

        when(userService.deleteUser(userLoginId)).thenReturn(getDummyUserList());

        ResponseEntity<List<UserDto>> responseEntity = userController.deleteUser(userLoginId);
        List<UserDto> result = responseEntity.getBody();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(result);
        assertEquals(getDummyUserList().size(), result.size());
        verify(userService, times(1)).findById(userLoginId);
        verify(loginClient, times(1)).deleteLoginRecordByUserName(anyString());
        verify(userService, times(1)).deleteUser(userLoginId);
    }


    @Test
    void testViewAllUsers_Success() throws RecordNotFoundException {
        when(userService.viewAllUsers()).thenReturn(getDummyUserList());

        List<UserDto> result = userController.viewAllUsers();

        assertNotNull(result);
        assertEquals(getDummyUserList().size(), result.size());
        verify(userService, times(1)).viewAllUsers();
    }

    @Test
    void testGetUserByUsername_Success() throws RecordNotFoundException {
        String userName = "john.doe";

        when(userService.getUserByUsername(userName)).thenReturn(getDummyUserDto());

        UserDto result = userController.getUserByUsername(userName);

        assertNotNull(result);
        assertEquals(getDummyUserDto().getUserName(), result.getUserName());
        assertEquals(getDummyUserDto().getPassword(), result.getPassword());
        assertEquals(getDummyUserDto().getRole(), result.getRole());
        verify(userService, times(1)).getUserByUsername(userName);
    }

    private List<UserDto> getDummyUserList() {
        List<UserDto> userList = new ArrayList<>();

        UserDto user1 = new UserDto();
        user1.setUserName("john.doe");
        user1.setPassword("password");
        user1.setRole("USER");
        userList.add(user1);

        UserDto user2 = new UserDto();
        user2.setUserName("jane.smith");
        user2.setPassword("password123");
        user2.setRole("ADMIN");
        userList.add(user2);

        return userList;
    }

    private UserDto getDummyUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUserName("john.doe");
        userDto.setPassword("password");
        userDto.setRole("USER");

        return userDto;
    }
    @Test
    void testHandleRecordNotFoundException() {
        UserController messageController = new UserController();

        String errorMessage = "Record not found";
        Exception ex = new RecordNotFoundException(errorMessage);
        WebRequest request = null;

        ResponseEntity<String> responseEntity = messageController.handleRecordNotFoundException(ex, request);

        assertEquals(errorMessage, responseEntity.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

}
