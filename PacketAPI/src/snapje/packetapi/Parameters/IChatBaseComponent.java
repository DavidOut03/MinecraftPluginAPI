package snapje.packetapi.Parameters;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.ChatColor;

import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;

public class IChatBaseComponent {
	
	//net.minecraft.server.v1_8_R3.IChatBaseComponent
	
	/*
	 * Class created by Snapje
	 * Any errors mail: davidoutdeveloper@gmail.com
	 */
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	private Reflection reflection = new Reflection();
	private String message;
	
	private String getMessage() {
		return this.message;
	}
	
	public IChatBaseComponent(String message) {
		this.message = message;
	}
	
	public String getText() {
		return this.message;
	}
	
	
	public Object getCompletedIChatBaseComponent() {
		Object component = null;
		
		try {
			component = reflection.getDeclaredClasses(nms.getNMSClass("IChatBaseComponent"))[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', getMessage()) + "\"}");
			//component = reflection.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', getMessage()) + "\"}");
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		}
		
		return component;
		
	}

}
