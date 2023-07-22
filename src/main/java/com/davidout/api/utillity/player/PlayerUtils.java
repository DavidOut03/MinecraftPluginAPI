package com.davidout.api.utillity.player;

import com.davidout.api.utillity.server.Version;
import com.davidout.api.utillity.text.TextUtils;
import com.davidout.api.utillity.server.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PlayerUtils {

    public static void setNameTag(Player p, String prefix, String suffix) {
        if(!Version.serverVersionIsAbove("1.7")) return;

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = (scoreboard.getTeam(p.getName()) == null) ? scoreboard.getTeam(p.getName()) : scoreboard.registerNewTeam(p.getName());
        if(prefix != null && !prefix.isEmpty()) team.setPrefix(TextUtils.formatColorCodes(prefix));
        if(suffix != null && !suffix.isEmpty()) team.setSuffix(TextUtils.formatColorCodes(suffix));
        team.addEntry(p.getName());
    }

    public static void resetNameTag(Player p) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam(p.getName());
        if (team == null) return;
            team.removeEntry(p.getName());
            if (!team.getEntries().isEmpty()) return;
                team.unregister();
    }

    public static void setTextBelowName(Player p, String text) {
        Scoreboard scoreboard = p.getScoreboard();
        Objective objective = scoreboard.registerNewObjective("belowName", "dummy", TextUtils.formatColorCodes(text));
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.getScore(TextUtils.formatColorCodes(text)).setScore(0);

    }
}
