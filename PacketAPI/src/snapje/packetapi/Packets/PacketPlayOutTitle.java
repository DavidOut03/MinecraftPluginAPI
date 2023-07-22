package snapje.packetapi.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import snapje.packetapi.Parameters.IChatBaseComponent;
import snapje.packetapi.Utils.Chat;
import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;

public class PacketPlayOutTitle {
	
	/*
	 * Class created by Snapje
	 * Any errors mail: davidoutdeveloper@gmail.com
	 */

	//net.minecraft.server.v1_8_R3.PacketPlayOutTitle pack = new net.minecraft.server.v1_8_R3.PacketPlayOutTitle(EnumTitleAction.TITLE, new IChatBaseComponent);
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	private Reflection reflection = new Reflection();
	private String title;
	private String subtitle;
	private int fadeInTicks, displayTicks, fadeOutTicks;
	private Object titleComponent;
	private Object subtitleComponent;
	
	public PacketPlayOutTitle(String title, String subtitle, int fadeInTicks, int displayTicks, int fadeOutTicks) {
		if(nms.getVersion() >= 1.08) {
		this.title = Chat.format(title);
		this.subtitle = subtitle;
		this.fadeInTicks = fadeInTicks;
		this.displayTicks = displayTicks;
		this.fadeOutTicks = fadeOutTicks;
		if(title == null) return;
		this.titleComponent = new IChatBaseComponent(title).getCompletedIChatBaseComponent();
		if(subtitle == null) return;
		this.subtitleComponent = new IChatBaseComponent(subtitle).getCompletedIChatBaseComponent();
		}
	}

	private int getFadeInTicks() {
		return fadeInTicks;
	}

	private int getDisplayTicks() {
		return displayTicks;
	}

	private int getFadeOutTicks() {
		return fadeOutTicks;
	}
	
	private Object getTitleComponent() {
		return this.titleComponent;
	}
	
	private Object getSubTitleComponent() {
		return this.subtitleComponent;
	}
	
	public Object getTitlePacket() {
		Object packet = null;
		
		if(nms.getVersion() >= 1.08) {
		if(title == null) return null;
		try {
			Object enumTitle = nms.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
			Object iChatBaseComponent = getTitleComponent();
			
			Constructor<?> testC = reflection.getConstructor("PacketPlayOutTitle", nms.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], nms.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
			packet = reflection.createObjectWithConstructor(testC, enumTitle, iChatBaseComponent, getFadeInTicks(), getDisplayTicks(), getFadeOutTicks());
			Constructor<?> packetConstructor = nms.getNMSClass("PacketPlayOutTitle").getConstructor(nms.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], nms.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
			//packet = packetConstructor.newInstance(enumTitle, iChatBaseComponent, getFadeInTicks(), getDisplayTicks(), getFadeOutTicks());
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (NoSuchFieldException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (InstantiationException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		}
		} else {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), "CannotSendTitleException", "The server version must be 1.8 or higher.");
		}
		return packet;
	}
	
	public Object getSubTitlePacket() {
    Object packet = null;
		
		if(subtitle == null) return null;
		try {
			Object enumTitle = nms.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
			Object iChatBaseComponent = getSubTitleComponent();
			
			Constructor<?> packetConstructor = nms.getNMSClass("PacketPlayOutTitle").getConstructor(nms.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], nms.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
			packet = packetConstructor.newInstance(enumTitle, iChatBaseComponent, getFadeInTicks(), getDisplayTicks(), getFadeOutTicks());
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (NoSuchFieldException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		} catch (InstantiationException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
		}
		return packet;
	}
	
	public void sendPacketToAllPlayers() {
		Object titlePacket = getTitlePacket();
		Object subtitlePacket = getSubTitlePacket();
		
		if(titlePacket == null) return;
		
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			nms.sendPacket(p, titlePacket);
		}
		
		if(subtitlePacket != null) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				nms.sendPacket(p, subtitlePacket);
			}
		}
		
	}

	
	


}
