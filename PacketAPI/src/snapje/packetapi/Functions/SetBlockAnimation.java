package snapje.packetapi.Functions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import snapje.packetapi.Packets.PacketPlayOutBlockBreakAnimation;
import snapje.packetapi.Utils.NetMinecraftServer;

public class SetBlockAnimation {
	
	private static NetMinecraftServer nms = new NetMinecraftServer();
	
	private Player player;
	private Location block;
	private int animationStatus;
	
	public SetBlockAnimation(Player p, Block block, int status) {
		this.player = p;
		this.block = block.getLocation();
		this.animationStatus = status;
	}
    public SetBlockAnimation(Player p, Location blocklocation, int status) {
		this.player = p;
		this.block = blocklocation;
		this.animationStatus = status;
	}
    
    public void sendPacket() {
    	PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(this.block, animationStatus);
		nms.sendPacket(player, animation.getPacket());
    }
	

	
	public static void setBlockAnimationForPlayer(Player p, Block block, int status) {
		PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(block.getLocation(), status);
		animation.setBlockAnimation(status);
		nms.sendPacket(p, animation.getPacket());
	}
	public static void setBlockAnimationForPlayer(Player p, Location blocklocation, int status) {
		PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(blocklocation, status);
		animation.setBlockAnimation(status);
		nms.sendPacket(p, animation.getPacket());
		
	}
	public static void setBlockAnimationForAllPlayers(Location block, int status) {
		PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(block, status);
		animation.setBlockAnimation(status);
		for(Player p : Bukkit.getOnlinePlayers()) {
		nms.sendPacket(p, animation.getPacket());
		}
	}
	public static void setBlockAnimationForAllPlayers(Block block, int status) {
		PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(block.getLocation(), status);
		animation.setBlockAnimation(status);
		for(Player p : Bukkit.getOnlinePlayers()) {
			nms.sendPacket(p, animation.getPacket());
			}
	}

}
