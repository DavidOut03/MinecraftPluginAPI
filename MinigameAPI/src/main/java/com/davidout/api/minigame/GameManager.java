package com.davidout.api.minigame;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GameManager {

    private static final List<Game> gameList = new ArrayList<>();

    public static void startNewGame(Game game) {
        gameList.add(game);
    }

    public static void removeGame(Game game) {
        gameList.remove(game);
    }

    public static void stopGame(Game game) {
        game.setGameState(GameState.STOPPING);
    }


    public static List<Game> getGames() {return gameList;}
    public static Game getGame(UUID uuid) {
        Optional<Game> returned = gameList.stream().filter(game -> game.getGameDetails().getGameId().equals(uuid)).findFirst();
       return returned.orElse(null);
    }

    public static Game getPlayersGame(Player player) {
        return gameList.stream().filter(game -> game.getGameDetails().getPlayers().contains(player) || game.getGameDetails().getSpectators().contains(player)).findFirst().orElse(null);
    }

    public static boolean playerIsSpectating(Player player) {
        return gameList.stream().filter(game -> game.getGameDetails().getSpectators().contains(player)).findFirst().orElse(null) != null;
    }

    public static boolean playerIsInGame(Player player) {
        return getPlayersGame(player) == null;
    }


}
