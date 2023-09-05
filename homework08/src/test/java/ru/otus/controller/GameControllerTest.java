package ru.otus.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({GameController.class})
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldExecuteCommand() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzdGVwYSIsImdhbWVJZCI6IjUxYTNlYmRhLTJmY2QtNDEwYS1iY2U3LWUwMGNkNTgyZDg5ZCJ9.AGBj4SpsvR2SI8M4GrlPSYvFN451zgINQx6F9Q53rTlIiPn1ivH4UbQ6d46enb9tpLY1V3dls8JuPq_ty1mj3sehuZoxJorCVGumXCDdmCKCfK2knYuHBfYWsYmylvGY1u3t_j5BQdEh4Te0nEvz58jVhUGypTLtb7hgt_bwIimTi-bTKLS3q_dnTt-kohvlIKGgCjyFqbRX1s8QBjjurKVx7ZUoJOe6MatGj4e7HvHts4ZhyOpkehfM5V0NUaWJhROheS8UqatfUkodV2fFCx0l4Lxh4NpEWkrc98-ps0KNeM3MpjudT3dq3EhzA1cDRZRkB0rfFcZ7HFgauNsulg";

        mockMvc.perform(get("/doMethod").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotExecuteCommand() throws Exception {
        mockMvc.perform(get("/doMethod")).andExpect(status().isForbidden());

    }
}
