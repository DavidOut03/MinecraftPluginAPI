package snapje.packetapi.Functions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import snapje.packetapi.Packets.PacketPlayOutChat;
import snapje.packetapi.Parameters.ChatMessageType;
import snapje.packetapi.Utils.NetMinecraftServer;

public class ActionBar {
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	private Player player;
	private String message;
	
	public ActionBar(Player p, String message) {
		this.player = p;
		this.message = message;
	}
	
	public ActionBar(String message) {
		this.message = message;
	}
	
	public void sendActionBar() {
		if(nms.getVersion() < 1.08) return;
		if(player == null) return;
	
		if(nms.getVersion() < 1.12) {
			PacketPlayOutChat packet = new PacketPlayOutChat(this.message, ChatMessageType.ACTIONBAR);
			nms.sendPacket(player, packet.getPacket());
		} else {
			PacketPlayOutChat packet = new PacketPlayOutChat(this.message, ChatMessageType.ACTIONBAR, player.getUniqueId());
			nms.sendPacket(player, packet.getPacket());
		}
		
		
	}
	
	public void sendActionBarToAllPlayers() {
		if(nms.getVersion() < 1.08) return;
		for(Player online : Bukkit.getOnlinePlayers()) {
			if(nms.getVersion() < 1.12) {
				PacketPlayOutChat packet = new PacketPlayOutChat(this.message, ChatMessageType.ACTIONBAR);
				nms.sendPacket(online, packet.getPacket());
			} else {
				PacketPlayOutChat packet = new PacketPlayOutChat(this.message, ChatMessageType.ACTIONBAR, player.getUniqueId());
				nms.sendPacket(online, packet.getPacket());
			}
		}
		
	}
	

}
