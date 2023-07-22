package snapje.packetapi.Utils;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NetMinecraftServer {
	
	public Class<?> getNMSClass(String className) {
		try {
			return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]  + "." + className);
			
		} catch (Exception ex) {snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), ex.getClass().getName()); return null;}
	}
	
	public  Class<?> getBukkitClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("org.bukkit.craftbukkit." + version + "." + name);
        }
        catch (Exception e) {
        	snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
            return null;
        }
    }
	
	public Class<?> getOBSClass(String className, String packageName) {
		try {
			return Class.forName("org.bukkit.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]  + "." + packageName + "." + className);
			
		} catch (Exception ex) {snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), ex.getClass().getName(), "&cThe class {CLASS-NAME} cannot be found.".replace("{CLASS-NAME}", className)); return null;}
	}
	
	public double getVersion() {
		String string = Bukkit.getServer().getVersion();
		if(string != null) {
		if(string.contains("1.7") || string.contains("1_7")) {
			return 1.07;
		}  else if(string.contains("1.8") || string.contains("1_8")) {
			return 1.08;
		} else if(string.contains("1.9") || string.contains("1_9")) {
			return 1.09;
		} else if(string.contains("1.10") || string.contains("1_10")) {
			return 1.10;
		} else if(string.contains("1.11") || string.contains("1_11")) {
			return 1.11;
		} else if(string.contains("1.12") || string.contains("1_12")) {
			return 1.12;
		} else if(string.contains("1.13") || string.contains("1_13")) {
			return 1.13;
		} else if(string.contains("1.14") || string.contains("1_14")) {
			return 1.14;
		} else if(string.contains("1.15") || string.contains("1_15")) {
			return 1.15;
		} else if(string.contains("1.16") || string.contains("1_16")) {
			return 1.16;
		} else if(string.contains("1.17") || string.contains("1_17")) {
			return 1.17;
		} else if(string.contains("1.18") || string.contains("1_18")) {
			return 1.18;
		}
		}
		return 0;
	}
	
	
	public void sendPacket(Player p, Object packet) {
		if(packet != null) {
		try {
			Object craftplayer = p.getClass().getMethod("getHandle").invoke(p);
			Object connection = craftplayer.getClass().getField("playerConnection").get(craftplayer);
			connection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(connection, packet);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) { e.printStackTrace();}
		} else {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), "CannotSendPacketException", "Could not send packet because it does not exist.");
		}
	}

}
