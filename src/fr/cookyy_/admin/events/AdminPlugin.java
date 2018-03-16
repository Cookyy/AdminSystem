package fr.cookyy_.admin.events;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import fr.cookyy_.admin.AdminSystemMain;

public class AdminPlugin
implements Listener
{
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent cm)
	{
		String cmd = cm.getMessage().toLowerCase();
		Player p = cm.getPlayer();
		if ((cmd.startsWith("/pl")) || 
				(cmd.startsWith("/?")) || 
				(cmd.startsWith("/plugins")) || 
				(cmd.startsWith("/help")) || 
				(cmd.startsWith("/bukkit:?")) ||
				(cmd.startsWith("/bukkit:plugins")) || 
				(cmd.startsWith("/bukkit:plugins")) ||
				(cmd.startsWith("/bukkit:help")) || 
				(cmd.startsWith("/bukkit:pl")))
		{
			if (!p.hasPermission("adminfr.pl"))
			{
				cm.setCancelled(true);
				p.sendMessage(AdminSystemMain.configFile.getString("Prefix.Inventory") + "§c§lYou are not allowed to use this command");
			}
			else
			{
				cm.setCancelled(false);
			}
			return;
		}
	}
}
