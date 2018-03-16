package fr.cookyy_.admin.utils;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.cookyy_.admin.AdminSystemMain;

public class ChatUtils
  implements Listener
{
  @EventHandler
  public void onChat(AsyncPlayerChatEvent e)
  {
    if ((e.getPlayer().hasPermission("adminfr.staff")) && 
      (e.getMessage().startsWith("!")))
    {
      e.setCancelled(true);
      for (Player pls : Bukkit.getServer().getOnlinePlayers()) {
        if (pls.hasPermission("adminfr.staff")) {
          pls.sendMessage(AdminSystemMain.configFile.getString("Prefix.Staff") + e.getPlayer().getName() + "§f : " + e.getMessage().replaceFirst("!", "§l"));
        }
      }
    }
  }
}
