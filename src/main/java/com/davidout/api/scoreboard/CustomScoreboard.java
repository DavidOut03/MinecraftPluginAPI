package com.davidout.api.scoreboard;

import com.davidout.api.MinecraftPlugin;
import com.davidout.api.utillity.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomScoreboard  {

    private Scoreboard board;
    private Objective objective;
    private String name;
    private String title;
    private List<String> players;
    private int schedular;

    public CustomScoreboard() {
        this.name = (name == null) ? "Custom Scoreboard" : getName();
        this.players = new ArrayList<>();
        this.title = TextUtils.formatColorCodes(this.name);
        this.resetScoreboard();
    }

    /**
     *
     *  Scoreboard management
     *
     */


    private void resetScoreboard() {
        Bukkit.getScoreboardManager().getMainScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        this.board =  Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = this.board.registerNewObjective("customObjective", "dummy");
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName(this.title);
    }

    private void startUpdater() {
        this.schedular = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinecraftPlugin.getPlugin(), new Runnable() {
            @Override
            public void run() {
             updateScoreboard();
            }
        }, 0L, (long) updateTimeInSeconds() * 20);
    }



    private void stopUpdater() {
        Bukkit.getScheduler().cancelTask(this.schedular);
    }

    public void updateScoreboard() {
        if(players == null || players.isEmpty()) {
            stopUpdater();
            return;
        }

        players.forEach(playerName -> {
            if(playerName == null || Bukkit.getPlayer(playerName) == null) return;

            Player player = Bukkit.getPlayer(playerName);
            List<String> lines = update(player);
            List<String> oldLines = new ArrayList<>(board.getEntries());

            if(playerName == null || Bukkit.getPlayer(playerName) == null) return;
            for (int i = 0; i < lines.size(); i++) {
                if(playerName == null || Bukkit.getPlayer(playerName) == null) return;

                String lineName = "line" + i;

                Team team = (board.getTeam(lineName) == null) ? board.registerNewTeam(lineName) : board.getTeam(lineName);
                team.setPrefix(getPrefix(lines.get(i)));
                team.setSuffix(getSuffix(lines.get(i)));

                if(!oldLines.isEmpty() && oldLines.get(0) != null) board.resetScores(oldLines.get(i));
                team.addEntry(getEntry(lines.get(i)));

                if(player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) {
                    removeFromScoreboard(player);
                    return;
                }

                if(playerName == null || Bukkit.getPlayer(playerName) == null) return;
                Score score =  player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(getEntry(lines.get(i)));
                score.setScore(i);

            }
        });
    }

    public void setLine(Player player, int line, String newLine) {
        List<String> lines = new ArrayList<>(board.getEntries());
        List<String> oldLines = new ArrayList<>(board.getEntries());
        lines.set(line, newLine);

        String lineName = "line" + line;
        Team team = (board.getTeam(lineName) == null) ? board.registerNewTeam(lineName) : board.getTeam(lineName);
        team.setPrefix(getPrefix(lines.get(line)));
        team.setSuffix(getSuffix(lines.get(line)));

        if(!oldLines.isEmpty() && oldLines.get(0) != null) board.resetScores(oldLines.get(line));
        team.addEntry(getEntry(lines.get(line)));

        Score score =  player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(getEntry(lines.get(line)));
        score.setScore(line);
    }

    /**
     *
     * Player management
     *
     */

    public void addToScoreboard(Player p) {
        if(this.players.isEmpty() && useUpdate()) startUpdater();
        this.players.add(p.getName());
        p.setScoreboard(board);
    }

    public void removeFromScoreboard(Player p) {
        this.players.remove(p.getName());
        p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        if(this.players.isEmpty() && useUpdate()) stopUpdater();
    }

    /**
     *
     *  Update management
     *
     */


    private String getPrefix(String line) {
        String lineText = (line == null) ? "" : TextUtils.formatColorCodes(line);
        return (lineText.length() <= 16) ? "" : lineText.substring(0, 16);
    }

    private String getEntry(String line) {
        String lineText = (line == null) ? "" : TextUtils.formatColorCodes(line);
        return (lineText.length() <= 16) ? lineText : (lineText.length() <= 32) ? ChatColor.getLastColors(getPrefix(line)) + lineText.substring(15, lineText.length()) : ChatColor.getLastColors(getPrefix(line)) + lineText.substring(15, 31);
    }

    private String getSuffix(String line) {
        String lineText = (line == null) ? "" : TextUtils.formatColorCodes(line);
        return (lineText.length() <= 32) ? "" : ChatColor.getLastColors(getEntry(line)) + lineText.substring(15, lineText.length());
    }



    /**
     *
     *  Global methods
     *
     */

    public String getTitle() {return this.title;}
    public void setTitle(String newTitle) {
        this.title = (newTitle.length() <= 32) ? TextUtils.formatColorCodes(newTitle) : TextUtils.formatColorCodes(newTitle.substring(0, 32));
    }

    /**
     *
     * Abstrackt methods
     *
     */

    public abstract String getName();
    public abstract List<String> update(Player player);
    public abstract boolean useUpdate();
    public abstract long updateTimeInSeconds();

}
