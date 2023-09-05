package ru.otus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.auth.JwtCreator;
import ru.otus.model.GameObject;
import ru.otus.model.User;
import ru.otus.service.GameService;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
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
    @MockBean
    private GameService gameService;

    @Test
    public void shouldReturnToken() throws Exception {
        User user = new User("stepa", "123");
        UUID gameId = UUID.fromString("51a3ebda-2fcd-410a-bce7-e00cd582d89d");

        var gamersList = List.of(
                new User().setName("vasya"),
                new User().setName("petya"),
                new User().setName("stepa")
        );

        var gamers = gamersList.stream().collect(Collectors.toMap(User::getName, Function.identity()));
        ;

        GameObject gameObject = new GameObject(gamers, gameId);

        given(gameService.getGameObject(gameId)).willReturn(gameObject);

        String expectedResult = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzdGVwYSIsImdhbWVJZCI6IjUxYTNlYmRhLTJmY2QtNDEwYS1iY2U3LWUwMGNkNTgyZDg5ZCJ9.AGBj4SpsvR2SI8M4GrlPSYvFN451zgINQx6F9Q53rTlIiPn1ivH4UbQ6d46enb9tpLY1V3dls8JuPq_ty1mj3sehuZoxJorCVGumXCDdmCKCfK2knYuHBfYWsYmylvGY1u3t_j5BQdEh4Te0nEvz58jVhUGypTLtb7hgt_bwIimTi-bTKLS3q_dnTt-kohvlIKGgCjyFqbRX1s8QBjjurKVx7ZUoJOe6MatGj4e7HvHts4ZhyOpkehfM5V0NUaWJhROheS8UqatfUkodV2fFCx0l4Lxh4NpEWkrc98-ps0KNeM3MpjudT3dq3EhzA1cDRZRkB0rfFcZ7HFgauNsulg";

        mockMvc.perform(post("/api/authenticate/" + gameId).contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResult));
    }

    @Test
    public void shouldCreateNewBattle() throws Exception {
        var gamersList = List.of(
                new User().setName("vasya"),
                new User().setName("petya"),
                new User().setName("stepa")
        );

        var gamers = gamersList.stream().collect(Collectors.toMap(User::getName, Function.identity()));
        ;

        UUID expectedResult = UUID.fromString("51a3ebda-2fcd-410a-bce7-e00cd582d89d");

        GameObject gameObject = new GameObject(gamers, expectedResult);
        given(gameService.createGame(gamers)).willReturn(gameObject);

        mockMvc.perform(post("/api/create-new-battle").contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(gamers)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResult.toString()));
    }

}
