package fr.dinnerwolph.stickshoot.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class kill
{
  private static Map<Player, Player> eject = new HashMap<>();
  
  public static Map<Player, Player> geteject()
  {
    return eject;
  }
  
  public static void setLastDamage(Player target, Player damager)
  {
    if (eject.containsKey(target))
    {
      eject.remove(target);
      eject.put(target, damager);
    }
    else
    {
      eject.put(target, damager);
    }
  }
  
  public static Player getEjecter(Player target)
  {
    return (Player)eject.get(target);
  }
}
