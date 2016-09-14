package fr.dinnerwolph.stickshoot.listener.player;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.dinnerwolph.scopegamesapi.ScopeGamesApi;
import fr.dinnerwolph.stickshoot.StickShoot;
import fr.dinnerwolph.stickshoot.utils.GameState;
import fr.dinnerwolph.stickshoot.utils.GameUtils;

public class Join implements Listener {

	private static Player player;
	private static ItemStack kits;
	private static ItemMeta kitsMeta;
	private static ItemStack bed;
	private static ItemMeta bedMeta;
	private static Inventory playerInventory;

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		player = event.getPlayer();
		playerInventory = player.getInventory();
		if(StickShoot.getMaintenanceMode()){
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("SSloby");
			player.sendPluginMessage(ScopeGamesApi.getInstance(), "BungeeCord", out.toByteArray());
		}
		if (GameState.getState() == GameState.WAITING || GameState.getState() == GameState.TIMER
				&& GameUtils.getPlayerList().size() <= StickShoot.getMinPlayer() - 1) {
			player.setGameMode(GameMode.ADVENTURE);
			player.teleport(GameUtils.getSpawnLocation(), TeleportCause.PLUGIN);
			GameUtils.addPlayerList(player);
			GameUtils.getCountKill().put(player, 0);
			player.setMaxHealth(10);
			player.setHealth(10);
			player.getInventory().clear();

			kits = new ItemStack(Material.ENCHANTED_BOOK);
			kitsMeta = kits.getItemMeta();
			kitsMeta.setDisplayName(ChatColor.GOLD + "Kits");
			kits.setItemMeta(kitsMeta);

			bed = new ItemStack(Material.BED);
			bedMeta = bed.getItemMeta();
			bedMeta.setDisplayName(ChatColor.GOLD + "Retour au HUB");
			bed.setItemMeta(bedMeta);

			playerInventory.setItem(0, kits);
			playerInventory.setItem(8, bed);

			event.setJoinMessage(
					player.getName() + " a rejoint la partie [" + GameUtils.getPlayerList().size() + "/15]");
			if (GameState.getState() == GameState.WAITING && GameUtils.getPlayerList().size() == 6) {
				GameState.setState(GameState.TIMER);
				GameUtils.Timer();
			}
		} else {
			player.setGameMode(GameMode.SPECTATOR);
			playerInventory.setItem(8, bed);
			event.setJoinMessage(null);
		}
	}

}
