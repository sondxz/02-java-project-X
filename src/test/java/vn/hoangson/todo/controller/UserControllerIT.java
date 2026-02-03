package vn.hoangson.todo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;

import vn.hoangson.todo.domain.User;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUser_shouldReturnUser_whenValid() throws Exception {
        // arrange
        User inputUser = new User(null, "eric", "hoidanit@gmail.com");

        // action
        String resultStr = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(inputUser))).andExpect(status().isCreated()).andReturn()
                .getResponse().getContentAsString();

        // assert
        System.out.println("resultStr: " + resultStr);
        User outputUser = objectMapper.readValue(resultStr, User.class);
        assertEquals(inputUser.getName(), outputUser.getName());
    }
}
