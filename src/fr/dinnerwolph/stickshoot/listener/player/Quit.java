package fr.dinnerwolph.stickshoot.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.dinnerwolph.stickshoot.utils.GameState;
import fr.dinnerwolph.stickshoot.utils.GameUtils;

public class Quit implements Listener{
	
	private static Player player;
	
	@EventHandler
	public void onPlayerquit(PlayerQuitEvent event){
		player = event.getPlayer();
		if(GameUtils.getPlayerList().contains(player)){
			GameUtils.removePlayerList(player);
			event.setQuitMessage(player.getName() + " a quitté la partie [" + GameUtils.getPlayerList().size() + "/15]");
			if(GameState.getState() == GameState.TIMER && GameUtils.getPlayerList().size() <= 5){
				GameUtils.stopTimer();
				Bukkit.broadcastMessage("Plus assez de joueurs pour commencer la partie");
				GameState.setState(GameState.WAITING);
				for(Player players : GameUtils.getPlayerList()){
					players.setLevel(0);
				}
			}
		}else {
			event.setQuitMessage(null);
		}
	}

}
