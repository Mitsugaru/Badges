package com.mitsugaru.badges.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.mitsugaru.badges.Badges;
import com.mitsugaru.badges.config.PlayerConfig;
import com.mitsugaru.badges.config.RootConfig;
import com.mitsugaru.badges.services.BadgeCommand;
import com.mitsugaru.badges.services.PermissionNode;

/**
 * Command to reload configuration and settings.
 */
public class ReloadCommand implements BadgeCommand {

   @Override
   public boolean execute(Badges plugin, CommandSender sender, Command command, String label, String[] args) {
      // Check permissions
      if(sender.hasPermission(PermissionNode.ADMIN.getNode())) {
         // Reload configuration
         RootConfig root = plugin.getModuleForClass(RootConfig.class);
         PlayerConfig player = plugin.getModuleForClass(PlayerConfig.class);
         root.reload();
         player.reload();

         // Notify sender
         sender.sendMessage(ChatColor.GREEN + Badges.TAG + " Configuration reloaded.");
      } else {
         sender.sendMessage(ChatColor.RED + Badges.TAG + " Lack permission: " + ChatColor.GRAY + PermissionNode.ADMIN.getNode());
      }
      return true;
   }

}
