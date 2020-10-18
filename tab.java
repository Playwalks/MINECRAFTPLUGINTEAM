package us.someteamname.tabedit;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_16_R2.IChatBaseComponent;
import net.minecraft.server.v1_16_R2.Packet;
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_16_R2.PlayerConnection;

public class tab extends JavaPlugin implements Listener {
	public static tab pl;
	
	public Config config;
	
	public final String h = getConfig().getString("tab.Header").replace("&", "§");
	
    public final String f = getConfig().getString("Tab.Footer").replace("&", "§");

 public TeamAPI tag;
    
    public void onEnable() {   
    	pl = this;
    	this.config = new Config();
    	this.tag = TeamAPI() ;
    	Bukkit.getServer().getPluginManager().registerEvents(this, (Plugin)this);
    	this.config.createConfig();
    	System.out.println("WORKING... THIS IS WINNING MODE!");
    }

	private us.someteamname.tabedit.TeamAPI TeamAPI() {
		return null;
	}

	public void onDisable() {}
    
    private void getTab(Player p) {
    sendTab(p, this.h, this.f);
  }
  
  private static void sendTab(Player player, String header, String footer) {
    CraftPlayer craftplayer = (CraftPlayer)player;
    PlayerConnection connection = (craftplayer.getHandle()).playerConnection;
    IChatBaseComponent headerJSON = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
    IChatBaseComponent footerJSON = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
    PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
    try {
      Field headerField = packet.getClass().getDeclaredField("a");
      headerField.setAccessible(true);
      headerField.set(packet, headerJSON);
      headerField.setAccessible(!headerField.isAccessible());
      Field footerField = packet.getClass().getDeclaredField("b");
      footerField.setAccessible(true);
      footerField.set(packet, footerJSON);
      footerField.setAccessible(!footerField.isAccessible());
    } catch (Exception e) {
      e.printStackTrace();
    } 
    connection.sendPacket((Packet<?>)packet);
  }

  String owner = getConfig().getString("Tab.Prefix.Owner").replace("&", "§");
  
  String dev = getConfig().getString("Tab.Prefix.Dev").replace("&", "§");
  
  String admin = getConfig().getString("Tab.Prefix.Admin").replace("&", "§");
  
  String mod = getConfig().getString("Tab.Prefix.Mod").replace("&", "§");
  
  String user = getConfig().getString("Tab.Prefix.User").replace("&", "§");
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player p = event.getPlayer();
    getTab(p);
    if (p.hasPermission("kkTab.Owner")) {
      p.setPlayerListName(String.valueOf(this.owner) + p.getName());
      this.tag.setTag(p, this.owner);
    } else if (p.hasPermission("kkTab.Dev")) {
      p.setPlayerListName(String.valueOf(this.dev) + p.getName());
      this.tag.setTag(p, this.dev);
    } else if (p.hasPermission("kkTab.Admin")) {
      p.setPlayerListName(String.valueOf(this.admin) + p.getName());
      this.tag.setTag(p, this.admin);
    } else if (p.hasPermission("kkTab.Mod")) {
      p.setPlayerListName(String.valueOf(this.mod) + p.getName());
      this.tag.setTag(p, this.mod);
    } else {
      p.setPlayerListName(String.valueOf(this.user) + p.getName());
      this.tag.setTag(p, this.user);
    } 
  }	
}
