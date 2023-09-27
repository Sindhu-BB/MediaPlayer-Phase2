/*package com.example.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.user.UserApplication;
import com.example.user.controller.MessageController;



@ContextConfiguration(classes = UserApplication.class)
@WebMvcTest(MessageController.class)

class MessageControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Test
    void testMessage() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.get("/message")).andExpect(MockMvcResultMatchers.status().is(500)).andExpect(MockMvcResultMatchers.content().string("No Scope registered for scope name 'refresh'"));
        
               
    }
}*/
