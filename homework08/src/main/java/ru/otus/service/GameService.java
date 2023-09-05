package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.ioc.FunctionWithObjects;
import ru.otus.ioc.IoC;
import ru.otus.model.GameObject;
import ru.otus.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GameService {

    private final Map<UUID, GameObject> games;

    public GameService() {
        this.games = new HashMap<>();
        IoC.resolve("IoC.Register", "createGame", (FunctionWithObjects) (args) -> new GameObject((Map<String, User>) args[0], UUID.randomUUID()));
    }

    public GameObject createGame(Map<String, User> gamers) {
        GameObject game = (GameObject) IoC.resolve("createGame", gamers);
        games.put(game.getGameId(), game);
        return game;
    }

    public GameObject getGameObject(UUID gameId) {
        return games.get(gameId);
    }
}
