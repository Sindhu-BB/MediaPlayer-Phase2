package com.example.user.controller;

import java.util.List;
import java.util.Optional;

//import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.user.dto.UserDto;
import com.example.user.entity.Login;
import com.example.user.exception.DuplicateRecordException;
import com.example.user.exception.RecordNotFoundException;
import com.example.user.feignclient.LoginClient;
import com.example.user.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private LoginClient loginClient;

	@PostMapping("/addUserRecord")
	public List<UserDto> addUser(@RequestBody UserDto user) throws DuplicateRecordException, RecordNotFoundException {
		Login login = mapToLogin(user);

		loginClient.insertLoginRecord(login);

		return this.userService.addUser(user);
	}

	@PutMapping("/updateUserRecord")
	public List<UserDto> updateUser(@RequestBody UserDto user)
			throws DuplicateRecordException, RecordNotFoundException {

		Login login = mapToLogin(user);
		loginClient.updateLoginRecord(login);
		return this.userService.updateUser(user);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/deleteUserRecord/{userLoginId}")
	public ResponseEntity<List<UserDto>> deleteUser(@PathVariable("userLoginId") Integer userLoginId)
			throws RecordNotFoundException {

		Optional<UserDto> p = this.userService.findById(userLoginId);
		if (p.isPresent()) {
			loginClient.deleteLoginRecordByUserName(p.get().getUserName());
		}
		List<UserDto> users = this.userService.deleteUser(userLoginId);

		if (users.isEmpty()) {
			return new ResponseEntity("Sorry! User with " + userLoginId + " is not available!", HttpStatus.NOT_FOUND);
		}
		logger.info("LoginRecord is successfully deleted");
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/viewAllUsers")
	public List<UserDto> viewAllUsers() throws RecordNotFoundException {
		return this.userService.viewAllUsers();
	}

	@GetMapping("/getUserByUsername/{userName}")
	public UserDto getUserByUsername(@PathVariable("userName") String userName) throws RecordNotFoundException {
		return this.userService.getUserByUsername(userName);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(RecordNotFoundException.class) // local to the RestController
	public final ResponseEntity<String> handleRecordNotFoundException(Exception ex, WebRequest request) {
		logger.error(ex.getMessage());
		return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private Login mapToLogin(UserDto user) {

		Login login = new Login();
		login.setUserName(user.getUserName());
		login.setPassword(user.getPassword());
		login.setRole(user.getRole());

		return login;

	}

}