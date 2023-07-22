package snapje.packetapi.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import snapje.packetapi.Utils.NetMinecraftServer;

public class PacketPlayOutRelEntityMove {
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	
	private int entityID;
	private byte locationX;
	private byte locationY;
	private byte locationZ;
	private boolean onGround;
	
	public PacketPlayOutRelEntityMove(int entityID, byte locX, byte locY, byte locZ, boolean onGround) {
		this.entityID = entityID;
		this.locationX = locX;
		this.locationY = locY;
		this.locationZ = locZ;
		this.onGround = onGround;
	}
	
	public Object getPacket() {
		Object packet = null;
		
		try {
			Constructor<?> packetConstructor = nms.getNMSClass("PacketPlayOutRelEntityMove").getConstructor(int.class, byte.class, byte.class, byte.class, boolean.class);
			packet = packetConstructor.newInstance(entityID, locationX, locationY, locationZ, onGround);
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (InstantiationException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
			e.printStackTrace();
		}
		
		
		return packet;
	}

}
