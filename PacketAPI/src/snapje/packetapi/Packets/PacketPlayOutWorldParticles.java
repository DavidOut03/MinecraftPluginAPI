package snapje.packetapi.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import snapje.packetapi.Parameters.EnumParticle;
import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;



public class PacketPlayOutWorldParticles {
	
	// EnumParticle particleType, Location loc, float xOffset, float yOffset, float zOffset, float speed, int particles
	
	
	
	// PacketPlayOutWorldParticles particle = new PacketPlayOutWorldParticles(EnumParticle.class, boolean.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class);
	// PacketPlayOutWorldParticles particle = new PacketPlayOutWorldParticles(particleType,true,(float) loc.getX(),(float) loc.getY(),(float) loc.getZ(), xOffset, yOffset, zOffset, speed ,particles);
	
	
	// new PacketPlayOutWorldParticles(particle, true, (float) location.getX(),(float) location.getY(), (float) location.getZ(), offsetX, offsetY, offsetZ, speed, particleCount, extra);
	
	//net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles packet = new net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles(name, float x, float y, float z, float xOffSet, float yOffSet, float zOffSet, int amount);
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	private Reflection reflection = new Reflection();
	private Object particle;
	private boolean bool = true;
	private float locX;
	private float locY;
	private float locZ;
	private float xOffSet;
	private float yOffSet;
	private float zOffSet;
	private float speed;
	private int particleCount;
	private int extra[] = new int[0];
	private String particleName;
	private boolean enabled = false;
	
	public PacketPlayOutWorldParticles(String particle, Location loc, float xOffset, float yOffset, float zOffset, float speed, int particleCount) {
		if(enabled == true) {
		if(nms.getVersion() >= 1.08) {
		EnumParticle ep = new EnumParticle(particle);
		this.particle = ep.getEnumParticle() != null;
		} else {
			this.particleName = particle;
		}
		this.locX = (float) loc.getX();
		this.locY = (float) loc.getX();
		this.locZ = (float) loc.getX();
		this.xOffSet = xOffset;
		this.yOffSet = yOffset;
		this.zOffSet = zOffset;
		this.speed = speed;
		this.particleCount = particleCount;
		} else {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), "CannotSendPacketException", "Packet cannot be send yet.");
		}
	}
	
	public Object getPacket() {
		if(enabled == true) {
		Object packet = null;
		
		try {
			if(nms.getVersion() < 1.08) {
				Constructor<?> packetConstructor = nms.getNMSClass("PacketPlayOutWorldParticles").getConstructor(String.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class);
				packet = packetConstructor.newInstance(this.particleName, locX, locY, locZ, xOffSet, yOffSet, zOffSet, particleCount);
			} else {
				Constructor<?> packetConstructor = nms.getNMSClass("PacketPlayOutWorldParticles").getConstructor(nms.getNMSClass("EnumParticle"), boolean.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class, int.class);
				 packet = packetConstructor.newInstance(this.particle, this.bool, this.locX, this.locY, this.locZ, this.xOffSet, this.yOffSet, this.zOffSet, this.speed, this.particleCount, this.extra);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
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
		} else {
			return null;
		}
	}
	
	public void sendPacketToAllPlayers() {
		Object packet = getPacket();
		
		if(packet == null) return;
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			nms.sendPacket(p, packet);
		}
	}
	
	
	
/*
	
	@SuppressWarnings("unused")
	public void sendParticle(String ParticleName, Location loc, float xOffset, float yOffset, float zOffset, float speed, int particles) {
		Object packet = null;
		
		if(getParticleType(ParticleName) == null) return;
		Object particleType = getParticleType(ParticleName);
		
		/*try {
		
			Constructor<?> packetConstructor = nms.getNMSClass("PacketPlayOutWorldParticles").getConstructor(nms.getNMSClass("EnumParticle"), boolean.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class);
			 packet = packetConstructor.newInstance(particleType, true, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), xOffset, yOffset, zOffset, speed, particles);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {e.printStackTrace();} */
		
		/*
		Bukkit.getConsoleSender().sendMessage(particleType + "");
		Constructor<?> packetConstructor;
		try {
			Bukkit.broadcastMessage("1");
			packetConstructor = nms.getNMSClass("PacketPlayOutWorldParticles").getConstructor(nms.getNMSClass("EnumParticle"), boolean.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class, int.class);
			Bukkit.broadcastMessage("2");
			// packet = packetConstructor.newInstance(particleType, true, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), xOffset, yOffset, zOffset, speed, particles);
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException e1) {
			Bukkit.broadcastMessage("constructor");
			e1.printStackTrace();
		}
		
		int[] ai = {1, 2, 4};
		 packet = getPacket(ParticleName, true, (float) loc.getX(), (float) loc.getX(), (float) loc.getX(), xOffset, yOffset, zOffset, speed, particles);
		
		if(packet != null) {
		for(Player p : Bukkit.getOnlinePlayers()) {
		// PacketPlayOutWorldParticles pa = new PacketPlayOutWorldParticles(EnumParticle.valueOf(ParticleName), true, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), xOffset, yOffset, zOffset, speed, particles);
		nms.sendPacket(p, packet);
		}
		}
	}
	
	private Object getPacket(String enumparticle, boolean flag, float f1, float f2, float f3, float f4, float f5, float f6, float f7, int l) {
		// PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(enumparticle, flag, f1, f2, f3, f4, f5, f6, f7, l, ai);
		Object packet = null;
		try {
			Constructor<?> constructPacket = nms.getNMSClass("PacketPlayOutWorldParticles").getConstructor(nms.getNMSClass("EnumParticle").getClass(), boolean.class,
			float.class,
			float.class,
			float.class,
			float.class,
			float.class,
			float.class,
			float.class,
			int.class,
			int.class);
			
			packet = nms.getNMSClass("PacketPlayOutWorldParticles").getConstructor(String.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class).newInstance(enumparticle, f1, f2, f3, f4, f5, f6, f7, l);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return packet;
	}
	
	private Object getParticleType(String particleName) {
		Object particleType = null;
		try {
			particleType = nms.getNMSClass("EnumParticle").getField(particleName).get(null);
		} catch (IllegalAccessException | IllegalArgumentException | SecurityException | NoSuchFieldException e) {e.printStackTrace();}
		
		if(particleType == null) Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "[ERROR]" + ChatColor.RED + " Particle type not found."); return particleType;
	}
	*/
}
