package fr.cookyy_.admin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.cookyy_.admin.AdminSystemMain;

public class BanGUI
  implements Listener
{
  public static void openBanGUI(Player player)
  {
    Inventory ban = Bukkit.createInventory(null, 54, AdminSystemMain.configFile.getString("Item.Players.Item.Banned"));
    int slot = 0;
    for (OfflinePlayer t : Bukkit.getBannedPlayers())
    {
      ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
      ItemMeta meta = skull.getItemMeta();
      meta.setDisplayName(t.getName());
      skull.setItemMeta(meta);
      
      ban.setItem(slot, skull);
      slot++;
    }
    player.openInventory(ban);
  }
  
  @EventHandler
  public void click(InventoryClickEvent event)
  {
    if (!event.getInventory().getName().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.Players.Item.Banned"))) {
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
    case HARD_CLAY: 
      p.closeInventory();
    }
  }
}
