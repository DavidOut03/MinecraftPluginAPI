package com.davidout.api.minecraft.custom.scoreboard;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreboardManager {

    private final List<CustomScoreboard> scoreboardList;
    private final HashMap<Player, CustomScoreboard> players;

    public ScoreboardManager() {
        this.scoreboardList = new ArrayList<>();
        this.players = new HashMap<>();
    }

    public void registerScoreboard(CustomScoreboard scoreboard) {
        this.scoreboardList.add(scoreboard);
    }

    public void unRegisterScoreboard(CustomScoreboard scoreboard) {
        this.scoreboardList.remove(scoreboard);
    }

    public void setScoreboard(Player player, CustomScoreboard scoreboard) {
        if(players.get(player) != null) players.get(player).removeFromScoreboard(player);

        if(scoreboard == null) return;
        this.players.put(player, scoreboard);
        scoreboard.addToScoreboard(player);
    }

    public void removeFromScoreboard(Player player) {
      if(players.get(player) == null) return;
      players.get(player).removeFromScoreboard(player);
    }


    public CustomScoreboard getScoreboard(String name) {
        if(name == null) return null;

        for (CustomScoreboard customScoreboard : this.scoreboardList) {
            if(!customScoreboard.getName().equalsIgnoreCase(name)) continue;
            return customScoreboard;
        }

        return null;
    }



}
