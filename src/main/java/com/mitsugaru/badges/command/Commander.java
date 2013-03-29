package com.mitsugaru.badges.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.mitsugaru.badges.Badges;
import com.mitsugaru.badges.services.CommandHandler;

/**
 * Main command handler.
 */
public class Commander extends CommandHandler {

   /**
    * Constructor.
    * 
    * @param plugin
    *           - Plugin instance.
    */
   public Commander(Badges plugin) {
      super(plugin, "badge");

      // Register commands
      registerCommand("reload", new ReloadCommand());
   }

   @Override
   public boolean noArgs(CommandSender sender, Command command, String label) {
      // TODO show help menu
      return true;
   }

   @Override
   public boolean unknownCommand(CommandSender sender, Command command, String label, String[] args) {
      sender.sendMessage(ChatColor.RED + Badges.TAG + " Unknown command: " + ChatColor.GRAY + args[0]);
      return true;
   }

}
