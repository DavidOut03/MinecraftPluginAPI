package snapje.packetapi.Parameters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class CraftMagicNumbers {
	
	public static Object getBlock(Block b) {
		Object block = null;
		
		 try {
			Class<?> cl = Class.forName("org.bukkit.craftbukkit." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + ".util." + "CraftMagicNumbers");
			block = cl.getDeclaredMethod("getBlock", b.getClass()).invoke(null, b);
		} catch (ClassNotFoundException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("CraftMagicNumbers", e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("CraftMagicNumbers", e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("CraftMagicNumbers", e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("CraftMagicNumbers", e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("CraftMagicNumbers", e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		} catch (SecurityException e) {
			snapje.packetapi.Utils.Error.sendErrorMessage("CraftMagicNumbers", e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
			e.printStackTrace();
		}
		
		return block;
	}
	
	public static Object getIBlockData(Block b) {
		Object blockdata = null;
		
		byte data = b.getData();
		
		if(getBlock(b) != null) {
			Object block  = getBlock(b);
			try {
				blockdata = block.getClass().getMethod("fromLegacyData", int.class).invoke(block, data);
			} catch (NoSuchMethodException e) {
				snapje.packetapi.Utils.Error.sendErrorMessage("CraftMagicNumbers", e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
				e.printStackTrace();
			} catch (SecurityException e) {
				snapje.packetapi.Utils.Error.sendErrorMessage("CraftMagicNumbers", e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				snapje.packetapi.Utils.Error.sendErrorMessage("CraftMagicNumbers", e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				snapje.packetapi.Utils.Error.sendErrorMessage("CraftMagicNumbers", e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				snapje.packetapi.Utils.Error.sendErrorMessage("CraftMagicNumbers", e.getClass().getName(), e.getStackTrace()[0].getLineNumber());
				e.printStackTrace();
			}
			
		}
		
		return blockdata;
	}

}
