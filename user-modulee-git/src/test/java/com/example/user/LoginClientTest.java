package com.example.user;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.user.entity.Login;
import com.example.user.exception.RecordNotFoundException;
import com.example.user.feignclient.LoginClient;

class AdminClientTest {

	@Mock
    private LoginClient loginClient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertLoginRecord() {
        // Mock the expected response from the loginClient
        Login loginRecord = new Login();
        Mockito.when(loginClient.insertLoginRecord(Mockito.any(Login.class)))
                .thenReturn(ResponseEntity.ok().build());

        // Call the insertLoginRecord method from the loginClient
        ResponseEntity<List<Login>> response = loginClient.insertLoginRecord(loginRecord);

        // Verify the response
        Assertions.assertEquals(200, response.getStatusCodeValue());

        // Verify that the loginClient method was called with the correct argument
        Mockito.verify(loginClient).insertLoginRecord(loginRecord);
    }

    @Test
    void testDeleteLoginRecordById() throws RecordNotFoundException {
        // Mock the expected response from the loginClient
        int id = 1;
        Mockito.when(loginClient.deleteLoginRecordById(id))
                .thenReturn(ResponseEntity.ok().build());

        // Call the deleteLoginRecordById method from the loginClient
        ResponseEntity<List<Login>> response = loginClient.deleteLoginRecordById(id);

        // Verify the response
        Assertions.assertEquals(200, response.getStatusCodeValue());

        // Verify that the loginClient method was called with the correct argument
        Mockito.verify(loginClient).deleteLoginRecordById(id);
    }

    @Test
    void testFindLoginRecordByUserName() throws RecordNotFoundException {
        // Mock the expected response from the loginClient
        String userName = "john";
        Login expectedRecord = new Login();
        Mockito.when(loginClient.findLoginRecordByUserName(userName))
                .thenReturn(expectedRecord);

        // Call the findLoginRecordByUserName method from the loginClient
        Login actualRecord = loginClient.findLoginRecordByUserName(userName);

        // Verify the result
        Assertions.assertEquals(expectedRecord, actualRecord);

        // Verify that the loginClient method was called with the correct argument
        Mockito.verify(loginClient).findLoginRecordByUserName(userName);
    }

    @Test
    void testUpdateLoginRecord() throws RecordNotFoundException {
        // Mock the expected response from the loginClient
        Login loginRecord = new Login();
        Mockito.when(loginClient.updateLoginRecord(Mockito.any(Login.class)))
                .thenReturn(ResponseEntity.ok().build());

        // Call the updateLoginRecord method from the loginClient
        ResponseEntity<List<Login>> response = loginClient.updateLoginRecord(loginRecord);

        // Verify the response
        Assertions.assertEquals(200, response.getStatusCodeValue());

        // Verify that the loginClient method was called with the correct argument
        Mockito.verify(loginClient).updateLoginRecord(loginRecord);
    }

    @Test
    void testDeleteLoginRecordByUserName() throws RecordNotFoundException {
        // Mock the expected response from the loginClient
        String userName = "john";
        List<Login> expectedRecords = List.of(new Login(), new Login());
        Mockito.when(loginClient.deleteLoginRecordByUserName(userName))
                .thenReturn(expectedRecords);

        // Call the deleteLoginRecordByUserName method from the loginClient
        List<Login> actualRecords = loginClient.deleteLoginRecordByUserName(userName);

        // Verify the result
        Assertions.assertEquals(expectedRecords, actualRecords);

        // Verify that the loginClient method was called with the correct argument
        Mockito.verify(loginClient).deleteLoginRecordByUserName(userName);
    }


}

