package com.example.user.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.user.entity.Login;
import com.example.user.exception.RecordNotFoundException;

@FeignClient(name="login-service",url="http://localhost:8081/login-app/api/login")       //(name ="login-app")
public interface LoginClient {
	
	@PostMapping("/insertLoginRecord")
	public ResponseEntity<List<Login>> insertLoginRecord(
		@RequestBody Login bean);
	
	
	
	@DeleteMapping("/deleteLoginRecordById/{id}")
	public ResponseEntity<List<Login>> deleteLoginRecordById(
			@PathVariable("id")Integer id) throws RecordNotFoundException ;
	
	
	
	@GetMapping("/findLoginRecordByUserName/{userName}")
	public Login findLoginRecordByUserName(@PathVariable("userName")String userName) throws RecordNotFoundException;
	
	
	@PutMapping("/updateLoginRecord")
	public ResponseEntity<List<Login>> updateLoginRecord(
			@RequestBody Login bean) throws RecordNotFoundException ;
	
	
	@DeleteMapping("/deleteLoginRecordByUserName/{userName}")
	public List<Login> deleteLoginRecordByUserName(@PathVariable("userName") String userName) throws RecordNotFoundException;
}
