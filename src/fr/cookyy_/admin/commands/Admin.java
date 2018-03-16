package fr.cookyy_.admin.commands;

import fr.cookyy_.admin.AdminSystemMain;
import fr.cookyy_.admin.listeners.OtherGUI;
import fr.cookyy_.admin.listeners.PlayerGUI;
import fr.cookyy_.admin.listeners.ServerGUI;
import fr.cookyy_.admin.listeners.WorldGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Admin
implements Listener, CommandExecutor
{

	public static void openAdminGUI(Player player)
	{
		Inventory other = Bukkit.createInventory(null, 27, AdminSystemMain.configFile.getString("Prefix.Inventory"));

		ItemStack server = new ItemStack(Material.REDSTONE);
		ItemMeta serverm = server.getItemMeta();
		serverm.setDisplayName(AdminSystemMain.configFile.getString("Item.GUI.Server"));
		server.setItemMeta(serverm);

		ItemStack world = new ItemStack(Material.WATCH);
		ItemMeta worldm = world.getItemMeta();
		worldm.setDisplayName(AdminSystemMain.configFile.getString("Item.GUI.World"));
		world.setItemMeta(worldm);

		ItemStack playerp = new ItemStack(Material.ARMOR_STAND);
		ItemMeta playerpm = playerp.getItemMeta();
		playerpm.setDisplayName(AdminSystemMain.configFile.getString("Item.GUI.Player"));
		playerp.setItemMeta(playerpm);

		ItemStack others = new ItemStack(Material.STICK);
		ItemMeta othersm = others.getItemMeta();
		othersm.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
		othersm.setDisplayName(AdminSystemMain.configFile.getString("Item.GUI.Others"));
		others.setItemMeta(othersm);

		ItemStack exit = new ItemStack(Material.BARRIER);
		ItemMeta exitmeta = exit.getItemMeta();
		exitmeta.setDisplayName("§c§lClose");
		exit.setItemMeta(exitmeta);

		ItemStack nop = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)0);
		ItemMeta nopmeta = nop.getItemMeta();
		nopmeta.setDisplayName("§cThis is useless");
		nop.setItemMeta(nopmeta);

		other.setItem(10, server);
		other.setItem(12, world);
		other.setItem(14, playerp);
		other.setItem(16, others);
		other.setItem(22, exit);

		for (int i = 0; i < other.getContents().length; i++)
		{
			ItemStack is = other.getItem(i);
			if ((is == null) || (is.getType() == Material.AIR)) {
				other.setItem(i, nop);
			}
		}
		player.openInventory(other);
	}

	@EventHandler
	public void click(InventoryClickEvent event)
	{
		if (!event.getInventory().getName().equalsIgnoreCase(AdminSystemMain.configFile.getString("Prefix.Inventory"))) {
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
			ServerGUI.openServerGUI(p);
			break;
		case WATCH: 
			WorldGUI.openWorldGUI(p);
			break;
		case ARMOR_STAND: 
			PlayerGUI.openPlayerGUI(p);
			break;
		case STICK: 
			OtherGUI.openOtherGUI(p);
			break;
		case BARRIER: 
			p.closeInventory();
			break;
		}
	}


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg3)
	{
		Player p = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("admin"))
		{
			if (!(sender instanceof Player))
			{
				sender.sendMessage(AdminSystemMain.messageFile.getString("Message.Commands"));
				return false;
			}
			if (!sender.hasPermission("adminfr.admin"))
			{
				sender.sendMessage(AdminSystemMain.messageFile.getString("Message.Permission"));
				return false;
			}
			if (sender.isOp()) {
				openAdminGUI((Player)sender);
			}
		}
		return false;
	}
}
