package com.davidout.api.minecraft.custom.scoreboard;

import com.davidout.api.minecraft.MinecraftPlugin;
import com.davidout.api.minecraft.utillity.text.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.*;

public abstract class CustomScoreboard  {

    private String name;
    private String title;
    private final HashMap<Player, Scoreboard> players;
    private int schedular;

    public CustomScoreboard() {
        this.name = "Custom Scoreboard";
        this.players = new HashMap<>();
        this.title = TextUtils.formatColorCodes(this.name);
    }

    /**
     *
     *  Scoreboard management
     *
     */


    private void resetScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("custom", "dummy", "Custom Scoreboard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
        players.put(player, scoreboard);

        updateScoreboard();
    }

    private void startUpdater() {
        this.schedular = Bukkit.getScheduler().scheduleSyncRepeatingTask(MinecraftPlugin.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if(players.isEmpty()) {
                    stopUpdater();
                    return;
                }
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


        players.forEach((player, scoreboard) -> {
            if(player == null) return;
            setLines(player);
        });
    }


    private void setLines(Player player) {
            List<String> newLines = update(player);
            Collections.reverse(newLines);


            if(player == null) return;
            players.get(player).getObjective(DisplaySlot.SIDEBAR).setDisplayName(TextUtils.formatColorCodes(title));

            for(int i = 0; i < newLines.size(); i++) {
                setLine(players.get(player), i, newLines.get(i));
            }

    }

    private void setLine(Scoreboard scoreboard, int line, String newText) {
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
        Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);

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
        this.resetScoreboard(p);
    }

    public void removeFromScoreboard(Player p) {
        players.remove(p);
        p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
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
