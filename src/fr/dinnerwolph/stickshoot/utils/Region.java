package fr.dinnerwolph.stickshoot.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import fr.dinnerwolph.stickshoot.StickShoot;

public class Region
  implements Listener
{
  public static WorldEditPlugin getWorldEdit()
  {
    Plugin p = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
    if ((p instanceof WorldEditPlugin)) {
      return (WorldEditPlugin)p;
    }
    return null;
  }
  
  public static void savespawn(Location l)
  {
    StickShoot.getInstance().getConfig().set("spawn.x", Integer.valueOf(l.getBlockX()));
    StickShoot.getInstance().getConfig().set("spawn.y", Integer.valueOf(l.getBlockY()));
    StickShoot.getInstance().getConfig().set("spawn.z", Integer.valueOf(l.getBlockZ()));
    StickShoot.getInstance().getConfig().set("spawn.w", l.getWorld());
    StickShoot.getInstance().saveConfig();
  }
  
  public static void saveRegion(Location l1, Location l2)
  {
	  StickShoot.getInstance().getConfig().set("PlayableZonne.start.x", Integer.valueOf(l1.getBlockX()));
	  StickShoot.getInstance().getConfig().set("PlayableZonne.start.y", Integer.valueOf(l1.getBlockY()));
	  StickShoot.getInstance().getConfig().set("PlayableZonne.start.z", Integer.valueOf(l1.getBlockZ()));
	  StickShoot.getInstance().getConfig().set("PlayableZonne.start.w", l1.getWorld().getWorldFolder().getName());
	  StickShoot.getInstance().getConfig().set("PlayableZonne.end.x", Integer.valueOf(l2.getBlockX()));
	  StickShoot.getInstance().getConfig().set("PlayableZonne.end.y", Integer.valueOf(l2.getBlockY()));
	  StickShoot.getInstance().getConfig().set("PlayableZonne.end.z", Integer.valueOf(l2.getBlockZ()));
	  StickShoot.getInstance().getConfig().set("PlayableZonne.end.w", l2.getWorld().getWorldFolder().getName());
	  StickShoot.getInstance().saveConfig();
  }
  
  public static Location getLocationStart()
  {
    World world = Bukkit.getWorld(StickShoot.getInstance().getConfig().get("PlayableZonne.start.w").toString());
    double x = StickShoot.getInstance().getConfig().getDouble("PlayableZonne.start.x");
    double y = StickShoot.getInstance().getConfig().getDouble("PlayableZonne.start.y");
    double z = StickShoot.getInstance().getConfig().getDouble("PlayableZonne.start.z");
    return new Location(world, x, y, z);
  }
  
  public static Location getLocationEnd()
  {
    World world = Bukkit.getWorld(StickShoot.getInstance().getConfig().get("PlayableZonne.end.w").toString());
    double x = StickShoot.getInstance().getConfig().getDouble("PlayableZonne.end.x");
    double y = StickShoot.getInstance().getConfig().getDouble("PlayableZonne.end.y");
    double z = StickShoot.getInstance().getConfig().getDouble("PlayableZonne.end.z");
    return new Location(world, x, y, z);
  }
  
  public static Location getSpawn()
  {
    World world = (World)Bukkit.getWorlds().get(0);
    double x = StickShoot.getInstance().getConfig().getDouble("spawn.x");
    double y = StickShoot.getInstance().getConfig().getDouble("spawn.y");
    double z = StickShoot.getInstance().getConfig().getDouble("spawn.z");
    return new Location(world, x, y, z);
  }
}
