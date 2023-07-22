package snapje.packetapi.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import snapje.packetapi.Parameters.IChatBaseComponent;
import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;


public class PacketPlayOutPlayerListHeaderFooter {
	
	/*
	 * Class created by Snapje
	 * Any errors mail: davidoutdeveloper@gmail.com
	 */
	
	//net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter packet = new net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter();
	//net.minecraft.server.v1_13_R1.PacketPlayOutPlayerListHeaderFooter packet = new net.minecraft.server.v1_13_R1.PacketPlayOutPlayerListHeaderFooter()
	//net.minecraft.server.v1_14_R1.PacketPlayOutPlayerListHeaderFooter packet = new net.minecraft.server.v1_14_R1.PacketPlayOutPlayerListHeaderFooter();
	//net.minecraft.server.v1_16_R3.PacketPlayOutPlayerListHeaderFooter packet = new net.minecraft.server.v1_16_R3.PacketPlayOutPlayerListHeaderFooter();

	
	private NetMinecraftServer nms = new NetMinecraftServer();
	private Reflection reflection = new Reflection();
	private String headerText;
	private String footerText;
	private Object header;
	private Object footer;
	
	public PacketPlayOutPlayerListHeaderFooter(String header, String footer) {
		if(nms.getVersion() >= 1.08) {
		this.headerText = header;
		this.footerText = footer;
		if(header == null) return;
		this.header = new IChatBaseComponent(header).getCompletedIChatBaseComponent();
		if(footer == null) return;
		this.footer = new IChatBaseComponent(footer).getCompletedIChatBaseComponent();
		}
	}
	
	private Object getHeader() {
		return this.header;
	}
	private Object getFooter() {
		return this.footer;
	}
	public String getHeaderText() {
		return this.headerText;
	}
	public String getFooterText() {
		return this.footerText;
	}
	
	public Object getPacket() {
		Object packet = null;
		if(nms.getVersion() >= 1.08) {
		if(getHeader() == null) return null;
		
		try {
			
			
			if(nms.getVersion() >= 1.14) {
				Constructor<?> packetConstructor = nms.getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor();
			    packet = packetConstructor.newInstance();
				Field headerField = packet.getClass().getDeclaredField("header");
				headerField.setAccessible(true);
				if(getHeader() != null) {
				headerField.set(packet, getHeader());
				} else {
					headerField.set(packet, " ");
				} 
				
				Field footerField = packet.getClass().getDeclaredField("footer");
				footerField.setAccessible(true);
				if(getFooter() != null) {
					footerField.set(packet, getFooter());
				} else {
					footerField.set(packet, " ");
				} 
			} else {
			Constructor<?> packetConstructor = nms.getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(nms.getNMSClass("IChatBaseComponent"));
		    packet = packetConstructor.newInstance(getHeader());
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			if(getHeader() != null) {
			headerField.set(packet, getHeader());
			} else {
				headerField.set(packet, " ");
			} 
			
			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			if(getFooter() != null) {
				footerField.set(packet, getFooter());
			} else {
				footerField.set(packet, " ");
			} 
			}
			
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
		} else {
			snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), "CannotSendTablistException", "The server version must be 1.8 or higher.");
		}
		return packet;
	}
	
	/*
	net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter packet = new net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter();
	Object header = new ChatComponentText(getHeaderText());
	try {
		Field headerField = packet.getClass().getDeclaredField("a");
		headerField.setAccessible(true);
		headerField.set(packet, getHeader());
		
		Field footerField = packet.getClass().getDeclaredField("b");
		footerField.setAccessible(true);
		footerField.set(packet, new IChatBaseComponent(" ").getCompletedIChatBaseComponent());
		
		return packet;
	} catch (NoSuchFieldException e) {
		snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
	} catch (SecurityException e) {
		snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
	} catch (IllegalArgumentException e) {
		snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
	} catch (IllegalAccessException e) {
		snapje.packetapi.Utils.Error.sendErrorMessage(this.getClass().getName(), e.getClass().getName());
	}
	*/

}
