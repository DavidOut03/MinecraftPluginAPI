package com.davidout.api.minigame;

public abstract class Game {

    private final GameDetails details;
    private GameState gameState;

    public Game() {
        this.details = new GameDetails();
        this.gameState = GameState.LOBBY;
    }

    public Game(GameDetails details) {
        this.details = new GameDetails();
        this.gameState = GameState.LOBBY;
    }

    public void setGameState(GameState newGameState) {
        this.gameState = newGameState;
        this.handleGameState();
    }

    private void handleGameState() {
        switch (gameState) {
            case LOBBY -> handleLobbyState();
            case STARTING -> handleStartingState();
            case STARTED -> handleStartedState();
            case STOPPING -> handleStoppingState();
            case STOPPED -> {
                handleStoppedState();
                GameManager.removeGame(this);
            }
        }
    }

    public abstract void handleLobbyState();
    public abstract void handleStartingState();
    public abstract void handleStartedState();
    public abstract void handleStoppingState();
    public abstract void handleStoppedState();



    public GameState getGameState() {
        return this.gameState;
    }

    public GameDetails getGameDetails() {
        return details;
    }
}
