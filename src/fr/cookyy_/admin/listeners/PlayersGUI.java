package fr.cookyy_.admin.listeners;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fr.cookyy_.admin.AdminSystemMain;

public class PlayersGUI implements Listener {

	public static void openPlayersGUI(Player player)
	{
		Inventory manage = Bukkit.createInventory(null, 27, AdminSystemMain.configFile.getString("Item.GUI.Players"));

		ItemStack ban = new ItemStack(Material.REDSTONE);
		ItemMeta banm = ban.getItemMeta();
		banm.setDisplayName(AdminSystemMain.configFile.getString("Item.Players.Item.Banned"));
		ban.setItemMeta(banm);

		ItemStack kick = new ItemStack(Material.GLOWSTONE_DUST);
		ItemMeta kickm = kick.getItemMeta();
		kickm.setDisplayName(AdminSystemMain.configFile.getString("Item.Players.Item.Kicked"));
		kick.setItemMeta(kickm);

		ItemStack nop = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)0);
		ItemMeta nopmeta = nop.getItemMeta();
		nopmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.GlassPanel.Name"));
		nop.setItemMeta(nopmeta);

		manage.setItem(10, ban);
		manage.setItem(16, kick);
		for (int i = 0; i < manage.getContents().length; i++)
		{
			ItemStack is = manage.getItem(i);
			if ((is == null) || (is.getType() == Material.AIR)) {
				manage.setItem(i, nop);
			}
		}

		player.openInventory(manage);
	}

	@EventHandler
	public void clickManage(InventoryClickEvent event)
	{
		if (!event.getInventory().getName().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.GUI.Players"))) {
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
		case REDSTONE: 
			BanGUI.openBanGUI(p);
			break;
		case GLOWSTONE_DUST: 
			KickGUI.openOnlineKickGUI(p);
			break;
		}
	}
}

