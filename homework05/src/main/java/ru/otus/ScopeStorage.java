package ru.otus;

public class ScopeStorage {

    ThreadLocal<Scope> currentScope = new ThreadLocal<>();

    Scope getCurrentScope() {
        return currentScope.get();
    }

    void setCurrentScope(Scope scope) {
        currentScope.set(scope);
    }
}
