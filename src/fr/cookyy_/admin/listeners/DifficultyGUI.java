package fr.cookyy_.admin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
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

public class DifficultyGUI
  implements Listener
{
  public static void openDifficultyGUI(Player player)
  {
    Inventory difficulty = Bukkit.createInventory(null, 27, AdminSystemMain.configFile.getString("Item.Server.Item.Difficulty"));
    
    ItemStack dirt = new ItemStack(Material.DIRT);
    ItemMeta dirtmeta = dirt.getItemMeta();
    dirtmeta.setDisplayName("§fPeaceful");
    dirt.setItemMeta(dirtmeta);
    
    ItemStack grass = new ItemStack(Material.GRASS);
    ItemMeta grassmeta = grass.getItemMeta();
    grassmeta.setDisplayName("§7Easy");
    grass.setItemMeta(grassmeta);
       
    ItemStack wood = new ItemStack(Material.WOOD);
    ItemMeta woodmeta = wood.getItemMeta();
    woodmeta.setDisplayName("§aNormal");
    wood.setItemMeta(woodmeta);
    
    ItemStack soul = new ItemStack(Material.SOUL_SAND);
    ItemMeta soulmeta = soul.getItemMeta();
    soulmeta.setDisplayName("§4Hard");
    soul.setItemMeta(soulmeta);
    
    ItemStack apple = new ItemStack(Material.NETHER_STAR);
    ItemMeta applemeta = apple.getItemMeta();
    applemeta.setDisplayName(AdminSystemMain.configFile.getString("Item.Server.Item.Difficulty"));
    apple.setItemMeta(applemeta);
    
    ItemStack gl = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
    ItemMeta glmeta = gl.getItemMeta();
    glmeta.setDisplayName(AdminSystemMain.configFile.getString("Item.GlassPanel.Name"));
    gl.setItemMeta(glmeta);
    
    difficulty.setItem(10, dirt);
    difficulty.setItem(12, grass);
    difficulty.setItem(14, wood);
    difficulty.setItem(16, soul);
    difficulty.setItem(22, apple);
    for (int i = 0; i < difficulty.getContents().length; i++)
    {
      ItemStack is = difficulty.getItem(i);
      if ((is == null) || (is.getType() == Material.AIR)) {
        difficulty.setItem(i, gl);
      }
    }
    player.openInventory(difficulty);
  }
  
  @EventHandler
  public void clickDiff(InventoryClickEvent event)
  {
    if (!event.getInventory().getName().equalsIgnoreCase(AdminSystemMain.configFile.getString("Item.Server.Item.Difficulty"))) {
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
    case DIRT: 
      Bukkit.getServer().getWorld(p.getWorld().getName()).setDifficulty(Difficulty.PEACEFUL);
      p.sendMessage(AdminSystemMain.configFile.getString("Prefix.Inventory") + "§aDifficulty: §bPeaceful");
      p.closeInventory();
      openDifficultyGUI(p);
      break;
    case GRASS: 
      Bukkit.getServer().getWorld(p.getWorld().getName()).setDifficulty(Difficulty.EASY);
      p.sendMessage(AdminSystemMain.configFile.getString("Prefix.Inventory") + "§aDifficulty: §bEasy");
      p.closeInventory();
      openDifficultyGUI(p);
      break;
    case WOOD: 
      Bukkit.getServer().getWorld(p.getWorld().getName()).setDifficulty(Difficulty.NORMAL);
      p.sendMessage(AdminSystemMain.configFile.getString("Prefix.Inventory") + "§aDifficulty: §bNormal");
      p.closeInventory();
      openDifficultyGUI(p);
      break;
    case SOUL_SAND: 
      Bukkit.getServer().getWorld(p.getWorld().getName()).setDifficulty(Difficulty.HARD);
      p.sendMessage(AdminSystemMain.configFile.getString("Prefix.Inventory") + "§aDifficulty: §bHard");
      p.closeInventory();
      openDifficultyGUI(p);
      break;
    case NETHER_STAR: 
      ServerGUI.openServerGUI(p);
      break;
    case FROSTED_ICE: 
      p.closeInventory();
    }
  }
}
