package snapje.packetapi.Functions;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import snapje.packetapi.Core.PacketAPI;
import snapje.packetapi.Packets.PacketPlayOutPlayerListHeaderFooter;
import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Updater;

public class Tablist {
	
	private static HashMap<Player, Tablist> tablist = new HashMap<Player, Tablist>();
	private NetMinecraftServer nms = new NetMinecraftServer();
	private ArrayList<String> headerList = new ArrayList<String>();
	private ArrayList<String> footerList = new ArrayList<String>();
	private String currentHeader;
	private String currentFooter;
	private Player player;
	private boolean animated = false;
	private int currentHeaderAnimation = 0;
	private int currentFooterAnimation = 0;
	private int delay = 100;
	private int update = 20;
	
	public Tablist(Player player, String header, String footer) {
		this.player = player;
		this.currentHeader = ChatColor.translateAlternateColorCodes('&', header);
		this.currentFooter = ChatColor.translateAlternateColorCodes('&', footer);
		tablist.put(player, this);
	}
	
	public Tablist(Player player, String header, String footer, boolean updated, int updateAfterSeconds) {
		this.player = player;
		headerList.add(ChatColor.translateAlternateColorCodes('&', header));
		footerList.add(ChatColor.translateAlternateColorCodes('&', footer));
		tablist.put(player, this);
		this.animated = updated;
		this.update = updateAfterSeconds * 20;
		
		if(animated == false) return;
		this.update = updateAfterSeconds * 20;
	}
	
	public Tablist(Player p, ArrayList<String> header, ArrayList<String> footer, boolean animated, int updateAfterSeconds) {
		this.player = p; 
		this.animated = animated;
		this.update = 20*updateAfterSeconds;
		tablist.put(player, this);
		
		if(header != null) {
		for(String line : header) {
			headerList.add(ChatColor.translateAlternateColorCodes('&', line));
		}
		}
		
		if(footer != null) {
			for(String line : footer) {
				footerList.add(ChatColor.translateAlternateColorCodes('&', line));
			}
			}
	}
	
	public void cancelUpdater() {
		this.animated = false;
	}
	public void enableUpdater() {
		this.animated = true;
		sendTabList();
	}
	
	public void setHeader(String string) {
		this.currentHeader = ChatColor.translateAlternateColorCodes('&', string);
	}
	public void setFooter(String string) {
		this.currentFooter = ChatColor.translateAlternateColorCodes('&', string);
	}
	
	
	public void sendTabList() {
		if(nms.getVersion() < 1.08) return;
		if(animated == true) {
			Updater updater = new Updater(PacketAPI.getPlugin(PacketAPI.class), update, delay) {
				
				
				@Override
				public void run() {
					
					if(animated == true) {
					
					if(currentHeaderAnimation >= headerList.size()) {
						currentHeaderAnimation++;
					} else {
						currentHeaderAnimation = 0;
					}
					
					if(currentFooterAnimation >= footerList.size()) {
						currentFooterAnimation++;
					} else {
						currentFooterAnimation = 0;
					}
					
					PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(headerList.get(currentHeaderAnimation), currentFooter);
					nms.sendPacket(player, packet.getPacket());
					
					} else {
						cancelTask();
					}
					
				}
			};
		} else {
			PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(currentHeader, currentFooter);
			nms.sendPacket(player, packet.getPacket());
		}
	}
	
	public static Tablist getTablist(Player p) {
		return tablist.get(p);
	}
}
