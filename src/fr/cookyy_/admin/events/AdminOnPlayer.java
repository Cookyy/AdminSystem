package fr.cookyy_.admin.events;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import fr.cookyy_.admin.AdminSystemMain;
import fr.cookyy_.admin.commands.Admin;
import fr.cookyy_.admin.listeners.VanishGUI;

public class AdminOnPlayer
implements Listener
{
	ArrayList<Player> hidden = new ArrayList();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		if (event.getPlayer().hasPermission("adminfr.admin"))
		{
			Player player = event.getPlayer();
			event.getPlayer().getInventory();
			
			ItemStack join = new ItemStack(Material.NETHER_STAR, 1);
		    ItemMeta joinm = join.getItemMeta();
		    joinm.addEnchant(Enchantment.DAMAGE_ALL, 2, false);
		    joinm.setDisplayName(AdminSystemMain.configFile.getString("ItemJoin.Name"));
		    join.setItemMeta(joinm);

			ItemStack vanish = new ItemStack(Material.BLAZE_ROD);
			ItemMeta vanishm = vanish.getItemMeta();
			vanishm.setDisplayName(AdminSystemMain.configFile.getString("Prefix.HideShow"));
			ArrayList<String> lore2 = new ArrayList();
			lore2.add("§7Right click to open");
			vanishm.setLore(lore2);
			vanish.setItemMeta(vanishm);

			player.getInventory().setItem(4, join);
			player.getInventory().setItem(8, vanish);

			for (Player p : this.hidden) {
				event.getPlayer().hidePlayer(p);
			}						
		}

	}

	@EventHandler
	public void interact(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if ((player.getInventory().getItemInHand().getType() == Material.NETHER_STAR) && (
				(event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.LEFT_CLICK_AIR))) {
			Admin.openAdminGUI(player);
		}
		if ((player.getInventory().getItemInHand().getType() == Material.BLAZE_ROD) && (
				(event.getAction() == Action.RIGHT_CLICK_AIR || (event.getAction() == Action.LEFT_CLICK_AIR)))) {
			VanishGUI.openVanishGUI(player);
		}
	}

	@EventHandler
	  public void onJoin(PlayerJoinEvent e)
	  {
	    Player p = e.getPlayer();
	    e.setJoinMessage(AdminSystemMain.messageFile.getString("Message.OnJoin").replace("%player%", p.getName()));
	  }
	
	@EventHandler
	  public void onQuit(PlayerQuitEvent e)
	  {
	    Player p = e.getPlayer();
	    e.setQuitMessage(AdminSystemMain.messageFile.getString("Message.OnQuit").replace("%player%", p.getName()));
	  }
}
