package com.davidout.api.minigame;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameDetails {

    private final UUID uuid;
    private final List<Player> players;
    private final List<Player> spectators;

    public GameDetails() {
        this.uuid = UUID.randomUUID();
        this.players = new ArrayList<>();
        this.spectators = new ArrayList<>();
    }


    public UUID getGameId() {return this.uuid;}
    public List<Player> getPlayers() {return players;}
    public List<Player> getSpectators() {return spectators;}


    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addSpectator(Player player) {
        this.spectators.add(player);
    }

    public void setPlayerToSpectator(Player player) {
        this.players.remove(player);
        this.spectators.add(player);
    }

}
