package snapje.packetapi.Core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import snapje.packetapi.Events.test;
import snapje.packetapi.Functions.ActionBar;
import snapje.packetapi.Functions.Corpse;
import snapje.packetapi.Functions.Functions;
import snapje.packetapi.Functions.SetBlockAnimation;
import snapje.packetapi.Functions.Tablist;
import snapje.packetapi.Functions.Title;
import snapje.packetapi.Packets.PacketPlayOutBlockBreakAnimation;
import snapje.packetapi.Packets.PacketPlayOutChat;
import snapje.packetapi.Packets.PacketPlayOutEntityDestroy;
import snapje.packetapi.Packets.PacketPlayOutEntityEquipment;
import snapje.packetapi.Packets.PacketPlayOutNamedEntitySpawn;
import snapje.packetapi.Packets.PacketPlayOutPlayerListHeaderFooter;
import snapje.packetapi.Packets.PacketPlayOutTitle;

public class PacketAPI extends JavaPlugin {
	
	/*
	 * Functions
	 */
	public Functions functions = new Functions();
	public ActionBar actionbar;
	public SetBlockAnimation animation;
	public Tablist tablist;
	public Title title;
	
	/*
	 * Packets
	 */
	public PacketPlayOutBlockBreakAnimation PacketPlayOutBlockBreakAnimation;
	public PacketPlayOutChat PacketPlayOutChat;
	public PacketPlayOutEntityDestroy PacketPlayOutEntityDestroy;
	public PacketPlayOutEntityEquipment PacketPlayOutEntityEquipment;
	public PacketPlayOutNamedEntitySpawn PacketPlayOutNamedEntitySpawn;
	public PacketPlayOutPlayerListHeaderFooter PacketPlayOutPlayerListHeaderFooter;
	public snapje.packetapi.Packets.PacketPlayOutSpawnEntityLiving PacketPlayOutSpawnEntityLiving;
	public PacketPlayOutTitle PacketPlayOutTitle;
	//public PacketPlayOutWorldParticles PacketPlayOutWorldParticles;
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new test(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
}