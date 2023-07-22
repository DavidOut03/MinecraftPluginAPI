package snapje.packetapi.Packets;

import java.awt.List;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_7_R4.DataWatcher;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import snapje.packetapi.Parameters.PacketPlayer;
import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;

public class PacketPlayOutEntityMetadata {

	//net.minecraft.server.v1_7_R4.PacketPlayOutEntityMetadata data = new net.minecraft.server.v1_7_R4.PacketPlayOutEntityMetadata(i, datawatcher, flag);
	//net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata data = new net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata(arg0, arg1, arg2);
	
	private NetMinecraftServer nms = new NetMinecraftServer();
	private Reflection reflection = new Reflection();
	
	private int entityID;
	private List data;
	private boolean flag;
	private Object entityPlayer;
	private Player player;
	
	public PacketPlayOutEntityMetadata(Entity et) {
		this.entityID = et.getEntityId();
	}
	
	public PacketPlayOutEntityMetadata(Player player) {
		this.entityID = player.getEntityId();
		this.player = player;
	}
	
	
	/*
	@SuppressWarnings("unused")
	private Object entityPlayer() {
		return PacketPlayer.getEntityHuman(this.player);
	Bukkit.
	}
	
	public void getPacketTest() {
		EntityPlayer p = (EntityPlayer) ((CraftPlayer)p).getHandle();
        DataWatcher w = p.getDataWatcher(); //DataWatcher for client side NPC
        w.set(new net.minecraft.server.v1_7_R4.DataWatcher<>(0, DataWatcherRegistry.a), (byte) 0x20); //0x20 makes NPC invisible but still rendered
        try {
            Reflections.sendPlayerPacket(e.getPlayer(), new PacketPlayOutEntityMetadata(NPCManager.getNPCId(e.getPlayer()), w, true)); //sends packet
        } catch (Exception e1) {
            e1.printStackTrace();
        }
	}
	*/
	
	/*
	 * 
	 * EntityPlayer p = (EntityPlayer) NPCManager.getNPC(e.getPlayer()); //Client side NPC
        DataWatcher w = p.getDataWatcher(); //DataWatcher for client side NPC
        w.set(new DataWatcherObject<>(0, DataWatcherRegistry.a), (byte) 0x20); //0x20 makes NPC invisible but still rendered
        try {
            Reflections.sendPlayerPacket(e.getPlayer(), new PacketPlayOutEntityMetadata(NPCManager.getNPCId(e.getPlayer()), w, true)); //sends packet
        } catch (Exception e1) {
            e1.printStackTrace();
        }
	 */
	
	
}
