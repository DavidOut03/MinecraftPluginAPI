package snapje.packetapi.Functions;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import snapje.packetapi.Utils.NetMinecraftServer;
import snapje.packetapi.Utils.Reflection;


public class Corpse implements Listener{
	
	/*
	 private final static int SERVER_VIEW_DISTANCE = Bukkit.getServer().getViewDistance();
	 private final static byte PLAYER_SLEEP_HEIGHT_FIX = 0xF / 0xA;
	 private final Plugin plugin;
	 
	 public Corpse(Plugin plugin) {
	  this.plugin = plugin;
	 }

	 public static EntityPlayer createPlayer(Player player) {
		 
	  MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer(); // get server
	  WorldServer world = ((CraftWorld) player.getWorld()).getHandle(); // get the world the player who died was standing in.
	  GameProfile botGameProfile = new GameProfile(UUID.randomUUID(), player.getName()); // create a gameProfile for the bot
	  GameProfile playerProfile = ((CraftPlayer) player).getProfile(); // get the skin of the player who just died
	  Collection < Property > skinProperty = playerProfile.getProperties().get("textures"); // get his textures
	  botGameProfile.getProperties().putAll("textures", skinProperty); // put the textures on the bot
	  PlayerInteractManager interactManager = new PlayerInteractManager(world);
	  EntityPlayer botPlayer = new EntityPlayer(minecraftServer, world, botGameProfile, interactManager); // initialize bot

	  Location playerLocation = player.getLocation();
	   botPlayer.setLocation(playerLocation.getX(), // set his location on player death
	   playerLocation.getY(),
	   playerLocation.getZ(),
	   playerLocation.getYaw(),
	   playerLocation.getPitch());
	  return botPlayer;
	 }

	 @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	 public void onPlayerDeath(PlayerDeathEvent event) {
	  Player player = event.getEntity();
	  UUID uuid = player.getUniqueId();
	  Location deathLocation = player.getLocation();
	  BlockPosition deathBlockPosition = new BlockPosition(deathLocation.getX(), deathLocation.getY(), deathLocation.getZ()); // you should modify the Y value and adapt it with the height of the ground if the player didn't die on ground, I'm not doing it here.
	  EntityPlayer botPlayer = createPlayer(player);
	  
	  for (Player p: Bukkit.getOnlinePlayers()) {
	   if (p.getLocation().distance(deathLocation) > SERVER_VIEW_DISTANCE) continue; // we don't send packets to players that are too far away.
	   PlayerConnection conn = ((CraftPlayer) p).getHandle().playerConnection;
	   conn.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, botPlayer));
	   conn.sendPacket(new PacketPlayOutNamedEntitySpawn(botPlayer)); // spawn the bot to nearby players.
	   conn.sendPacket(new PacketPlayOutBed(botPlayer, deathBlockPosition)); // make the bot 'sleep'
	   conn.sendPacket(new PacketPlayOutEntity.PacketPlayOutRelEntityMove(botPlayer.getId(), // fix the bot height
	    (byte) 0, // we do not need to change X or Z values.
	    PLAYER_SLEEP_HEIGHT_FIX, // decrease height by 0.15f
	    (byte) 0,
	    true));
	  }

	  Bukkit.getScheduler().runTaskLater(plugin, () -> {
	   for (Player p: Bukkit.getOnlinePlayers()) {
	    ((CraftPlayer) p).getHandle().playerConnection
	     .sendPacket(new PacketPlayOutEntityDestroy(botPlayer.getId())); // let's destroy the bot after some time
	   }
	  }, 200);
	 }
	 
*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	private static Reflection reflection = new Reflection();
	private static NetMinecraftServer nms = new NetMinecraftServer();
	
	
	public static void spawn(Player p) {
		PacketPlayOutNamedEntitySpawn npc = new PacketPlayOutNamedEntitySpawn(p.getEntityId(), null, 0, MathHelper.floor( (p.getLocation().getY() + 2)  * 32), 0, (byte) (145 * 256.0F / 360.0F), (byte) 0, new ItemStack(Material.RED_ROSE));
		PacketPlayOutBed bed = new PacketPlayOutBed(p);
		PacketPlayOutRelEntityMove move = new PacketPlayOutRelEntityMove(p.getEntityId(), (byte) 0, (byte) ((p.getLocation().getBlockY()) -1.7 - p.getLocation().getBlockY() * 32), (byte) 0, false);
		
		for(Player online : Bukkit.getOnlinePlayers()) {
			if(online != p) {
				nms.sendPacket(online, npc.getPacket());
				nms.sendPacket(online, bed.getPacket());
				nms.sendPacket(online, move.getPacket());
			}
		}
	}
	
	
	
	
	
	public static void spawnCorpse(Player player) {
        PacketPlayOutNamedEntitySpawn npc = new PacketPlayOutNamedEntitySpawn(
                ((CraftPlayer) player).getHandle());
        PacketPlayOutBed sleep = new PacketPlayOutBed();
        PacketPlayOutEntityEquipment helmet = new PacketPlayOutEntityEquipment(
                player.getEntityId(), 1, CraftItemStack.asNMSCopy(player
                        .getInventory().getHelmet()));
        PacketPlayOutEntityEquipment chestplate = new PacketPlayOutEntityEquipment(
                player.getEntityId(), 2, CraftItemStack.asNMSCopy(player
                        .getInventory().getChestplate()));
        PacketPlayOutEntityEquipment leggings = new PacketPlayOutEntityEquipment(
                player.getEntityId(), 3, CraftItemStack.asNMSCopy(player
                        .getInventory().getLeggings()));
        PacketPlayOutEntityEquipment boots = new PacketPlayOutEntityEquipment(
                player.getEntityId(), 4, CraftItemStack.asNMSCopy(player
                        .getInventory().getBoots()));
        PacketPlayOutRelEntityMove move = new PacketPlayOutRelEntityMove(
                player.getEntityId(), (byte) 0, (byte) ((player.getLocation()
                        .getY() - 1.7 - player.getLocation().getY()) * 32),
                (byte) 0, false);
        try {
            Field npca = npc.getClass().getDeclaredField("a");
            npca.setAccessible(true);
            npca.setInt(npc, player.getEntityId());
            npca.setAccessible(!npca.isAccessible());
            Field npch = npc.getClass().getDeclaredField("h");
            npch.setAccessible(true);
            npch.setInt(npc, Item.getId(Item.getItemOf(Blocks.RED_FLOWER)));
            npch.setAccessible(!npch.isAccessible());
            Field npcd = npc.getClass().getDeclaredField("d");
            npcd.setAccessible(true);
            npcd.setInt(npc, MathHelper.floor((player.getLocation().getY() + 2) * 32));
            npcd.setAccessible(!npcd.isAccessible());
            Field sleepa = sleep.getClass().getDeclaredField("a");
            sleepa.setAccessible(true);
            sleepa.setInt(sleep, player.getEntityId());
            sleepa.setAccessible(!sleepa.isAccessible());
            Field sleepb = sleep.getClass().getDeclaredField("b");
            sleepb.setAccessible(true);
            sleepb.setInt(sleep, (int) player.getLocation().getX());
            sleepb.setAccessible(!npca.isAccessible());
            Field sleepc = sleep.getClass().getDeclaredField("c");
            sleepc.setAccessible(true);
            sleepc.setInt(sleep, (int) player.getLocation().getY());
            sleepc.setAccessible(!sleepc.isAccessible());
            Field sleepd = sleep.getClass().getDeclaredField("d");
            sleepd.setAccessible(true);
            sleepd.setInt(sleep, (int) player.getLocation().getZ());
            sleepd.setAccessible(!sleepd.isAccessible());
            Field npcf = npc.getClass().getDeclaredField("f");
            npcf.setAccessible(true);
            npcf.setByte(npc, (byte) (145 * 256.0F / 360.0F));
            npcf.setAccessible(!npcf.isAccessible());
            Field npcg = npc.getClass().getDeclaredField("g");
            npcg.setAccessible(true);
            npcg.setByte(npc, (byte) 0);
            npcg.setAccessible(!npcg.isAccessible());
        } catch (Exception x) {
            x.printStackTrace();
        }
        for (Player p : player.getWorld().getPlayers()) {
            if (p != player) {
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(npc);
                ((CraftPlayer) p).getHandle().playerConnection
                        .sendPacket(helmet);
                ((CraftPlayer) p).getHandle().playerConnection
                        .sendPacket(chestplate);
                ((CraftPlayer) p).getHandle().playerConnection
                        .sendPacket(leggings);
                ((CraftPlayer) p).getHandle().playerConnection
                        .sendPacket(boots);
                ((CraftPlayer) p).getHandle().playerConnection
                        .sendPacket(sleep);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(move);
            }
        }
      
    }
    */
    

}
