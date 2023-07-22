package snapje.packetapi.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;

public class PacketPlayOutBlockChange {

	/*
	 * Compatible versions
	 * 
	 * //net.minecraft.server.v1_7_R4.PacketPlayOutBlockChange packet = new net.minecraft.server.v1_7_R4.PacketPlayOutBlockChange(x, y, z, World);
	 * //net.minecraft.server.v1_8_R3.PacketPlayOutBlockChange block = new net.minecraft.server.v1_8_R3.PacketPlayOutBlockChange(World, BlockPosition);
	 *
	 */
	
	//net.minecraft.server.v1_10_R1.PacketPlayOutBlockChange packet = new net.minecraft.server.v1_10_R1.PacketPlayOutBlockChange(World, BlockPosition);
	
	
	//CraftWorld wo
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	private Reflection reflection = new Reflection();
	
	private Location location;
	private Material newMaterial;
	private int newMaterialData = 0;
	private Material oldMaterial;
	
	public PacketPlayOutBlockChange(Location loc, Material newMaterialType) {
		this.location = loc;
		this.oldMaterial = loc.getBlock().getType();
		this.newMaterial = newMaterialType;
	}
	
	public PacketPlayOutBlockChange(Block b, Material newMaterialType) {
		this.location = b.getLocation();
		this.oldMaterial = b.getType();
		this.newMaterial = newMaterialType;
	}
	
	public PacketPlayOutBlockChange(Location loc, Material newMaterialType, int data) { // this is for 1.7
		Player p = Bukkit.getPlayer("Snapje");
		this.location = loc;
		this.oldMaterial = loc.getBlock().getType();
		this.newMaterial = newMaterialType;
		this.newMaterialData = data;
	}
	
	public PacketPlayOutBlockChange(Block b, Material newMaterialType, int data) { // this is meant for 1.7
		this.location = b.getLocation();
		this.oldMaterial = b.getType();
		this.newMaterial = newMaterialType;
		this.newMaterialData = data;
	}
	
	
	public Material getOldMaterial() {
		return this.oldMaterial;
	}
	
	public void setNewMaterial(Material mat) {
		this.newMaterial = mat;
	}
	public Material getNewMaterial() {
		return this.newMaterial;
	}
	
	public Object getPacket() {
		Object packet = null;
		
		
		if(getWorldData() != null) {
		
			Constructor<?> packetConstructor;
			try {
				if(nms.getVersion() == 1.07) {
					
					packetConstructor = reflection.getConstructor("PacketPlayOutBlockChange", int.class, int.class, int.class , nms.getNMSClass("World"));
					packet = packetConstructor.newInstance(location.getBlockX(), location.getBlockY(), location.getBlockZ(), getWorldData());
					
					Field block = packet.getClass().getField("block");
					block.setAccessible(true);
					block.set(packet, this.getBlockByMaterialID(newMaterial.getId()));
					
					if(this.newMaterialData != 0) {
					Field data = packet.getClass().getField("data");
					data.setAccessible(true);
					data.set(packet, this.getBlockByMaterialID(this.newMaterialData));
					}
				} else {
					if(getBlockPosition() != null) {
						if(getIBlockData() != null) {
				packetConstructor = reflection.getConstructor("PacketPlayOutBlockChange", nms.getNMSClass("World"), getBlockPosition().getClass());
				packet = packetConstructor.newInstance(getWorldData(), getBlockPosition());
				Field field = packet.getClass().getField("block");
				field.setAccessible(true);
				field.set(packet, getIBlockData());
						} else {
							 snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), "NoSuchObjectException", "Could not create BlockPosition class.");
						}
					} else {
						 snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), "NoSuchObjectException", "Could not create BlockPosition class.");
					}
				
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
			} catch (NoSuchFieldException e) {
				snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
				e.printStackTrace();
			}
			
		} else {
			 snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), "NoSuchObjectException", "Could not create WorldServer class.");
		}
		
		  
		
		return packet;
	}
	
	private Object getIBlockData() {
		Object returned = null;
		
		try {
			returned = nms.getNMSClass("Block").getDeclaredMethod("getByCombinedId", int.class).invoke(null, getNewMaterial().getId() + (this.newMaterialData << 12));
		} catch (IllegalAccessException e) {
			 snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			 snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			 snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			 snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (SecurityException e) {
			 snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		}
		
		return returned;
	}
	
	private Object getWorldData() {
		Object worlddata = null;
		
		 //WorldServer world = ((CraftWorld)location.getWorld()).getHandle();
		 try {
			worlddata = location.getWorld().getClass().getMethod("getHandle").invoke(location.getWorld());
		} catch (IllegalAccessException e) {
			 snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		}
		
		return worlddata;
	}
	
	private Object getBlockPosition() {
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
	
	public Object getBlockByMaterialID(int id) {
		Object block = null;
		if(nms.getVersion() == 1.07) {
		try {
			block = nms.getNMSClass("Block").getDeclaredMethod("getById", int.class).invoke(null, id);
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		}
		
		}
		return block;
	}
}
