package fr.cookyy_.admin.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import fr.cookyy_.admin.AdminSystemMain;

public class AdminHat
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command command, String arg2, String[] arg3)
  {
    if ((sender instanceof Player))
    {
      Player player = (Player)sender;
      PlayerInventory inv = player.getInventory();
      
      ItemStack held = player.getItemInHand();
      ItemStack helm = inv.getHelmet();
      if ((held.getAmount() == 1) || (held.getType() == Material.AIR))
      {
        inv.setHelmet(held);
        player.setItemInHand(helm);
        player.sendMessage(AdminSystemMain.configFile.getString("Prefix.Hat") + "§aNew Hats");
        player.updateInventory();
      }
      else
      {
        sender.sendMessage(AdminSystemMain.configFile.getString("Prefix.Hat") + "§cYour held item must have a stack size of 1.");
      }
    }
    else
    {
      sender.sendMessage(AdminSystemMain.configFile.getString("Prefix.Hat") + "You cannot execute this command from the console.");
    }
    return true;
  }
}
