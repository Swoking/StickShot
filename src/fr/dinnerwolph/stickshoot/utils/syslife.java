package fr.dinnerwolph.stickshoot.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.RestartCommand;
import org.spigotmc.SpigotConfig;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.dinnerwolph.scopegamesapi.ScopeGamesApi;
import fr.dinnerwolph.stickshoot.StickShoot;

public class syslife {

	@SuppressWarnings("deprecation")
	Player firstPos = (Player) Bukkit.getOfflinePlayer("");
	int firstPosKill = -1;
	@SuppressWarnings("deprecation")
	Player SecondPos = (Player) Bukkit.getOfflinePlayer("");
	int SecondPosKill = -1;
	@SuppressWarnings("deprecation")
	Player thirdPos = (Player) Bukkit.getOfflinePlayer("");
	int thirdPosKill = -1;
	@SuppressWarnings("deprecation")
	Player PlayerPos = (Player) Bukkit.getOfflinePlayer("");
	int playerPosKill = -1;

	public syslife(Player p, Player damager) {
		int i = new Random().nextInt(3) + 1;
		double x = StickShoot.getInstance().getConfig().getDouble("respawn.x" + i);
		double y = StickShoot.getInstance().getConfig().getDouble("respawn.y" + i);
		double z = StickShoot.getInstance().getConfig().getDouble("respawn.z" + i);

		Location location = new Location((World) Bukkit.getWorlds().get(0), x, y, z);
		if (p.getMaxHealth() == 2.0D) {
			p.setGameMode(GameMode.SPECTATOR);
			try {
				Bukkit.broadcastMessage(p.getName() + " a été éliminer par " + kill.getEjecter(p).getName());
			} catch (Exception localException) {
			}
			p.sendMessage("§2Merci d'avoir joué sur le §lStickShot");
			p.sendMessage("§cVous gagnez peut être  la §eprochaine partie");
			p.teleport(Region.getSpawn());
			GameUtils.removePlayerList(p);
			if (GameUtils.getPlayerList().size() <= 1) {
				
			}
			for (Map.Entry<Player, Integer> e : GameUtils.getCountKill().entrySet()) {
				if(e.getValue() > firstPosKill){
					firstPosKill = e.getValue();
					firstPos = e.getKey();
				} else if (e.getValue() > SecondPosKill) {
					SecondPosKill = e.getValue();
					SecondPos = e.getKey();
				} else if (e.getValue() > thirdPosKill) {
					thirdPosKill = e.getValue();
					thirdPos = e.getKey();
				}
			}
			Bukkit.broadcastMessage("1)" + firstPos.getName()  + " " + firstPosKill  + " kill");
			Bukkit.broadcastMessage("2)" + SecondPos.getName() + " " + SecondPosKill + " kill");
			Bukkit.broadcastMessage("3)" + thirdPos.getName()  + " " + thirdPosKill  + " kill");
			for(Player players: GameUtils.getGamePlayer()){
				playerPosKill = GameUtils.getCountKill().get(players);
				players.sendMessage("Vous avez fais " + playerPosKill + " kill");
			}
			
				new BukkitRunnable() {

					@Override
					public void run() {
						stopServer();
					}
				}.runTaskLater(StickShoot.getInstance(), 10 * 20L);
				
				new BukkitRunnable() {

					@Override
					public void run() {
						Bukkit.broadcastMessage("Réinitialisation du mini-jeux ...");
						Bukkit.unloadWorld("world", false);
						
						try {
							File file = Bukkit.getWorld("world").getWorldFolder();
							File file2 =  Bukkit.getWorld("default_map").getWorldFolder();
							FileUtils.deleteDirectory(file);
							FileUtils.copyDirectory(file2, file);
							
							RestartCommand.restart(new File(SpigotConfig.restartScript));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}.runTaskLater(StickShoot.getInstance(), 30 * 20L);

			try {
				p.setMaxHealth(p.getMaxHealth() - 2.0D);
				damager.getInventory().setItem(8, new ItemStack(Material.TNT));
				if (GameUtils.getCountKill().containsKey(damager)) {
					GameUtils.getCountKill().put(damager, GameUtils.getCountKill().get(damager) + 1);
				} else {
					GameUtils.getCountKill().put(damager, 1);
				}
			} catch (Exception localException1) {
			}
			p.teleport(location, TeleportCause.PLUGIN);
		}
	}

	private void stopServer() {
		for (Player players : Bukkit.getOnlinePlayers()) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("SSloby");
			players.sendPluginMessage(ScopeGamesApi.getInstance(), "BungeeCord", out.toByteArray());
		}
	}
}
