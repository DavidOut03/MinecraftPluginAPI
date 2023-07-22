package snapje.packetapi.Parameters;

import snapje.packetapi.Utils.NetMinecraftServer;

public class EnumParticle {
	
	//net.minecraft.server.v1_8_R3.EnumParticle
	
	/*
	 * Class created by Snapje
	 * Any errors mail: davidoutdeveloper@gmail.com
	 */
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	private String particleType;
	
	private String getParticleType() {
		return this.particleType;
	}
	
	public EnumParticle(String message) {
		this.particleType = message;
	}
		
	public Object getEnumParticle() {
		Object enumParticle = null;
		
		try {
			enumParticle = nms.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField(particleType).get(null);
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (NoSuchFieldException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		}
		
		return enumParticle;
	}
	
	private Object valueOf() {
		
		Object valueOf = null;
		
		if(getEnumParticle() != null) {
			valueOf = getEnumParticle();
		}
		
		return valueOf;
	}
	
	
}
