package mcapi.davidout.controller.listener;

import mcapi.davidout.MinecraftPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        MinecraftPlugin.getPlugin().getScoreboardManager().removeFromScoreboard(e.getPlayer());
    }
}
