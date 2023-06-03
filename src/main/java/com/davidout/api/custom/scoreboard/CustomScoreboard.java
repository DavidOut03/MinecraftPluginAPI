package com.davidout.api.custom.scoreboard;

import com.davidout.api.MinecraftPlugin;
import com.davidout.api.utillity.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Collections;
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
            Collections.reverse(lines);

            for(int i = 0; i < lines.size(); i++) {
                setLine(player, i, lines);
            }
        });
    }

    public void setLine(Player player, int line, List<String> newLines) {
        List<String> oldLines = new ArrayList<>(player.getScoreboard().getEntries());
        String newLine = newLines.get(line);

        if(line >= oldLines.size() || oldLines.get(line) == null) {
            setScore(player, line, newLine);
            return;
        }

        if(oldLines.get(line).equalsIgnoreCase(newLine)) return;
        setScore(player, line, newLine);
    }

    private void setScore(Player player, int line, String newScore) {
        List<String> oldLines = new ArrayList<>(player.getScoreboard().getEntries());
        String prefix = getPrefix(newScore);
        String entry = getEntry(newScore);
        String suffix = getSuffix(newScore);

        Objective objective = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
        if(objective != null && oldLines.size() > line && oldLines.get(line) != null && objective.getScore(oldLines.get(line)) != null) {
            objective.getScoreboard().resetScores(oldLines.get(line));
        }

        String lineName = "line" + line;
        Team team = (board.getTeam(lineName) == null) ? board.registerNewTeam(lineName) : board.getTeam(lineName);
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry);

        Score score = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(entry);
        score.setScore(line + 1);
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
        return (lineText.length() <= 16) ? lineText : (lineText.length() <= 32) ? lineText.substring(15, lineText.length()) : lineText.substring(15, 31);
    }

    private String getSuffix(String line) {
        String lineText = (line == null) ? "" : TextUtils.formatColorCodes(line);
        return (lineText.length() <= 32) ? "" : lineText.substring(15, 31);
    }



    /**
     *
     *  Global methods
     *
     */

    public String getTitle() {return this.title;}
    public void setTitle(String newTitle) {this.title = TextUtils.formatColorCodes(newTitle.substring(0, 31));}

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
