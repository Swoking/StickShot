package fr.dinnerwolph.stickshoot.listener.world;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import fr.dinnerwolph.stickshoot.StickShoot;


public class Block implements Listener{
	
	StickShoot plugin = StickShoot.getInstance();
	
	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent event){
		if(event.getBlock().getType() == Material.TNT){
			event.getBlock().setType(Material.AIR);
			Entity tnt = event.getPlayer().getWorld().spawn(event.getBlock().getLocation(), TNTPrimed.class);
			((TNTPrimed) tnt).setFireTicks(0);
		}
	}

	
	@EventHandler
    public void explodeHeight(EntityExplodeEvent e) {
		if(plugin.getConfig().getBoolean("tnt.canceled"))return;
        if(e.getEntityType() == EntityType.PRIMED_TNT) {
            e.blockList().clear();
        }
    }
}
