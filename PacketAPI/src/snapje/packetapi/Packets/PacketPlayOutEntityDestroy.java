package snapje.packetapi.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;

public class PacketPlayOutEntityDestroy {
	
	/*
	 * Class created by Snapje
	 * Any errors mail: davidoutdeveloper@gmail.com
	 */
	
	//net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy packet = new net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy(ai);

	private NetMinecraftServer nms = new NetMinecraftServer();
	private Reflection reflection = new Reflection();
	private int entityID[];
	


	public PacketPlayOutEntityDestroy(int id) {
		this.entityID = new int[] {id};
	}
	
	
	public Object getPacket() {
		Object packet = null;;
		
		try {
			 Constructor<?> dieConstructor = nms.getNMSClass("PacketPlayOutEntityDestroy").getConstructor(int.class);
			 packet = dieConstructor.newInstance(this.entityID);
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
		}
		
		
		return packet;
	}
	
}
