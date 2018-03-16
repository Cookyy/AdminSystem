package fr.cookyy_.admin.utils;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class ConfigFile
  extends SpigotFile
{
  public ConfigFile(File datafolder, String name)
  {
    super(datafolder, name);
    addDefault("Prefix.Inventory", "&b&lAdminFR › ");
    addDefault("Prefix.Staff", "&4AdminStaff › ");
    addDefault("Prefix.Hat", "&6&lAdminHats › ");
    addDefault("Prefix.Mute", "&cAdminMute › ");
    addDefault("Prefix.HideShow", "&aHide &f/ &cShow &f› ");

    addDefault("Item.GUI.Server", "&cServer");
    addDefault("Item.GUI.World", "&aWorld");
    addDefault("Item.GUI.Player", "&6Player");
    addDefault("Item.GUI.Others", "&2Others");
    addDefault("Item.GUI.Vanish", "&6Vanish");
    addDefault("Item.GUI.Players", "&5Players");
    
    addDefault("Item.Server.Item.Stop", "&4&lStop server");
    addDefault("Item.Server.Item.Reload", "&3Reload");
    addDefault("Item.Server.Item.WhiteList", "&f&lWhitelist");
    addDefault("Item.Server.Item.Difficulty", "&aDifficulty");
    
    addDefault("Item.World.Item.KickAll", "&cKick all connected");
    addDefault("Item.World.Item.KillAll", "&7Kill everyone");
    addDefault("Item.World.Item.Day", "&aDay");
    addDefault("Item.World.Item.Night", "&7Night");
    
    addDefault("Item.Player.Item.HealFeed", "&aHeal &f/ &cFeed");
    addDefault("Item.Player.Item.Player", "&5Players");
    addDefault("Item.Player.Item.GameMode", "&aGameMode");
    
    addDefault("Item.Players.Item.Banned", "&4Player Banned");
    addDefault("Item.Players.Item.Kicked", "&3Player Kicked");

    addDefault("Item.Others.Item.ClearInventory", "&7Clear Inventory");
    addDefault("Item.Others.Item.ClearChat", "&cClear chat");
            
    addDefault("Item.Vanish.Item.Show", "&aShow Player");
    addDefault("Item.Vanish.Item.Hide", "&cHide Player");
    
    addDefault("ItemJoin.Name", "&bAdminFR");
    addDefault("Item.GlassPanel.Name", "&cThis is useless");
    addDefault("Item.Barrier.Name", "&c&lClose");
    addDefault("Item.NetherStar.Name", "&cReturn");
  }
}
