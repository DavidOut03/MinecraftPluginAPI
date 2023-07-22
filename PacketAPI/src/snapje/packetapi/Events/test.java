package snapje.packetapi.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import snapje.packetapi.Functions.Corpse;
import snapje.packetapi.Functions.Functions;
import snapje.packetapi.Parameters.ItemSlotType;
import snapje.packetapi.Utils.NetMinecraftServer;


public class test implements Listener {
	
	/*
	NetMinecraftServer nms = new NetMinecraftServer();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Functions.setPlayersTabList(e.getPlayer(), "&cHeader", "&cFooter");
		Functions.sendActionBarToPlayer(e.getPlayer(), "&aWelcome");
	}
	*/

	
	
	
	/*
	
	
	@EventHandler
	public void PacketPlayOutBlockBreakAnimation(PlayerInteractEvent e) {
		
		if(e.getClickedBlock() != null) {
		if(e.getClickedBlock().getType() == Material.BEDROCK) {
			snapje.packetapi.Packets.PacketPlayOutBlockBreakAnimation packet = new snapje.packetapi.Packets.PacketPlayOutBlockBreakAnimation(e.getClickedBlock().getLocation(), 7);
			nms.sendPacket(e.getPlayer(), packet.getPacket());
		}
		}
		
	}
	
	@EventHandler
	public void PacketPlayOutBlockChange(PlayerMoveEvent e) {
		
		if(e.getTo() != e.getFrom()) {
		if(e.getTo().getBlockX() != e.getFrom().getBlockX() || e.getTo().getBlockZ() != e.getFrom().getBlockZ()) {
		Block b = e.getTo().clone().add(0, -1, 0).getBlock();
		if(b.getType() == Material.BEDROCK) {
			snapje.packetapi.Packets.PacketPlayOutBlockChange packet = new snapje.packetapi.Packets.PacketPlayOutBlockChange(b.getLocation(), Material.OBSIDIAN);
			if(packet.getPacket() != null) {
		nms.sendPacket(e.getPlayer(), packet.getPacket());
			} 
		}
		}
		}
		
	}
	
	@EventHandler
	public void PacketPlayOutChat(BlockPlaceEvent e) {
		
		if(e.getBlock().getType() == Material.TNT) {

			snapje.packetapi.Packets.PacketPlayOutChat packet = new snapje.packetapi.Packets.PacketPlayOutChat("&cYou cannot place tnt here.");
			if(packet.getPacket() != null) {
			nms.sendPacket(e.getPlayer(), packet.getPacket());
			}
		}
	}
	
	@EventHandler
	public void PacketPlayOutEntityEquipment(EntityDamageEvent e) {
		
		if(e.getEntity().getType() == EntityType.PLAYER || e.getEntity().getType() == EntityType.ZOMBIE) {
		
			snapje.packetapi.Packets.PacketPlayOutEntityEquipment packet = new snapje.packetapi.Packets.PacketPlayOutEntityEquipment(e.getEntity(), new ItemStack(Material.DIAMOND_CHESTPLATE), ItemSlotType.CHESTPLATE);

			for(Player p : Bukkit.getOnlinePlayers()) {
				nms.sendPacket(p, packet.getPacket());
				
			}
			
		}
		
	}
	
	
	@EventHandler
	public void PacketPlayOutPlayerListHeaderFooter(BlockBreakEvent e) {
		
		snapje.packetapi.Packets.PacketPlayOutPlayerListHeaderFooter packet = new snapje.packetapi.Packets.PacketPlayOutPlayerListHeaderFooter("&6MaterialType:", "&e" + e.getBlock().getType());
		nms.sendPacket(e.getPlayer(), packet.getPacket());
	}
	
	
	@EventHandler
	public void PacketPlayOutTitle(BlockPlaceEvent e) {
		if(e.getBlock().getType() == Material.TNT) {
			snapje.packetapi.Packets.PacketPlayOutTitle packet = new snapje.packetapi.Packets.PacketPlayOutTitle("&cYou cannot place tnt here.", "sorry for this", 10, 20, 10);
			if(packet.getTitlePacket() != null) {
			nms.sendPacket(e.getPlayer(), packet.getTitlePacket());
			if(packet.getSubTitlePacket() != null) {
			nms.sendPacket(e.getPlayer(), packet.getSubTitlePacket());
			}
			}
		}
	}
	
	@EventHandler
	public void PacketPlayOutWorldParticles(PlayerInteractEvent e) {
		
		if(e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.OBSIDIAN) {
			snapje.packetapi.Packets.PacketPlayOutWorldParticles packet = new snapje.packetapi.Packets.PacketPlayOutWorldParticles("FLAME", e.getClickedBlock().getLocation(), 0.2f, 0.2f, 0.2f, 10f, 100);
			nms.sendPacket(e.getPlayer(), packet.getPacket());
		}
	}
	*/
	
	
	


}
