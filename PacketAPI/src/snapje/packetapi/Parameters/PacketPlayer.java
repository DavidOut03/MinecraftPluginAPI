package snapje.packetapi.Parameters;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

public class PacketPlayer {
	
	public static Object getEntityHuman(Player p) {
		if(getCraftPlayer(p) == null) {
			snapje.packetapi.Utils.Error.sendErrorMessage("PacketPlayer", "CraftPlayer not found");
			return null;
		} else {
			return getCraftPlayer(p);
		}
	}
	
	
	public static Object getCraftPlayer(Player player) {
		Object cp = null;
		try {
		     cp = player.getClass().getMethod("getHandle").invoke(player);
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("PacketPlayer", e.getClass().getName());
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("PacketPlayer", e.getClass().getName());
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("PacketPlayer", e.getClass().getName());
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("PacketPlayer", e.getClass().getName());
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("PacketPlayer", e.getClass().getName());
		}
		
		
		return cp;
	}
	
	public static Object getGameProfile(Player player) {
		Object gp = null;
		
		try {
			Object craftplayer = player.getClass().getMethod("getHandle").invoke(player);
			 gp = craftplayer.getClass().getMethod("getProfile").invoke(craftplayer);
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("PacketPlayer", e.getClass().getName());
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("PacketPlayer", e.getClass().getName());
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("PacketPlayer", e.getClass().getName());
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("PacketPlayer", e.getClass().getName());
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("PacketPlayer", e.getClass().getName());
		}
		return gp;
	}

}
