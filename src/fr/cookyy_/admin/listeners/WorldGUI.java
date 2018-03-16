package fr.cookyy_.admin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.cookyy_.admin.AdminSystemMain;
import fr.cookyy_.admin.commands.Admin;

public class WorldGUI
implements Listener
{
	public static void openWorldGUI(Player player)
	{
		Inventory world = Bukkit.createInventory(null, 27, AdminSystemMain.configFile.getString("Item.GUI.World"));

		ItemStack kick = new ItemStack(Material.BLAZE_ROD);
		ItemMeta kickmeta = kick.getItemMeta();
		kickmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.World.Item.KickAll"));
		kick.setItemMeta(kickmeta);

		ItemStack kill = new ItemStack(Material.BONE);
		ItemMeta killmeta = kill.getItemMeta();
		killmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.World.Item.KillAll"));
		kill.setItemMeta(killmeta);

		ItemStack day = new ItemStack(Material.BUCKET);
		ItemMeta daymeta = day.getItemMeta();
		daymeta.setDisplayName(AdminSystemMain.configFile.getString("Item.World.Item.Day"));
		day.setItemMeta(daymeta);

		ItemStack night = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta nightmeta = night.getItemMeta();
		nightmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.World.Item.Night"));
		night.setItemMeta(nightmeta);

		ItemStack nop = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
		ItemMeta nopmeta = nop.getItemMeta();
		nopmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.GlassPanel.Name"));
		nop.setItemMeta(nopmeta);

		ItemStack main = new ItemStack(Material.NETHER_STAR);
		ItemMeta mainmeta = main.getItemMeta();
		mainmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.NetherStar.Name"));
		main.setItemMeta(mainmeta);

		world.setItem(10, kick);
		world.setItem(14, kill);
		world.setItem(12, day);
		world.setItem(16, night);
		world.setItem(22, main);
		for (int i = 0; i < world.getContents().length; i++)
		{
			ItemStack is = world.getItem(i);
			if ((is == null) || (is.getType() == Material.AIR)) {
				world.setItem(i, nop);
			}
		}
		player.openInventory(world);
	}

	@EventHandler
	public void click(InventoryClickEvent event)
	{
		if (!event.getInventory().getName().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.GUI.World"))) {
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
		case BLAZE_ROD: 
			for (Player playerp : Bukkit.getServer().getOnlinePlayers()) {
				playerp.kickPlayer("§cYou were kicked by admin");
			}
			break;
		case BONE: 
			for (Player health : Bukkit.getServer().getOnlinePlayers()) {
				health.setHealth(0.0D);
			}
			break;
		case BUCKET: 
			p.getWorld().setTime(1000L);
			p.sendMessage(AdminSystemMain.configFile.getString("Prefix.Inventory") + "§eIt's the day");
			openWorldGUI(p);
			break;
		case LAVA_BUCKET: 
			p.getWorld().setTime(14000L);
			p.sendMessage(AdminSystemMain.configFile.getString("Prefix.Inventory") + "§eIt's night");
			openWorldGUI(p);
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
