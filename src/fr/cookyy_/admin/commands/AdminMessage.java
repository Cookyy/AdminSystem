package fr.cookyy_.admin.commands;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class AdminMessage implements CommandExecutor {

	  public static Map<Player, Player> lastMSG = new HashMap();
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    if (!(sender instanceof Player))
	    {
	      sender.sendMessage("§cOnly players can msg to other players.");
	      return true;
	    }
	    Player p = (Player)sender;
	    if (label.equalsIgnoreCase("msg") || label.equalsIgnoreCase("m"))
	    {
	      if (args.length == 0)
	      {
	        p.sendMessage("§cCurrent usage: /msg <player> <message>");
	        return true;
	      }
	      Player target = Bukkit.getPlayer(args[0]);
	      if (target == null)
	      {
	        p.sendMessage("§cThis player: §e" + args[0] + "§c doesn't online/exists!");
	        return true;
	      }
	      if (p == target)
	      {
	        p.sendMessage("§cYou cannot send message to yourself!");
	        return true;
	      }
	      StringBuilder str = new StringBuilder();
	      for (int i = 1; i < args.length; i++) {
	        str.append(args[i] + " ");
	      }
	      String msg = str.toString();
	      target.sendMessage("§e" + p.getName() + "§6 --> §e" + target.getName() + "§c: " + msg);
	      p.sendMessage("§e" + target.getName() + "§6 --> §e" + p.getName() + "§c: " + msg);
	      lastMSG.put(target, p);
	    }
	    return true;
	  }
	}
