package ru.otus.model;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class GameObject {
    private final Map<String, User> gamers;
    private UUID gameId;

    public GameObject(Map<String, User> gamers, UUID uuid) {
        this.gamers = gamers;
        this.gameId = uuid;
    }
}
