package com.example.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.user.entity.Login;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginResponseTest {

	private static Login login;

    @BeforeAll
    public static void setUp() {
        login = new Login();
    }

    @Test
    @Order(2)
    void testGettersAndSetters() {
        int loginId = 1;
        String userName = "john.doe";
        String role = "admin";
        String password = "password";

        login.setLoginId(loginId);
        login.setUserName(userName);
        login.setRole(role);
        login.setPassword(password);

        assertEquals(loginId, login.getLoginId());
        assertEquals(userName, login.getUserName());
        assertEquals(role, login.getRole());
        assertEquals(password, login.getPassword());
    }

    @Test
    @Order(3)
    void testToString() {
        int loginId = 1;
        String userName = "john.doe";
        String role = "admin";
        String password = "password";

        login.setLoginId(loginId);
        login.setUserName(userName);
        login.setRole(role);
        login.setPassword(password);

        String expectedToString = "Login [loginId=" + loginId + ", userName=" + userName + ", role=" + role + ", password=" + password + "]";
        assertEquals(expectedToString, login.toString());
    }

    @Test
    @Order(1)
    void testDefaultConstructor() {
        assertNotNull(login);
        assertEquals(0, login.getLoginId());
        assertNull(login.getUserName());
        assertNull(login.getRole());
        assertNull(login.getPassword());
    }
}

