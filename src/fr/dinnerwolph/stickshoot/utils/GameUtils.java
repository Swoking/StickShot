package fr.dinnerwolph.stickshoot.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import fr.dinnerwolph.stickshoot.StickShoot;

public class GameUtils {

	private static List<Player>	PlayerList = new ArrayList<>();
	private static BukkitTask 	task;
	private static int 			timer;
	private static ItemStack 	stick;
	private static ItemMeta 	stickMeta;
	private static ItemStack 	bow;
	private static ItemMeta 	bowMeta;
	private static Location 	spawn 		= new Location(Bukkit.getWorlds().get(0), 0, 202, 0); 
	private static BossBar 		bar 		= Bukkit.createBossBar("Commencement de la partie", BarColor.PURPLE, BarStyle.SOLID, BarFlag.PLAY_BOSS_MUSIC);
	private static Map<Player, Integer> CountKill = new HashMap<>();
	private static ArrayList<Player> GamePlayer = new ArrayList<>();
	
	public static List<Player> getPlayerList() {
		return PlayerList;
	}

	public static void addPlayerList(Player player) {
		PlayerList.add(player);
	}

	public static void removePlayerList(Player player) {
		PlayerList.remove(player);
	}
	
	public static void stopTimer(){
		task.cancel();
	}
 
	public static void Timer(){
		timer = 11;
		bar.setProgress(1.0);
		task = new BukkitRunnable() {
			@Override
			public void run() {
				timer--;
				double timers = timer / 120;
				bar.setProgress(timers);
				for(Player players : getPlayerList()){
					bar.addPlayer(players);
				}
				
				if(timer == 120 || timer == 60 || timer == 30 || timer == 10 || timer == 5 || timer == 4 | timer == 3 || timer == 2){
					Bukkit.broadcastMessage("La partie commence dans " + timer + " secondes");
				}
				if(timer == 1){
					Bukkit.broadcastMessage("La partie commence dans 1 seconde");
					this.cancel();
					GameState.setState(GameState.IN_GAME);
					start();
				}			
			}
		}.runTaskTimer(StickShoot.getInstance(), 1L, 20L);
	}
	
	public static void start(){
		for(Player players : getPlayerList()){
			players.setGameMode(GameMode.SURVIVAL);
			players.setLevel(0);
			
			stick = new ItemStack(Material.STICK);
			stickMeta = stick.getItemMeta();
			stickMeta.addEnchant(Enchantment.KNOCKBACK, 10, true);
			stickMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			stick.setItemMeta(stickMeta);
			
			bow = new ItemStack(Material.BOW);
			bowMeta = bow.getItemMeta();
			bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			bowMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 10, true);
			bowMeta.addEnchant(Enchantment.DURABILITY, 1000, true);
			bowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			bow.setItemMeta(bowMeta);
			
			players.getInventory().clear();
			players.getInventory().addItem(stick);
			players.getInventory().addItem(bow);
			players.getInventory().setItem(9, new ItemStack(Material.ARROW));
			
			int x1 = new Random().nextInt(StickShoot.getSpawnSpgere());
	        int x2 = new Random().nextInt(StickShoot.getSpawnSpgere());
	        int x = x1 - x2;
	        
	        int z1 = new Random().nextInt(StickShoot.getSpawnSpgere());
	        int z2 = new Random().nextInt(StickShoot.getSpawnSpgere());
	        int z = z1 - z2;
	        int y = StickShoot.getInstance().getConfig().getInt("stickshot.y");
	        players.teleport(new Location(Bukkit.getWorlds().get(0), x, y, z), PlayerTeleportEvent.TeleportCause.PLUGIN);
		}
	}
	
	public static Location getSpawnLocation(){
		return spawn;
	}
	
	public static Map<Player, Integer> getCountKill(){
		return CountKill;
	}
	
	public static ArrayList<Player> getGamePlayer(){
		return GamePlayer;
	}

}
