package snapje.packetapi.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import snapje.packetapi.Parameters.ItemSlotType;
import snapje.packetapi.Utils.Chat;
import snapje.packetapi.Utils.Error;
import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;

public class PacketPlayOutEntityEquipment {
	
	//net.minecraft.server.v1_7_R4.PacketPlayOutEntityEquipment packet = new net.minecraft.server.v1_7_R4.PacketPlayOutEntityEquipment(int i, int j, itemstack);
	//net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment packet = new net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment(entityID, itemSlotType, itemstack);
	//net.minecraft.server.v1_9_R1.PacketPlayOutEntityEquipment packet = new net.minecraft.server.v1_9_R1.PacketPlayOutEntityEquipment(id, slot, itemstack);
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	private Reflection reflection = new Reflection();
	
	private int entityID;
	private ItemSlotType itemSlotType;
	private ItemStack item;
	private Player player;
	
	public PacketPlayOutEntityEquipment(Entity et, ItemStack item, ItemSlotType type) {
		this.entityID = et.getEntityId();
		if(item != null) {
		this.item = item;
		} else {
			this.item = new ItemStack(Material.AIR);
		}
		this.itemSlotType = type;
	}
	
	public PacketPlayOutEntityEquipment(Player p, ItemStack item, ItemSlotType type) {
		this.entityID = p.getEntityId();
		if(item != null) {
			this.item = item;
			} else {
				this.item = new ItemStack(Material.AIR);
			}
		this.itemSlotType = type;
		this.player = p;
	}
	
	public Object getPacket() {
		Object packet = null;
		
		try {
			if(nms.getVersion() >= 1.09) {
				if(ItemSlotType.getItemStotType(this.itemSlotType) != null) {
				Constructor<?> constructor = reflection.getConstructor("PacketPlayOutEntityEquipment", int.class, nms.getNMSClass("EnumItemSlot"), nms.getNMSClass("ItemStack"));
				if(item != null || item.getType() != Material.AIR) {
				packet = constructor.newInstance(this.entityID, ItemSlotType.getItemStotType(this.itemSlotType), getItem());
				} else {
					packet = constructor.newInstance(this.entityID, ItemSlotType.getSlotNumber(this.itemSlotType), null);
				}
				}
			} else {
			Constructor<?> constructor = reflection.getConstructor("PacketPlayOutEntityEquipment", int.class, int.class, nms.getNMSClass("ItemStack"));
			if(item != null || item.getType() != Material.AIR) {
			packet = constructor.newInstance(this.entityID, ItemSlotType.getSlotNumber(this.itemSlotType), getItem());
			} else {
				packet = constructor.newInstance(this.entityID, ItemSlotType.getSlotNumber(this.itemSlotType), null);
			}
			}
		} catch (NoSuchMethodException e) {
			Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (SecurityException e) {
			Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (InstantiationException e) {
			Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		}
		
		
		
		
		return packet;
	}
	
	
	 public Object getItem() {
		 if(snapje.packetapi.Parameters.CraftItemStack.asNMSCopy(item) != null) {
	        return snapje.packetapi.Parameters.CraftItemStack.asNMSCopy(item);
		 } else {
			 return null;
		 }
	    }
	
	
	public void sendPacketToAllPlayers(boolean sendPacketToPlayer) {
		if(getPacket() != null) {
			Object packet = getPacket();
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(player != null && p.equals(player) && sendPacketToPlayer == true) {
				nms.sendPacket(p, packet);
			} else {
				if(player != null && p.equals(player) && sendPacketToPlayer == false) {
					return;
				} else {
					nms.sendPacket(p, packet);
				}
			}
		}
		} else {
			Bukkit.getConsoleSender().sendMessage(Chat.format("&cPacket couldn't be created."));
		}
	}
	

	
	

}


 