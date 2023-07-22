package snapje.packetapi.Parameters;

import java.lang.reflect.InvocationTargetException;

import snapje.packetapi.Utils.NetMinecraftServer;

public enum ItemSlotType {
	
	BOOTS, LEGGINGS, CHESTPLATE, HELMET, RIGHTHAND, LEFTHAND;
	
	public static int getSlotNumber(ItemSlotType type) {
		NetMinecraftServer nms = new NetMinecraftServer();
		
		int number = 0;
		if(nms.getVersion() >= 1.09) {
		if(type.equals(ItemSlotType.BOOTS)) {
			number = 4;
		} else if(type.equals(ItemSlotType.LEGGINGS)) {
			number = 3;
		} else if(type.equals(ItemSlotType.CHESTPLATE)) {
			number = 2;
		} else if(type.equals(ItemSlotType.HELMET)) {
			number = 1;
		} else if(type.equals(ItemSlotType.RIGHTHAND)) {
			number = 0;
		}
		}
		
		return number;
	}
	
	public static Object getItemStotType(ItemSlotType type) {
		NetMinecraftServer nms = new NetMinecraftServer();
		Object slottype= null;
		
		if(nms.getVersion() >= 1.09) {
		try {
		if(type.equals(ItemSlotType.BOOTS)) {
			slottype = nms.getNMSClass("EnumItemSlot").getMethod("valueOf", String.class).invoke(null, "FEET");
		} else if(type.equals(ItemSlotType.LEGGINGS)) {
			slottype = nms.getNMSClass("EnumItemSlot").getMethod("valueOf", String.class).invoke(null, "LEGS");
		} else if(type.equals(ItemSlotType.CHESTPLATE)) {
			slottype = nms.getNMSClass("EnumItemSlot").getMethod("valueOf", String.class).invoke(null, "CHEST");
		} else if(type.equals(ItemSlotType.HELMET)) {
			slottype = nms.getNMSClass("EnumItemSlot").getMethod("valueOf", String.class).invoke(null, "HEAD");
		} else if(type.equals(ItemSlotType.RIGHTHAND)) {
			slottype = nms.getNMSClass("EnumItemSlot").getMethod("valueOf", String.class).invoke(null, "OFFHAND");
		} else if(type.equals(ItemSlotType.LEFTHAND)) {
			slottype = nms.getNMSClass("EnumItemSlot").getMethod("valueOf", String.class).invoke(null, "OFFHAND");
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
