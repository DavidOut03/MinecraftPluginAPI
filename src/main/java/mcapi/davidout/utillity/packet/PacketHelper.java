package mcapi.davidout.utillity.packet;

import mcapi.davidout.utillity.packet.reflection.ReflectionHelper;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class PacketHelper {

    public static Object getCraftPlayer(Player player){
        try {
            return  ReflectionHelper.getMethod(player.getClass(), "getHandle").invoke(player);
        } catch (Exception ignored) {
            return null;
        }
    }

    public static Object getPlayerConnection(Player player) {
        Object craftPlayer = getCraftPlayer(player);
        if(craftPlayer == null) return null;

        try {
            return ReflectionHelper.getField(craftPlayer.getClass(), "playerConnection").get(craftPlayer);
        } catch (Exception ex) {
            return null;
        }
    }

    public static boolean sendPacket(Player player, Object packet) throws InvocationTargetException, IllegalAccessException {
        Object connection = getPlayerConnection(player);
        if(connection == null) return false;

        ReflectionHelper.getMethod(connection.getClass(), "sendPacket", NMSHelper.getNMSClass("Packet")).invoke(packet);
        return true;
    }

}
