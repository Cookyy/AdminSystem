package fr.cookyy_.admin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fr.cookyy_.admin.AdminSystemMain;
import fr.cookyy_.admin.commands.Admin;

public class PlayerGUI
implements Listener
{
	public static void openPlayerGUI(Player player)
	{
		Inventory adminp = Bukkit.createInventory(null, 27, AdminSystemMain.configFile.getString("Item.GUI.Player"));

		ItemStack healfeed = new ItemStack(Material.BREAD);
		ItemMeta healfeedmeta = healfeed.getItemMeta();
		healfeedmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.Player.Item.HealFeed"));
		healfeed.setItemMeta(healfeedmeta);

		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
		ItemMeta meta = skull.getItemMeta();
		meta.setDisplayName(AdminSystemMain.configFile.getString("Item.Player.Item.Player"));
		skull.setItemMeta(meta);

		ItemStack gm = new ItemStack(Material.GRASS);
		ItemMeta gmm = gm.getItemMeta();
		gmm.setDisplayName(AdminSystemMain.configFile.getString("Item.Player.Item.GameMode"));
		gm.setItemMeta(gmm);

		ItemStack main = new ItemStack(Material.NETHER_STAR);
		ItemMeta mainmeta = main.getItemMeta();
		mainmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.NetherStar.Name"));
		main.setItemMeta(mainmeta);

		ItemStack nop = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
		ItemMeta nopmeta = nop.getItemMeta();
		nopmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.GlassPanel.Name"));
		nop.setItemMeta(nopmeta);

		adminp.setItem(10, healfeed);
		adminp.setItem(13, skull);
		adminp.setItem(16, gm);
		adminp.setItem(22, main);
		for (int i = 0; i < adminp.getContents().length; i++)
		{
			ItemStack is = adminp.getItem(i);
			if ((is == null) || (is.getType() == Material.AIR)) {
				adminp.setItem(i, nop);
			}
		}
		player.openInventory(adminp);
	}

	@EventHandler
	public void click(InventoryClickEvent event)
	{
		if (!event.getInventory().getName().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.GUI.Player"))) {
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
		case BREAD: 
			HealFeedGUI.openHeelFeedGUI(p);
			break;
		case GRASS: 
			GameModeGUI.openGameModeGUI(p);
			break;
		case SKULL_ITEM:
			PlayersGUI.openPlayersGUI(p);
			break;
		case NETHER_STAR: 
			Admin.openAdminGUI(p);
			break;
		case STAINED_GLASS_PANE:
			p.closeInventory();
			break;
		}
	}
}
