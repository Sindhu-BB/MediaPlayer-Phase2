package com.example.user;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.user.entity.User;

class UserTest {

    @Test
    void testCreateUserWithValidData() {
        User user = new User(1, "John", "Doe", "johndoe", "johndoe@example.com", "password");
        Assertions.assertEquals(1, user.getUserLoginId());
        Assertions.assertEquals("John", user.getFirstName());
        Assertions.assertEquals("Doe", user.getLastName());
        Assertions.assertEquals("johndoe", user.getUserName());
        Assertions.assertEquals("johndoe@example.com", user.getEmailAddress());
        Assertions.assertEquals("password", user.getPassword());
    }



    @Test
    void testCreateUserWithValidEmailAddress() {
        User user = new User(1, "John", "Doe", "johndoe", "johndoe@example.com", "password");
        Assertions.assertEquals("johndoe@example.com", user.getEmailAddress());
    }


    @Test
    void testCreateUserWithPassword() {
        User user = new User(1, "John", "Doe", "johndoe", "johndoe@example.com", "password");
        Assertions.assertEquals("password", user.getPassword());
    }
    
    @Test
    void testGetSetUserLoginId() {
        int expectedId = 1;
        User user = new User();
        user.setUserLoginId(expectedId);

        int actualId = user.getUserLoginId();

        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    void testGetSetFirstName() {
        String expectedFirstName = "John";
        User user = new User();
        user.setFirstName(expectedFirstName);

        String actualFirstName = user.getFirstName();

        Assertions.assertEquals(expectedFirstName, actualFirstName);
    }
    
    

    @Test
    void testGetSetLastName() {
        String expectedLastName = "Doe";
        User user = new User();
        user.setLastName(expectedLastName);

        String actualLastName = user.getLastName();

        Assertions.assertEquals(expectedLastName, actualLastName);
    }

    @Test
    void testGetSetUserName() {
        String expectedUserName = "johndoe";
        User user = new User();
        user.setUserName(expectedUserName);

        String actualUserName = user.getUserName();

        Assertions.assertEquals(expectedUserName, actualUserName);
    }

    @Test
    void testGetSetEmailAddress() {
        String expectedEmailAddress = "johndoe@example.com";
        User user = new User();
        user.setEmailAddress(expectedEmailAddress);

        String actualEmailAddress = user.getEmailAddress();

        Assertions.assertEquals(expectedEmailAddress, actualEmailAddress);
    }

    @Test
    void testGetSetPassword() {
        String expectedPassword = "password123";
        User user = new User();
        user.setPassword(expectedPassword);

        String actualPassword = user.getPassword();

        Assertions.assertEquals(expectedPassword, actualPassword);
    }
    
    @Test
    void testGetSetRole() {
        String expectedRole = "user";
        User user = new User();
        user.setRole(expectedRole);

        String actualRole = user.getRole();

        Assertions.assertEquals(expectedRole, actualRole);
    }
}
