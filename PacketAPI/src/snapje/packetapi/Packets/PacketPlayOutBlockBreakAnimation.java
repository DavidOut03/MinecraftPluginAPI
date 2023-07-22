package snapje.packetapi.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import snapje.packetapi.Utils.Error;
import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;

public class PacketPlayOutBlockBreakAnimation {
	
	//net.minecraft.server.v1_7_R4.PacketPlayOutBlockBreakAnimation animation = new net.minecraft.server.v1_7_R4.PacketPlayOutBlockBreakAnimation(blockID, x, y, z, status)
	//net.minecraft.server.v1_8_R3.PacketPlayOutBlockBreakAnimation animation = new net.minecraft.server.v1_8_R3.PacketPlayOutBlockBreakAnimation(blockID, blockposition, status);
	//PacketPlayOutBlockBreakAnimation animation = new net.minecraft.server.v1_10_R1.PacketPlayOutBlockBreakAnimation(blockID, blockposition, status);
	
	/*
	 * Class created by Snapje
	 * Any errors mail: davidoutdeveloper@gmail.com
	 */
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	private Reflection reflection = new Reflection();
	private int id;
	private Block block;
	private Location location;
	private int blockAnimationStatus;
	
	
	public PacketPlayOutBlockBreakAnimation(Location loc, int status) {
		Random random = new Random();
		
		this.blockAnimationStatus = status;
		this.block = loc.getBlock();
		this.location = loc;
		this.id = random.nextInt(100000);
	}
	
	public int getBlockAnimationStatus() {
		return blockAnimationStatus;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public int getBlockID() {
		return id;
	}
	
	public void setBlockAnimation(int status) {
		this.blockAnimationStatus = status;
	}
	

	public Object getPacket() {
		
		
		Object packet = null;
		
		try {
			if(nms.getVersion() == 1.07) {
				Constructor<?> constructor = nms.getNMSClass("PacketPlayOutBlockBreakAnimation").getConstructor(int.class, int.class , int.class, int.class, int.class);
				packet = constructor.newInstance(id, location.getBlockX(), location.getBlockY(), location.getBlockZ(), blockAnimationStatus);
			} else {
			 Constructor<?> blockPositonConstructor = nms.getNMSClass("BlockPosition").getConstructor(int.class, int.class, int.class);
			 Object blockPosition = blockPositonConstructor.newInstance(location.getBlockX(), location.getBlockY(), location.getBlockZ());
			 
			 Constructor<?> constructor = nms.getNMSClass("PacketPlayOutBlockBreakAnimation").getConstructor(int.class, nms.getNMSClass("BlockPosition"), int.class);
			 packet = constructor.newInstance(id, blockPosition, blockAnimationStatus);
			}
			
		} catch (NoSuchMethodException e) {
			Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (InstantiationException e) {
			Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalAccessException e) {
			Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalArgumentException e) {
			Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (InvocationTargetException e) {
			Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		}
		
		
		return packet;
	}
	
	public void sendPacketToAllPlayers() {
		Object packet = getPacket();
		
		if(packet == null) return;
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			nms.sendPacket(p, packet);
		}
	}
	
	
	
	

/*
	
	public void breakBlock(Location block, int status) {
		
		Object packet = buildPacket(block, status);
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			reflection.sendPacket(player, packet);
		}
		
		
	}
	
     private Object buildPacket(Location block, int status) {
    	 Object object = null;
    	 try {
    		 Constructor<?> blockPositonConstructor = reflection.getNMSClass("BlockPosition").getConstructor(int.class, int.class, int.class);
			 Object blockPosition = blockPositonConstructor.newInstance(block.getBlockX(), block.getBlockY(), block.getBlockZ());
			 
			 Constructor<?> constructor = reflection.getNMSClass("PacketPlayOutBlockBreakAnimation").getConstructor(int.class, reflection.getNMSClass("BlockPosition"), int.class);
			 
			 Random random = new Random();
			 int id = random.nextInt(100000);
			 object = constructor.newInstance(id, blockPosition, status);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException| InvocationTargetException | NoSuchMethodException | SecurityException e) { e.printStackTrace();}
    	 
    	 return object;
	}
	*/
	

}
