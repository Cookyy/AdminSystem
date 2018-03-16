package fr.cookyy_.admin;

import java.util.logging.Logger;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_11_R1.ServerPing;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.cookyy_.admin.commands.Admin;
import fr.cookyy_.admin.commands.AdminClearInventory;
import fr.cookyy_.admin.commands.AdminHat;
import fr.cookyy_.admin.commands.AdminHelp;
import fr.cookyy_.admin.commands.AdminInvSee;
import fr.cookyy_.admin.commands.AdminMessage;
import fr.cookyy_.admin.commands.AdminMute;
import fr.cookyy_.admin.commands.AdminReply;
import fr.cookyy_.admin.events.AdminOnPlayer;
import fr.cookyy_.admin.events.AdminPlugin;
import fr.cookyy_.admin.listeners.BanGUI;
import fr.cookyy_.admin.listeners.DifficultyGUI;
import fr.cookyy_.admin.listeners.GameModeGUI;
import fr.cookyy_.admin.listeners.HealFeedGUI;
import fr.cookyy_.admin.listeners.KickGUI;
import fr.cookyy_.admin.listeners.OtherGUI;
import fr.cookyy_.admin.listeners.PlayerGUI;
import fr.cookyy_.admin.listeners.PlayersGUI;
import fr.cookyy_.admin.listeners.PlayerWhitelist;
import fr.cookyy_.admin.listeners.ServerGUI;
import fr.cookyy_.admin.listeners.VanishGUI;
import fr.cookyy_.admin.listeners.WhiteListGUI;
import fr.cookyy_.admin.listeners.WorldGUI;
import fr.cookyy_.admin.utils.ChatUtils;
import fr.cookyy_.admin.utils.ConfigFile;
import fr.cookyy_.admin.utils.MessageFile;
import fr.cookyy_.admin.utils.SpigotFile;

public class AdminSystemMain<ProxyPingEvent>
extends JavaPlugin
{
	public static String prefix = ChatColor.translateAlternateColorCodes('&', "&b&lAdminFR › ");
	public static String prefixstaff = ChatColor.translateAlternateColorCodes('&', "&4AdminStaff › ");

	public static String prefixhat = ChatColor.translateAlternateColorCodes('&', "&2&lAdminHats › ");
	public static String prefixmute = ChatColor.translateAlternateColorCodes('&', "&cAdminMute › ");
	public static String prefixhideshow = ChatColor.translateAlternateColorCodes('&', "&aHide &f/ &cShow &f› ");
	public static ConfigFile configFile;
	public static MessageFile messageFile; 
	private static AdminSystemMain plugin;

	public void onEnable()
	{
		configFile = new ConfigFile(getDataFolder(), "config");
		messageFile = new MessageFile(getDataFolder(), "message");
		prefix = configFile.getString("Prefix.Inventory");
		Bukkit.getConsoleSender().sendMessage(prefix + "§aenable ! " + "§c" +  getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage(prefix + "§bOne commande §c/admin <3");
		plugin = this;
		registerCommands();
		registerEvents();
	}

	public static AdminSystemMain getInstance()
	{
		return plugin;
	}

	public void registerCommands()
	{
		getCommand("admin").setExecutor(new Admin());
		getCommand("adminhelp").setExecutor(new AdminHelp());
		getCommand("adminclear").setExecutor(new AdminClearInventory());
		getCommand("adminhat").setExecutor(new AdminHat());
		getCommand("adminmute").setExecutor(new AdminMute());
		getCommand("admininvsee").setExecutor(new AdminInvSee());
		getCommand("msg").setExecutor(new AdminMessage());
		getCommand("reply").setExecutor(new AdminReply());

	}


	public void registerEvents()
	{
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new Admin(), this);
		pm.registerEvents(new AdminHelp(), this);
		pm.registerEvents(new AdminMute(), this);
		pm.registerEvents(new AdminOnPlayer(), this);
		pm.registerEvents(new AdminPlugin(), this);
		pm.registerEvents(new BanGUI(), this);
		pm.registerEvents(new ChatUtils(), this);
		pm.registerEvents(new DifficultyGUI(), this);
		pm.registerEvents(new GameModeGUI(), this);
		pm.registerEvents(new KickGUI(), this);
		pm.registerEvents(new HealFeedGUI(), this);
		pm.registerEvents(new OtherGUI(), this);
		pm.registerEvents(new PlayerGUI(), this);
		pm.registerEvents(new PlayersGUI(), this);
		pm.registerEvents(new PlayerWhitelist(), this);
		pm.registerEvents(new ServerGUI(), this);
		pm.registerEvents(new VanishGUI(), this);
		pm.registerEvents(new WhiteListGUI(), this);
		pm.registerEvents(new WorldGUI(), this);

	}

	public void onDisable()
	{
		Bukkit.getConsoleSender().sendMessage(prefix + "§cdisabled !");
	}
}
