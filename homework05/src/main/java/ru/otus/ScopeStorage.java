package ru.otus;

public class ScopeStorage {
    ThreadLocal<ScopeInterface> scope = new ThreadLocal<>();
}
