package snapje.packetapi.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;

public class PacketPlayOutSpawnEntityLiving {

	/*
	 * Class created by Snapje
	 * Any errors mail: davidoutdeveloper@gmail.com
	 */
	
	//net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving pac = new net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving(entityliving);
	//net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving packet = new net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving();
	//DataWatcher dw = new DataWatcher(null);
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	private Reflection reflection = new Reflection();
	private int entityID;
	private int entityType;
	private int locX;
	private int locY;
	private int locZ;
	
	private int f;
	private int g;
	private int h;
	
	private byte locYaw;
	private byte locPitch;
	private byte k;
	
	private Object dataWatcher;
	private List m;
	
	public PacketPlayOutSpawnEntityLiving(Entity et, EntityType type) {
		this.entityID = et.getEntityId();
		this.setEntityType(type.getTypeId());
		this.locX = getMathHelper(et.getLocation().getX());
		this.locY = getMathHelper(et.getLocation().getY());
		this.locZ = getMathHelper(et.getLocation().getZ());
		this.locYaw = (byte)((int) (et.getLocation().getYaw() * 256.0F / 360.0F));
		this.locPitch = (byte)((int) (et.getLocation().getPitch() * 256.0F / 360.0F));
		this.dataWatcher = getnewDataWatcher(6, 20f);
	}
	
	public Object getPacket() {
		Object packet = null;
		Constructor<?> packetConstructor;
		
		
		
		
		try {
			packetConstructor = nms.getNMSClass("PacketPlayOutSpawnEntityLiving").getConstructor();
			packet = packetConstructor.newInstance();
		} catch (InstantiationException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		}  catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		}
		
		try {
			reflection.setField(packet, "a", this.entityID);
			reflection.setField(packet, "b", this.entityType);
			reflection.setField(packet, "c", this.locX);
			reflection.setField(packet, "d", this.locY);
			reflection.setField(packet, "e", this.locZ);
			reflection.setField(packet, "i", this.locYaw);
			reflection.setField(packet, "j", this.locYaw);
			reflection.setField(packet, "l", this.dataWatcher);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return packet;
	}
	
	private int getMathHelper(double loc) {
		int returned = 0;
		Method method;
	
		try {
			method = nms.getNMSClass("MathHelper").getDeclaredMethod("floor", Double.class);
			returned = (int) method.invoke(null, (loc * 32.0F));
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		}
		
		
		return returned;
	}
	
	
	private Object getnewDataWatcher(int i, float number) {
		
		
		Constructor<?> dwConstructor;
		Object datawatcher = null;
		try {
			dwConstructor = nms.getNMSClass("DataWatcher").getConstructor(Entity.class);
			datawatcher = dwConstructor.newInstance(null);
			
			Field a = datawatcher.getClass().getDeclaredField("a");
			a.setAccessible(true);
			a.set(datawatcher, i);
			
			Field b = datawatcher.getClass().getDeclaredField("b");
			b.setAccessible(true);
			b.set(datawatcher, number);
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (InstantiationException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (NoSuchFieldException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		}
		
		return datawatcher;
	}
	
	public int getEntityID() {
		return entityID;
	}

	public int getEntityType() {
		return entityType;
	}

	public void setEntityType(int entityType) {
		this.entityType = entityType;
	}

	public int getX() {
		return locX;
	}

	public int getY() {
		return locY;
	}

	public int getZ() {
		return locZ;
	}

	public byte getYaw() {
		return locYaw;
	}

	public byte getPitch() {
		return locPitch;
	}
	
	
	
	
}
