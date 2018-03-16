package fr.cookyy_.admin.listeners;

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

public class HealFeedGUI
  implements Listener
{
  public static void openHeelFeedGUI(Player player)
  {
    Inventory hf = Bukkit.createInventory(null, 9, AdminSystemMain.configFile.getString("Item.Player.Item.HealFeed"));
    
    ItemStack heal = new ItemStack(Material.APPLE);
    ItemMeta healmeta = heal.getItemMeta();
    healmeta.setDisplayName("§aHeal");
    heal.setItemMeta(healmeta);
    
    ItemStack feed = new ItemStack(Material.COOKED_BEEF);
    ItemMeta feedmeta = feed.getItemMeta();
    feedmeta.setDisplayName("§cFeed");
    feed.setItemMeta(feedmeta);
    
    ItemStack main = new ItemStack(Material.NETHER_STAR);
    ItemMeta mainmeta = main.getItemMeta();
    mainmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.NetherStar.Name"));
    main.setItemMeta(mainmeta);
    
    ItemStack nop = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
    ItemMeta nopmeta = nop.getItemMeta();
    nopmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.GlassPanel.Name"));
    nop.setItemMeta(nopmeta);
    
    hf.setItem(1, heal);
    hf.setItem(4, main);
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
    if (!event.getInventory().getName().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.Player.Item.HealFeed"))) {
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
    case APPLE: 
      p.setHealth(20.0D);
      p.sendMessage(AdminSystemMain.configFile.getString("Prefix.Inventory") + "§aHeal");
      p.closeInventory();
      openHeelFeedGUI(p);
      break;
    case COOKED_BEEF: 
      p.setFoodLevel(20);
      p.sendMessage(AdminSystemMain.configFile.getString("Prefix.Inventory") + "§cFeed");
      p.closeInventory();
      openHeelFeedGUI(p);
      break;
    case NETHER_STAR: 
      Admin.openAdminGUI(p);
      break;
    case STAINED_GLASS_PANE: 
      p.closeInventory();
    }
  }
}
