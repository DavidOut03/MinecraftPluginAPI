package snapje.packetapi.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import snapje.packetapi.Parameters.PacketPlayer;
import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;

public class PacketPlayOutBed {
	
	/*
	 *  tested versions:
	 *  net.minecraft.server.v1_8_R3.PacketPlayOutBed packet = new Packet(EntityHuman, BlockPosition);
	 */
	
	private Reflection reflection = new Reflection();
	private NetMinecraftServer nms = new NetMinecraftServer();

	private Object blockPosition;
	private Player player;
	private Location location;
	
	public PacketPlayOutBed(Player player) {
		this.blockPosition = getBlockPosition(player.getLocation());
		this.player = player;
		this.location = player.getLocation();
		
	}
	
	public PacketPlayOutBed(Player player, Location location) {
		this.blockPosition = getBlockPosition(player.getLocation());
		this.player = player;
		this.location = location;
	}
	
	public Object getPacket() {
		Object packet = null;
		
		if(blockPosition != null) {
			try {
				if(nms.getVersion() < 1.08) {
					Constructor<?> packetConstructor = reflection.getConstructor("PacketPlayOutBed", nms.getNMSClass("EntityHuman"), int.class, int.class, int.class);
					packet = packetConstructor.newInstance(PacketPlayer.getEntityHuman(player), location.getBlockX(), location.getBlockY(), location.getBlockZ());
				} else {
				Constructor<?> packetConstructor = reflection.getConstructor("PacketPlayOutBed", nms.getNMSClass("EntityHuman"), getBlockPosition(player.getLocation()).getClass());
				packet = packetConstructor.newInstance(PacketPlayer.getEntityHuman(player), getBlockPosition(player.getLocation()));
				}
			} catch (NoSuchMethodException e) {
				snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
				e.printStackTrace();
			} catch (SecurityException e) {
				snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
				e.printStackTrace();
			} catch (InstantiationException e) {
				snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
				e.printStackTrace();
			}
			
		}
		
		return packet;
	}
	
	
	private Object getBlockPosition(Location location) {
		Object blockPosition = null;
		

		try {
			Constructor<?> blockPositionConstructor = reflection.getConstructor("BlockPosition", int.class, int.class, int.class);
			blockPosition = blockPositionConstructor.newInstance(location.getBlockX(), location.getBlockY(), location.getBlockZ());
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (InstantiationException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		}
		
		
		return blockPosition;
	}
	
	

}
