package snapje.packetapi.Utils;

import org.bukkit.Bukkit;

public class Error {
	
	public static void sendErrorMessage(String className, String exception) {
		Bukkit.getConsoleSender().sendMessage(Chat.format("&4[ERROR] &c" + exception + " in &4'&c" + className + "&4'&c. " ));
	}
	
	public static void sendErrorMessage(String className, String exception, String explenation) {
		Bukkit.getConsoleSender().sendMessage(Chat.format("&4[ERROR] &c" + exception + " in &4'&c" + className + "&4'&c. " + explenation));
	}
	
	public static void sendErrorMessage(String className, String exception, int lineNumber) {
		Bukkit.getConsoleSender().sendMessage(Chat.format("&4[ERROR] &c" + exception + " in &4'&c" + className + "&4'&c, on line (LINE). ").replace("(LINE)", lineNumber + ""));
	}
	
	public static void sendErrorMessage(String className, String exception, String explenation, int lineNumber) {
		Bukkit.getConsoleSender().sendMessage(Chat.format("&4[ERROR] &c" + exception + " in &4'&c" + className + "&4'&c, on line (LINE). " + explenation).replace("(LINE)", lineNumber + ""));
	}

}
