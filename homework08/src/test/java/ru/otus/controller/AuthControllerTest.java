package ru.otus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({AuthController.class, JwtCreator.class})
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnToken() throws Exception {
        User user = new User("stepa", "123");

        String expectedResult = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzdGVwYSJ9.Ur7fu0jhXKS8GY0CrEZTRAEwyyi7uB9NYfl1mL9wOTxGLMJov8kYCXJpthweCXT2QqNUB_E5CRHML9xHYad2vqpGBcOVzi7BzxxS-sCvvuQNE9VQNclwyXjjEDpa5Cb3_SALkb_qzvzS6lvwbMHhvzezuAXu3Il7uqo4MOI4PRGIQNXTXjJIR9kstRNfWGZdcec71-xXaJ0jtcBZLhN-UqLDrxAOTsEuOpBl1adxPpgYKZDv7_fBrrZ3irl5NRfAQc52yWCBc12joz8Ff4mfNnCcLK39Wu5c2jySHC3tj4aFEvcMn75GaGVDXotZJBI03X9JRGUpojzh6YD3lZBF3Q";

        mockMvc.perform(post("/api/authenticate").contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResult));
    }

    @Test
    public void shouldCreateNewBattle() throws Exception {
        var gamers = List.of(
                new User().setName("vasya"),
                new User().setName("petya"),
                new User().setName("stepa")
        );
        String expectedResult = "51a3ebda-2fcd-410a-bce7-e00cd582d89d";

        mockMvc.perform(post("/api/create-new-battle").contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(gamers)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResult));
    }

}
