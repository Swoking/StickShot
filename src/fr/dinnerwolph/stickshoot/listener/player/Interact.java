package fr.dinnerwolph.stickshoot.listener.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.dinnerwolph.scopegamesapi.ScopeGamesApi;
import sun.net.www.content.text.plain;

public class Interact implements Listener {

	private static Player player;

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		player = event.getPlayer();
		try {
			if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Retour au HUB")) {
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("Connect");
				out.writeUTF("SSloby");
				player.sendPluginMessage(ScopeGamesApi.getInstance(), "BungeeCord", out.toByteArray());
			}
		} catch (Exception e) {
			System.out.println(player.getName() + ": a utilisé l'item :" + event.getItem().getType());
		}
	}

	@EventHandler
	public void InventoryClick(InventoryClickEvent event) {
		player = (Player) event.getWhoClicked();
		try {
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.GOLD + "Retour au HUB")) {
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("Connect");
				out.writeUTF("SSloby");
				player.sendPluginMessage(ScopeGamesApi.getInstance(), "BungeeCord", out.toByteArray());
			}
		} catch (Exception e) {
		}
		System.out.println(player.getName() + ": a utilisé l'item :" + event.getCurrentItem().getType());
	}

}
