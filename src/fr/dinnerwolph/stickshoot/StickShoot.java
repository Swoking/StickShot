package fr.dinnerwolph.stickshoot;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.plugin.java.JavaPlugin;

import fr.dinnerwolph.stickshoot.commands.CommandGame;
import fr.dinnerwolph.stickshoot.listener.ListenerManager;
import fr.dinnerwolph.stickshoot.utils.GameState;

public class StickShoot extends JavaPlugin{
	
	private static StickShoot 	plugin;
	private static int 			spawnsphere;
	private static int 			minPlayer;
	private static int 			maxPlayer;
	private static boolean 		maintenaceMode;
	private static int 			maxHealth;
	
	public void onEnable(){
		plugin = this;
		new ListenerManager(getInstance());
		GameState.setState(GameState.WAITING);
		getCommand("game").setExecutor(new CommandGame());
		saveDefaultConfig();
		Bukkit.getWorlds().get(0).setDifficulty(Difficulty.PEACEFUL);
		spawnsphere = this.getConfig().getInt("spawn.raillon");
		minPlayer = this.getConfig().getInt("player.min");
		maintenaceMode = this.getConfig().getBoolean("maintenance");
		maxHealth = this.getConfig().getInt("player.maxHealth");
		maxPlayer = this.getConfig().getInt("player.max");
	}
	
	public static StickShoot getInstance(){
		return plugin;
	}
	
	public static Integer getSpawnSpgere(){
		return spawnsphere;
	}
	
	public static Integer getMinPlayer(){
		return minPlayer;
	}
	
	public static boolean getMaintenanceMode(){
		return maintenaceMode;
	}
	
	public static Integer getMaxHealth(){
		return maxHealth;
	}

	public static Integer getMaxPlayer(){
		return maxPlayer;
	}
	
}
