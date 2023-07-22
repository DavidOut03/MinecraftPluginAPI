package snapje.packetapi.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import snapje.packetapi.Parameters.ChatMessageType;
import snapje.packetapi.Parameters.IChatBaseComponent;
import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;

public class PacketPlayOutChat extends Object {
	
	/*
	 * Class created by Snapje
	 * Any errors mail: davidoutdeveloper@gmail.com
	 */
	
	//net.minecraft.server.v1_7_R4.PacketPlayOutChat packet = new net.minecraft.server.v1_7_R4.PacketPlayOutChat(ichatbasecomponent);
	//net.minecraft.server.v1_8_R3.PacketPlayOutChat packet = new net.minecraft.server.v1_8_R3.PacketPlayOutChat(ichatbasecomponent, byte);
	//net.minecraft.server.v1_13_R1.PacketPlayOutChat
	//net.minecraft.server.v1_16_R3.PacketPlayOutChat packet = new net.minecraft.server.v1_16_R3.PacketPlayOutChat(ichatbasecomponent, ChatMessageType.g, uuid)
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	private Reflection reflection = new Reflection();
	private ChatMessageType type;
	private Object actionbarComponent;
	private String message;
	private UUID uuid;
	
	private Object getIChatBaseComponent() {
		return actionbarComponent;
	}
	
	public PacketPlayOutChat(String message, ChatMessageType type) {
		if(nms.getVersion() > 1.07) {
		this.message = message;
		this.actionbarComponent = new IChatBaseComponent(message).getCompletedIChatBaseComponent();
		this.type = type;
		} else {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), "CannotSendActionBarException", "The server version must be 1.8 or higher.");
		}
	}
	
	public PacketPlayOutChat(String message, ChatMessageType type,  UUID uuid) {
		if(nms.getVersion() > 1.07) {
		this.message = message;
		this.actionbarComponent = new IChatBaseComponent(message).getCompletedIChatBaseComponent();
		this.uuid = uuid;
		this.type = type;
		} else {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), "CannotSendActionBarException", "The server version must be 1.8 or higher.");
		}
	}

	public String getMessage() {
		return message;
	}
	
	public Object getPacket() {
		Object packet = null;
		if(nms.getVersion() > 1.07) {
		if(getIChatBaseComponent() == null) return packet;
		try {
			Object chatComponent = getIChatBaseComponent();
			if(nms.getVersion() >= 1.16) {
				if(uuid != null) {
				Constructor<?> packetConstructor = nms.getNMSClass("PacketPlayOutChat").getConstructor(nms.getNMSClass("IChatBaseComponent"), nms.getNMSClass("ChatMessageType"), UUID.class);
				Object chat = nms.getNMSClass("ChatMessageType").getDeclaredMethod("a", byte.class).invoke(null, (byte) ChatMessageType.getChatMessageByteType(type));
				packet = packetConstructor.newInstance(chatComponent, chat, this.uuid);
				}
			} else {
				Constructor<?> packetConstructor = nms.getNMSClass("PacketPlayOutChat").getConstructor(nms.getNMSClass("IChatBaseComponent"), byte.class);
				packet = packetConstructor.newInstance(chatComponent, ChatMessageType.getChatMessageByteType(type));
			}
			
			
		} catch (InstantiationException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
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
		} else {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), "CannotSendActionBarException", "The server version must be 1.8 or higher.");
		}
		return packet;
	}
	
	public void sendPacketToAllPlayers() {
		Object packet = getPacket();
		
		if(packet == null) return;
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			nms.sendPacket(p, packet);
		}
	}

}
