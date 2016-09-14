package fr.dinnerwolph.stickshoot.listener.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.dinnerwolph.stickshoot.utils.GameState;
import fr.dinnerwolph.stickshoot.utils.Region;
import fr.dinnerwolph.stickshoot.utils.kill;
import fr.dinnerwolph.stickshoot.utils.syslife;

public class Move implements Listener{
	  private double maxX;
	  private double minX;
	  private double maxY;
	  private double minY;
	  private double maxZ;
	  private double minZ;
	  private Player target;
	  private Player ejecter;
	  
	  @EventHandler
	  public void move(PlayerMoveEvent event)
	  {
	    if (GameState.getState() != GameState.IN_GAME) {
	      return;
	    }
	    this.target = event.getPlayer();
	    this.ejecter = kill.getEjecter(this.target);
	    if (Region.getLocationStart().getX() >= Region.getLocationEnd().getX())
	    {
	      this.maxX = Region.getLocationStart().getX();
	      this.minX = Region.getLocationEnd().getX();
	    }
	    else
	    {
	      this.maxX = Region.getLocationEnd().getX();
	      this.minX = Region.getLocationStart().getX();
	    }
	    if (Region.getLocationStart().getY() >= Region.getLocationEnd().getY())
	    {
	      this.maxY = Region.getLocationStart().getY();
	      this.minY = Region.getLocationEnd().getY();
	    }
	    else
	    {
	      this.maxY = Region.getLocationEnd().getY();
	      this.minY = Region.getLocationStart().getY();
	    }
	    if (Region.getLocationStart().getZ() >= Region.getLocationEnd().getZ())
	    {
	      this.maxZ = Region.getLocationStart().getZ();
	      this.minZ = Region.getLocationEnd().getZ();
	    }
	    else
	    {
	      this.maxZ = Region.getLocationEnd().getZ();
	      this.minZ = Region.getLocationStart().getZ();
	    }
	    if (this.target.getGameMode() == GameMode.SPECTATOR) {
	      return;
	    }
	    if ((this.target.getLocation().getX() < this.minX) || (this.target.getLocation().getX() > this.maxX) || 
	      (this.target.getLocation().getY() < this.minY) || (this.target.getLocation().getY() > this.maxY) || 
	      (this.target.getLocation().getZ() < this.minZ) || (this.target.getLocation().getZ() > this.maxZ)) {
	      new syslife(this.target, this.ejecter);
	    }
	  }
	}