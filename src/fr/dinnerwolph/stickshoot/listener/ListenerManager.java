package fr.dinnerwolph.stickshoot.listener;


import org.bukkit.plugin.PluginManager;

import fr.dinnerwolph.stickshoot.StickShoot;
import fr.dinnerwolph.stickshoot.listener.player.Damage;
import fr.dinnerwolph.stickshoot.listener.player.Interact;
import fr.dinnerwolph.stickshoot.listener.player.Join;
import fr.dinnerwolph.stickshoot.listener.player.Move;
import fr.dinnerwolph.stickshoot.listener.player.Quit;
import fr.dinnerwolph.stickshoot.listener.world.Block;

public class ListenerManager {
	
	public ListenerManager(StickShoot plugin) {
		PluginManager pManager = plugin.getServer().getPluginManager();
		pManager.registerEvents(new Join(), plugin);
		pManager.registerEvents(new Quit(), plugin);
		pManager.registerEvents(new Damage(), plugin);
		pManager.registerEvents(new Block(), plugin);
		pManager.registerEvents(new Move(), plugin);
		pManager.registerEvents(new Interact(), plugin);
	}

}
