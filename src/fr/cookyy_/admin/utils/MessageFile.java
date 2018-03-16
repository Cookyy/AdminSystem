package fr.cookyy_.admin.utils;

import java.io.File;

public class MessageFile
  extends SpigotFile
{
  public MessageFile(File datafolder, String name)
  {
    super(datafolder, name);
    addDefault("Message.OnJoin", "&8[&a+&8]&6 %player% &bjoined the server !");
    addDefault("Message.OnQuit", "&8[&c-&8]&6 %player% &bquit the server !");
    addDefault("Message.Permission", "&cYou do not have permission to perform this command");
    addDefault("Message.Commands", "&cYou must be a player to perform this command"); 
    
    addDefault("Message.MutePlayerOn", "&cYou have muted &f");
    addDefault("Message.MutePlayerOff", "&bYou have unmuted &f");
    
    addDefault("Message.PlayerMuteOn", "&cYou are muted by &6");
    addDefault("Message.PlayerMuteOff", "&aYou are unmuted by &6");
    
    
  }
}
