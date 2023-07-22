package snapje.packetapi.Functions;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import snapje.packetapi.Packets.PacketPlayOutTitle;
import snapje.packetapi.Utils.NetMinecraftServer;

public class Title {
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	
	private Player player;
	private String title;
	private String subTitle;
	private int fadeInTicks;
	private int fadeOutTicks;
	private int displayTicks;
	
	public Title(Player player, String title) {
		this.player = player;
		this.title = ChatColor.translateAlternateColorCodes('&', title);
		this.subTitle = null;
	}
	public Title(Player player, String title, String subtitle) {
		this.player = player;
		this.title = ChatColor.translateAlternateColorCodes('&', title);
		this.subTitle = ChatColor.translateAlternateColorCodes('&', subtitle);
	}
	
	public Title(Player player, String title, int fadeInTicks, int displayTicks, int fadeOutTicks) {
		this.player = player;
		this.title = ChatColor.translateAlternateColorCodes('&', title);
		this.subTitle = null;
	}
	
	public Title(Player player, String title, String subtitle, int fadeInTicks, int displayTicks, int fadeOutTicks) {
		this.player = player;
		this.title = ChatColor.translateAlternateColorCodes('&', title);
		this.subTitle = ChatColor.translateAlternateColorCodes('&', subtitle);
	}
	
	public void sendTitle() {
		if(this.player == null) return;
		PacketPlayOutTitle title = new PacketPlayOutTitle(this.title, this.subTitle, this.fadeInTicks, this.displayTicks, this.fadeOutTicks);
		nms.sendPacket(player, title.getTitlePacket());
		nms.sendPacket(player, title.getSubTitlePacket());
	}

}
