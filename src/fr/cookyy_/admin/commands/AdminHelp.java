package fr.cookyy_.admin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import fr.cookyy_.admin.AdminSystemMain;

public class AdminHelp
  implements Listener, CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg3)
  {
    if (cmd.getName().equalsIgnoreCase("adminhelp"))
    {
      if (!(sender instanceof Player))
      {
        sender.sendMessage(AdminSystemMain.messageFile.getString("Message.Commands"));
        return false;
      }
      if (!sender.hasPermission("adminfr.adminhelp"))
      {
        sender.sendMessage(AdminSystemMain.messageFile.getString("Message.Permission"));
        return false;
      }
      Player p = (Player)sender;
      p.sendMessage("        §b" + "              " +AdminSystemMain.configFile.getString("Prefix.Inventory") + "");
      p.sendMessage("");
      p.sendMessage("§e/admin §f- §bOpen gui §f- §aadminfr.admin");
      p.sendMessage("§e/adminec §f- §bOpen Enderchest §f- §aadminfr.adminec");
      p.sendMessage("§e/admininvsee §f- §bInvSee §f- §aadminfr.admininvsee");
      p.sendMessage("§e/admininvsee <player> §f- §bInvSee Player §f- §aadminfr.admininvsee");
      p.sendMessage("§e/adminmute <player> §f- §bMute Player §f- §aadminfr.adminmute");
      p.sendMessage("§e/adminclear §f- §bClear Inventory §f- §aadminfr.clear");
      p.sendMessage("§e/adminclear <player> §f- §bClear Inventory Player §f- §aadminfr.clear");
      p.sendMessage("§e/adminhat §f- §bNews Hats §f- §cNo permissions");
      
      return true;
    }
    return false;
  }
}
