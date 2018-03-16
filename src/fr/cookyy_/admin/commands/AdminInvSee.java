package fr.cookyy_.admin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cookyy_.admin.AdminSystemMain;

public class AdminInvSee
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (label.equalsIgnoreCase("admininvsee")) {
      if (sender.hasPermission("adminfr.admininvsee"))
      {
        if (args.length == 1)
        {
          if ((sender instanceof Player))
          {
            String name = args[0];
            if (Bukkit.getPlayer(name) == null)
            {
              sender.sendMessage("§cPlayer is offline !");
              return false;
            }
            Player target = Bukkit.getPlayer(name);
            Player player = (Player)sender;
            if (player == target)
            {
              player.sendMessage(AdminSystemMain.configFile.getString("Prefix.Inventory") + "§cYou can not look at your own inventory!");
              return true;
            }
            player.openInventory(target.getInventory());
            
            return true;
          }
          sender.sendMessage("§cYou must be a player !");
        }
        else
        {
          sender.sendMessage(AdminSystemMain.configFile.getString("Prefix.Inventory") +"§c/admininvsee <player>");
        }
      }
      else {
        sender.sendMessage("§cYou do not have permissions !");
      }
    }
    return false;
  }
}
