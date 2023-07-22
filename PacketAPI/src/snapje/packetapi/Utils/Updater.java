package snapje.packetapi.Utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public abstract class Updater implements Runnable {

    private int taskID;
	
	public Updater(Plugin plugin, int timebetween, int delay) {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, delay, timebetween);
	}
	
	public void cancelTask() {
		Bukkit.getScheduler().cancelTask(taskID);
	}
}
