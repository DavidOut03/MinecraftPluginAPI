package snapje.packetapi.Parameters;

import java.lang.reflect.InvocationTargetException;

import snapje.packetapi.Utils.NetMinecraftServer;

public enum ChatMessageType {
	
	CHAT, SYSTEM, GAME_INFO, ACTIONBAR;
	
	public static byte getChatMessageByteType(ChatMessageType type) {
		NetMinecraftServer nms = new NetMinecraftServer();
		
		
		if(type.equals(ChatMessageType.CHAT)) {
			return (byte) 0;
		} else if(type.equals(ChatMessageType.SYSTEM)) {
			return (byte) 1;
		} else if(type.equals(ChatMessageType.GAME_INFO) || type.equals(ChatMessageType.ACTIONBAR)) {
			return (byte) 2;
		} 
		
		return 0;
	}
	
	public static Object getChatMessageType(ChatMessageType type) {
		NetMinecraftServer nms = new NetMinecraftServer();
		Object slottype= null;
		
		if(nms.getVersion() >= 1.12) {
		try {
		if(type.equals(ChatMessageType.CHAT)) {
			slottype = nms.getNMSClass("EnumItemSlot").getMethod("valueOf", String.class).invoke(null, "CHAT");
		} else if(type.equals(ChatMessageType.SYSTEM)) {
			slottype = nms.getNMSClass("EnumItemSlot").getMethod("valueOf", String.class).invoke(null, "SYSTEM");
		} else if(type.equals(ChatMessageType.GAME_INFO) || type.equals(ChatMessageType.ACTIONBAR)) {
			slottype = nms.getNMSClass("EnumItemSlot").getMethod("valueOf", String.class).invoke(null, "GAME_INFO");
		} 
	   } catch (IllegalArgumentException e) {
		e.printStackTrace();
	   } catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (SecurityException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		}
		
		return slottype;
	}

}
