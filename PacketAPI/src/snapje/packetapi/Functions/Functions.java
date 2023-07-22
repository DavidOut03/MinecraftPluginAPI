package snapje.packetapi.Functions;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import snapje.packetapi.Packets.PacketPlayOutEntityEquipment;
import snapje.packetapi.Parameters.ItemSlotType;
import snapje.packetapi.Utils.NetMinecraftServer;

public class Functions {
	
	public static void sendActionBarToPlayer(Player p, String message) {
		ActionBar bar = new ActionBar(p, message);
		bar.sendActionBar();
	}
	
	public static void setBlockAnimation(Player p, int status, Block b) {
		SetBlockAnimation animation = new SetBlockAnimation(p, b, status);
		animation.sendPacket();
	}
	
	public static void setPlayersTabList(Player p, String header, String footer) {
		Tablist tablist = new Tablist(p, header, footer);
		tablist.sendTabList();
	}
	
	public static void sendTitleToPlayer(Player p, String title, String subtitle, int fadeInTicks, int displayTicks, int fadeOutTicks) {
		Title titlePacket = null;
		if(subtitle == null) {
		 titlePacket = new Title(p, title, fadeInTicks, displayTicks, fadeOutTicks);
		} else {
		 titlePacket = new Title(p, title, subtitle, fadeInTicks, displayTicks, fadeOutTicks);
		}
		
		if(titlePacket != null) {
		titlePacket.sendTitle();
		}
	}
	
	public static void hidePlayersArmor(Player p) {
		NetMinecraftServer nms = new NetMinecraftServer();
		PacketPlayOutEntityEquipment helm = new PacketPlayOutEntityEquipment(p, null, ItemSlotType.HELMET);
		PacketPlayOutEntityEquipment chest = new PacketPlayOutEntityEquipment(p, null, ItemSlotType.CHESTPLATE);
		PacketPlayOutEntityEquipment pants = new PacketPlayOutEntityEquipment(p, null, ItemSlotType.LEGGINGS);
		PacketPlayOutEntityEquipment boots = new PacketPlayOutEntityEquipment(p, null, ItemSlotType.BOOTS);
		
		for(Player online : Bukkit.getOnlinePlayers()) {
			if(!online.equals(p)) {
				nms.sendPacket(online, helm.getPacket());
				nms.sendPacket(online, chest.getPacket());
				nms.sendPacket(online, pants.getPacket());
				nms.sendPacket(online, boots.getPacket());
			}
		}
	}

}
