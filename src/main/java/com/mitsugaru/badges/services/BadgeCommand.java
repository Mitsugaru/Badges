package com.mitsugaru.badges.services;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.mitsugaru.badges.Badges;

/**
 * Represents a command.
 * 
 * @param <T>
 *           Game plugin.
 */
public interface BadgeCommand {

   /**
    * Execution method for the command.
    * 
    * @param sender
    *           - Sender of the command.
    * @param command
    *           - Command used.
    * @param label
    *           - Label.
    * @param args
    *           - Command arguments.
    * @return True if valid command and executed. Else false.
    */
   boolean execute(final Badges plugin, final CommandSender sender, final Command command, final String label, String[] args);

}
