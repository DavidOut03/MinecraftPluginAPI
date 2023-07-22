package snapje.packetapi.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


import snapje.packetapi.Parameters.PacketPlayer;
import snapje.packetapi.Utils.Chat;
import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;

public class PacketPlayOutNamedEntitySpawn {

	//PacketPlayOutNamedEntitySpawn packet = new net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn();
	//CraftPlayer
	//GameProfile

	private NetMinecraftServer nms = new NetMinecraftServer();
	private Reflection reflection = new Reflection();


	private Object entityHuman;
	private String new_name = null;
	private String original_name;
	private Player player;
	
	private int entityID;
	private UUID entityUUID;
	private int locationX;
	private int locationY;
	private int locationZ;
	private byte locationYaw;
	private byte locationPitch;
	private ItemStack itemInHand;
	private Object datawatcher;
	
	public PacketPlayOutNamedEntitySpawn(Player p) {
		if(nms.getVersion() > 1.07)  {
		this.player = p;
		if(PacketPlayer.getEntityHuman(p) == null)  {p.sendMessage(Chat.format("&cEntityHuman == null"));return;}
		this.entityHuman = PacketPlayer.getEntityHuman(p);
		
		this.entityID = p.getEntityId();
		this.entityUUID = p.getUniqueId();
		this.locationX = p.getLocation().getBlockX();
		this.locationY = p.getLocation().getBlockY();
		this.locationZ = p.getLocation().getBlockZ();
		this.locationYaw = (byte) p.getLocation().getYaw();
		this.locationPitch = (byte) p.getLocation().getPitch();
		}
	}
	
	public PacketPlayOutNamedEntitySpawn(int entityID, UUID entityUUID, int locationX, int locationY, int locationZ, byte locationYaw, byte locationPitch, ItemStack item) {
		if(entityID != 0) { this.entityID = entityID;}
		if(entityUUID != null) { this.entityUUID = entityUUID;}
		if(locationX == 0 && locationZ == 0) {} else {this.locationX = locationX;}
		if(locationY == 0 && locationX == 0) {} else {this.locationY = locationY;}
		if(locationX == 0 && locationZ == 0) {} else {this.locationZ = locationZ;}
		
		if(locationYaw != 0) {this.locationYaw = locationYaw;}
		if(locationPitch != 0) {this.locationPitch = locationPitch;}
		if(item != null) {this.itemInHand = item;}
	}
	
	
	public PacketPlayOutNamedEntitySpawn(Player p, String name) {
		this.player = p;
		if(PacketPlayer.getEntityHuman(p) == null)  {p.sendMessage(Chat.format("&cEntityHuman == null"));return;}
		this.entityHuman = PacketPlayer.getEntityHuman(p);
		if(name != null && name.length() <= 16) {
		this.original_name = p.getName();
		this.new_name = ChatColor.translateAlternateColorCodes('&', name);
		} else {
			Bukkit.broadcastMessage("name to long");
		}
	}
	
	public Object getPacket() {
		Object packet = null;
		Constructor<?> constructor = null;
		
		if(PacketPlayer.getCraftPlayer(this.player) == null && PacketPlayer.getEntityHuman(this.player) == null) return null;
		
		setName();
		
		try {
			constructor = reflection.getConstructor("PacketPlayOutNamedEntitySpawn", nms.getNMSClass("EntityHuman"));
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		}
		
		if(constructor == null) return null;
		
		try {
			packet = constructor.newInstance(this.entityHuman);
			
			if(nms.getVersion() == 1.08) {
				
				if(entityID != 0) { setField(packet, "a", entityID);}
				if(entityUUID != null) {setField(packet, "b", entityUUID);}
				if(locationX == 0 && locationZ == 0) {} else {setField(packet, "c", locationX);}
				if(locationY == 0 && locationX == 0) {} else {setField(packet, "d", locationY);}
				if(locationX == 0 && locationZ == 0) {} else {setField(packet, "e", locationZ);}
				
				if(locationYaw != 0) {setField(packet, "f", locationYaw);}
				if(locationPitch != 0) {setField(packet, "g", locationPitch);}
				if(itemInHand != null) {setField(packet, "h", this.itemInHand.getTypeId());}
			}
		} catch (InstantiationException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		}

		if(packet == null) {
			player.sendMessage("could not create packet");
		}
		
		return packet;
	}
	
	
	public void sendPacketToAllPlayers() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p != player) {
				nms.sendPacket(p, getPacket());
			}
		}
	}
	
	
	
	
	private void setName() {
		if(PacketPlayer.getGameProfile(this.player) == null) 	player.sendMessage("profile not found.");
		try {
			Object gameprofile = PacketPlayer.getGameProfile(this.player);
			Field f = gameprofile.getClass().getDeclaredField("name");
			f.setAccessible(true);
			f.set(gameprofile, this.new_name);
		} catch (NoSuchFieldException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		}
	}
	
	private void setField(Object clas, String field, Object object) {
		Field f;
		try {
			f = clas.getClass().getField(field);
			f.setAccessible(true);
			f.set(clas, object);
		} catch (NoSuchFieldException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		}
		
	}
	
	


}
