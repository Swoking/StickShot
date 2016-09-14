package fr.dinnerwolph.stickshoot.listener.player;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.dinnerwolph.stickshoot.utils.kill;

public class Damage implements Listener {

	Entity entity;
	Entity damager;

	@EventHandler
	public void onDamageEven(EntityDamageByEntityEvent event) {
		this.entity = event.getEntity();
		this.damager = event.getDamager();

		if (this.entity instanceof Player && this.damager instanceof Player) {
			kill.setLastDamage((Player) entity, (Player) damager);
			event.setDamage(0.0);
		}
		if (this.entity instanceof Player && event.getCause()== DamageCause.PROJECTILE) {
			Arrow arrow = (Arrow) damager;
			kill.setLastDamage((Player) entity, (Player) arrow.getShooter());
			event.setDamage(0.0);
		}
	}

	@EventHandler
	public void onDamageEvent(EntityDamageEvent event) {
		if (event.getCause() == DamageCause.FALL)
			event.setCancelled(true);
		if (event.getCause() == DamageCause.BLOCK_EXPLOSION)
			event.setCancelled(true);
	}
}
