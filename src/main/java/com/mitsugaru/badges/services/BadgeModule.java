package com.mitsugaru.badges.services;

import com.mitsugaru.badges.Badges;

/**
 * Represents a module used for the plugin.
 */
public abstract class BadgeModule implements IModule {

   /**
    * Plugin reference.
    */
   protected Badges plugin;

   /**
    * Constructor.
    * 
    * @param plugin
    *           - Plugin hook.
    */
   public BadgeModule(Badges plugin) {
      this.plugin = plugin;
   }

}
