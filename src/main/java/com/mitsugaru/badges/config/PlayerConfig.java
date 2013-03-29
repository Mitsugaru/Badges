package com.mitsugaru.badges.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.mitsugaru.badges.Badges;
import com.mitsugaru.badges.services.ModularConfig;

/**
 * Configuration handler for the players.yml.
 */
public class PlayerConfig extends ModularConfig<Badges> {

   /**
    * Reference to the file.
    */
   private final File file;
   /**
    * Yaml Configuration reference.
    */
   private final YamlConfiguration config;

   /**
    * Constructor.
    * 
    * @param plugin
    *           - Plugin instance.
    */
   public PlayerConfig(Badges plugin) {
      super(plugin);
      file = new File(plugin.getDataFolder().getAbsolutePath() + "/players.yml");
      config = YamlConfiguration.loadConfiguration(file);
   }

   @Override
   public void save() {
      // Set config
      try {
         // Save the file
         config.save(file);
      } catch(IOException e) {
         plugin.getLogger().log(Level.SEVERE, "File I/O Exception on saving player data", e);
      }
   }

   @Override
   public void set(String path, Object value) {
      config.set(path, value);
   }

   @Override
   public void reload() {
      try {
         config.load(file);
      } catch(FileNotFoundException e) {
         plugin.getLogger().log(Level.SEVERE, "Missing player data", e);
      } catch(IOException e) {
         plugin.getLogger().log(Level.SEVERE, "File I/O Exception on loading player data", e);
      } catch(InvalidConfigurationException e) {
         plugin.getLogger().log(Level.SEVERE, "Invalid player data", e);
      }
   }

   @Override
   public void loadSettings(ConfigurationSection config) {
      // Not going to keep anything in memory, let the config handle itself.
   }

   @Override
   public void loadDefaults(ConfigurationSection config) {
      // No defaults to put.
   }

   @Override
   public void boundsCheck() {
      // TODO maybe remove badges that are no longer recognized, AKA auto
      // cleanup.
   }

   // TODO add prefix / suffix methods

   /**
    * Get the currently selected tag for the given player.
    * 
    * @param world
    *           - World.
    * @param name
    *           - Player name.
    * @return Selected tag.
    */
   public String getPrefixForPlayer(String world, String name) {
      // TODO a default?
      return config.getString(name + ".current." + world + ".prefix", "");
   }

   /**
    * Set the prefix for the player.
    * 
    * @param world
    *           - World.
    * @param name
    *           - Player name.
    */
   public void setPrefixForPlayer(String world, String name, String prefix) {
      config.set(name + ".current." + world + ".prefix", prefix);
   }

   /**
    * Get the list of tags that are available to the player.
    * 
    * @param world
    *           - World.
    * @param name
    *           - Player name.
    * @return List of player tags.
    */
   public List<String> getPrefixesForPlayer(String world, String name) {
      // TODO grab the world specific lists as well
      return config.getStringList(name + ".tags.prefix");
   }

   /**
    * Get the currently selected tag for the given player.
    * 
    * @param world
    *           - World.
    * @param name
    *           - Player name.
    * @return Selected tag.
    */
   public String getSuffixForPlayer(String world, String name) {
      // TODO a default?
      return config.getString(name + ".current." + world + ".suffix", "");
   }
   
   /**
    * Set the prefix for the player.
    * 
    * @param world
    *           - World.
    * @param name
    *           - Player name.
    */
   public void setSuffixForPlayer(String world, String name, String suffix) {
      config.set(name + ".current." + world + ".prefix", suffix);
   }

   /**
    * Get the list of tags that are available to the player.
    * 
    * @param world
    *           - World.
    * @param name
    *           - Player name.
    * @return List of player tags.
    */
   public List<String> getSuffixesForPlayer(String world, String name) {
      // TODO grab the world specific lists as well
      return config.getStringList(name + ".tags.suffix");
   }

   @Override
   public void starting() {
   }

   @Override
   public void closing() {
      save();
   }

}
