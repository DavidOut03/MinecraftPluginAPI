package snapje.packetapi.Parameters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import snapje.packetapi.Utils.Chat;
import snapje.packetapi.Utils.NetMinecraftServer;


public class CraftItemStack {
	
	
	public static Object asNMSCopy(ItemStack original) {
		NetMinecraftServer nms = new NetMinecraftServer();
		Method meth;
		Object object = null;
		Class<?> cri = null;
		
		try {
			 cri = Class.forName("org.bukkit.craftbukkit." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + ".inventory." + "CraftItemStack");
		} catch (ClassNotFoundException e1) {
		Bukkit.broadcastMessage("class not found");
		}
		if(cri != null) {
		try {
			if(cri.getDeclaredMethod("asNMSCopy", ItemStack.class) != null) {
            meth = cri.getDeclaredMethod("asNMSCopy", ItemStack.class);
            meth.setAccessible(true);
            object = meth.invoke(null, original);
			} else {
				Bukkit.getConsoleSender().sendMessage(Chat.format("&cMethod not found"));
			}
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		return object;
	}
	

	

}
