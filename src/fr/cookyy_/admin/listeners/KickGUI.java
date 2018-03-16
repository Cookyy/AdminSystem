package fr.cookyy_.admin.listeners;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.cookyy_.admin.AdminSystemMain;

public class KickGUI implements Listener {

	public static ArrayList<String> kick = new ArrayList();

	public static void openOnlineKickGUI(Player player)
	{
		Inventory kick = Bukkit.createInventory(null, 54, AdminSystemMain.configFile.getString("Item.Players.Item.Kicked"));

		int slot = 0;
		for (Player t : Bukkit.getServer().getOnlinePlayers())
		{
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
			SkullMeta meta = (SkullMeta)skull.getItemMeta();
			meta.setOwner(t.getName());
			skull.setItemMeta(meta);
			meta.setDisplayName(t.getName());
			skull.setItemMeta(meta);
			kick.setItem(slot, skull);
			slot = 1;
		}
		player.openInventory(kick);
	}

	@EventHandler
	public void onClickKickOnline(InventoryClickEvent event)
	{
		if (!event.getInventory().getName().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.Players.Item.Kicked"))) {
			return;
		}
		Player p = (Player)event.getWhoClicked();
		event.setCancelled(true);
		if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType() == Material.AIR) || (!event.getCurrentItem().hasItemMeta()))
		{
			p.closeInventory();

			return;
		}
	}

	@EventHandler
	public void clickplkick(InventoryClickEvent event)
	{
		if (!event.getInventory().getName().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.Players.Item.Kicked"))) {
			return;
		}
		Player p = (Player)event.getWhoClicked();
		event.setCancelled(true);
		if ((event.getCurrentItem() == null) || (event.getCurrentItem().getType() == Material.AIR) || (!event.getCurrentItem().hasItemMeta()))
		{
			p.closeInventory();
			return;
		}
		switch (event.getCurrentItem().getType())
		{
		case SKULL_ITEM: 
			kick.add(event.getCurrentItem().getItemMeta().getDisplayName());
			Bukkit.broadcastMessage(event.getCurrentItem().getItemMeta().getDisplayName());
			Iterator localIterator = Bukkit.getServer().getOnlinePlayers().iterator();
			for (;;)
			{
				Player t = (Player)localIterator.next();
				if (kick.contains(t.getName()))
				{
					t.kickPlayer("§cYou were kicked by " + ChatColor.AQUA + p.getName());
					kick.remove(t.getName());
				}
				if (!localIterator.hasNext()) {
					break;
				}
			}
		}
	}


}
