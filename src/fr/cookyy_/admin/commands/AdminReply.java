package fr.cookyy_.admin.commands;

import java.util.Map;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminReply implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage("§cOnly players can msg to other players.");
			return true;
		}
		Player p = (Player)sender;
		if (label.equalsIgnoreCase("reply") || label.equalsIgnoreCase("r"))
		{
			if (args.length == 0)
			{
				p.sendMessage("§cCurrent usage: /reply <message>");
				return true;
			}
			Player target = (Player)AdminMessage.lastMSG.get(p);
			if (target == null)
			{
				p.sendMessage("§cYou didn't send a message!");
				return true;
			}
			if (!AdminMessage.lastMSG.containsKey(target))
			{
				p.sendMessage("You can not reply, because you didn't send a message.");
				return true;
			}
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				str.append(args[i] + " ");
			}
			String msg = str.toString();
			target.sendMessage("§e" + p.getName() + "§6 --> §e" + target.getName() + "§c: " + msg);
			p.sendMessage("§e" + target.getName() + "§6 --> §e" + p.getName() + "§c: " + msg);
		}
		return true;
	}
}

