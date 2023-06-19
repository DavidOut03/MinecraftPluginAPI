package com.davidout.api.custom.scoreboard;

import com.davidout.api.MinecraftPlugin;
import com.davidout.api.utillity.text.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        this.setTitle(getName());

        updateScoreboard();
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
            setLines(playerName);
        });
    }


    private void setLines(String playerName) {
            if(playerName == null || Bukkit.getPlayer(playerName) == null) return;
            Player player = Bukkit.getPlayer(playerName);
            List<String> newLines = update(player);
            Collections.reverse(newLines);


            if(player == null) return;
            Scoreboard scoreboard = player.getScoreboard();
            scoreboard.getObjective(DisplaySlot.SIDEBAR).setDisplayName(TextUtils.formatColorCodes(title));


            for(int i = 0; i < newLines.size(); i++) {
                setLine(scoreboard, i, newLines.get(i), player);
            }

            player.setScoreboard(scoreboard);
    }

    private void setLine(Scoreboard scoreboard, int line, String newText, Player player) {
        Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
        String prefix = getPrefix(newText);
        String entry = ChatColor.getLastColors(prefix) + getEntry(newText);
        String suffix = ChatColor.getLastColors(entry) + getSuffix(newText);


        this.resetOldLine(line, scoreboard);
        Team team = (scoreboard.getTeam("line" + line) == null) ? scoreboard.registerNewTeam("line" + line) : scoreboard.getTeam("line" + line);
        if(team == null) return;
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry);

        Score score = objective.getScore(TextUtils.formatColorCodes(newText));
        score.setScore(line + 1);
    }

    private void resetOldLine(int line, Scoreboard scoreboard) {
        for(String entry : objective.getScoreboard().getEntries()) {
            Score score = objective.getScore(entry);
            if(score.getScore() != (line + 1)) continue;
            scoreboard.resetScores(entry);
        }
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
        String lineText = (line == null) ? "" : ChatColor.getLastColors(getPrefix(line)) + TextUtils.formatColorCodes(line);
        return (lineText.length() <= 16) ? lineText : (lineText.length() <= 32) ? lineText.substring(15, lineText.length()) : lineText.substring(15, 31);
    }

    private String getSuffix(String line) {
        String lineText = (line == null) ? "" : ChatColor.getLastColors(getEntry(line)) + TextUtils.formatColorCodes(line);
        return (lineText.length() <= 32) ? "" : lineText.substring(15, 31);
    }



    /**
     *
     *  Global methods
     *
     */

    public String getTitle() {return this.title;}
    public void setTitle(String newTitle) {
       this.title = (newTitle.length() > 32) ? TextUtils.formatColorCodes(newTitle.substring(0, 32)) : TextUtils.formatColorCodes(newTitle);
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
