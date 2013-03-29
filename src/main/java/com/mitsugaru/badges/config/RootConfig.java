package com.mitsugaru.badges.config;

import org.bukkit.configuration.ConfigurationSection;

import com.mitsugaru.badges.Badges;
import com.mitsugaru.badges.services.ModularConfig;

/**
 * Configuration handler for the root config.yml.
 */
public class RootConfig extends ModularConfig<Badges> {

   /**
    * Constructor.
    * 
    * @param plugin
    *           - Plugin instance.
    */
   public RootConfig(Badges plugin) {
      super(plugin);
   }

   @Override
   public void save() {
      plugin.saveConfig();
   }

   @Override
   public void set(String path, Object value) {
      plugin.getConfig().set(path, value);
      plugin.saveConfig();
   }

   @Override
   public void reload() {
      plugin.reloadConfig();
      loadSettings(plugin.getConfig());
   }

   @Override
   public void loadSettings(ConfigurationSection config) {
      for(final RootConfigNode node : RootConfigNode.values()) {
         updateOption(node);
      }
   }

   @Override
   public void loadDefaults(ConfigurationSection config) {
      for(final RootConfigNode node : RootConfigNode.values()) {
         if(!config.contains(node.getPath())) {
            config.set(node.getPath(), node.getDefaultValue());
         }
      }
   }

   @Override
   public void boundsCheck() {
   }

   @Override
   public void starting() {
      loadDefaults(plugin.getConfig());
      plugin.saveConfig();
      reload();
   }

   @Override
   public void closing() {
      plugin.saveConfig();
   }

}
