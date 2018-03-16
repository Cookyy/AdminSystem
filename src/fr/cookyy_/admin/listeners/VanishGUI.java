package fr.cookyy_.admin.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.cookyy_.admin.AdminSystemMain;
import fr.cookyy_.admin.commands.Admin;

public class VanishGUI
implements Listener
{
	ArrayList<Player> hidden = new ArrayList();

	public static void openVanishGUI(Player player)
	{
		Inventory hf = Bukkit.createInventory(null, 9, AdminSystemMain.configFile.getString("Prefix.HideShow"));

		ItemStack heal = new ItemStack(Material.REDSTONE);
		ItemMeta healmeta = heal.getItemMeta();
		healmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.Vanish.Item.Show"));
		heal.setItemMeta(healmeta);

		ItemStack feed = new ItemStack(Material.SUGAR);
		ItemMeta feedmeta = feed.getItemMeta();
		feedmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.Vanish.Item.Hide"));
		feed.setItemMeta(feedmeta);

		ItemStack nop = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
		ItemMeta nopmeta = nop.getItemMeta();
		nopmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.GlassPanel.Name"));
		nop.setItemMeta(nopmeta);

		hf.setItem(1, heal);
		hf.setItem(7, feed);
		for (int i = 0; i < hf.getContents().length; i++)
		{
			ItemStack is = hf.getItem(i);
			if ((is == null) || (is.getType() == Material.AIR)) {
				hf.setItem(i, nop);
			}
		}
		player.openInventory(hf);
	}

	@EventHandler
	public void click(InventoryClickEvent event)
	{
		if (!event.getInventory().getName().equalsIgnoreCase(AdminSystemMain.configFile.getString("Prefix.HideShow"))) {
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
			p.sendMessage(AdminSystemMain.configFile.getString("Prefix.HideShow") + "§aShow Player");
			this.hidden.remove(p);
			for (Player player : Bukkit.getOnlinePlayers()) {
			p.showPlayer(p);
			p.closeInventory();
			}
			break;
			
		case SUGAR: 
			p.sendMessage(AdminSystemMain.configFile.getString("Prefix.HideShow") + "§cHide Player");
			this.hidden.add(p);
			for (Player player : Bukkit.getOnlinePlayers()) {
			p.hidePlayer(p);
			p.closeInventory();
			}			
			break;
		
		case NETHER_STAR: 
			Admin.openAdminGUI(p);
			break;
			
		case STAINED_GLASS_PANE: 
			p.closeInventory();
				}
		}
}
