package fr.dinnerwolph.stickshoot.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.spigotmc.RestartCommand;
import org.spigotmc.SpigotConfig;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.dinnerwolph.scopegamesapi.ScopeGamesApi;
import fr.dinnerwolph.stickshoot.utils.GameUtils;

public class CommandGame implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0){
			sender.sendMessage("/game <start|stop>");
			return true;
		}
		if(args[0].equalsIgnoreCase("start")){
			Bukkit.broadcastMessage(sender.getName() + " a forcé le démarrage du StickShot");
			GameUtils.Timer();
		}
		if(args[0].equalsIgnoreCase("stop")){
			Bukkit.broadcastMessage(sender.getName() + " a forcé l'arrêt du StickShot");
			for(Player players : Bukkit.getOnlinePlayers()){
				  ByteArrayDataOutput out = ByteStreams.newDataOutput();
					out.writeUTF("Connect");
					out.writeUTF("SSloby");
					players.sendPluginMessage(ScopeGamesApi.getInstance(), "BungeeCord", out.toByteArray());
			  }
			RestartCommand.restart(new File(SpigotConfig.restartScript));
		}
		return false;
	}

}
