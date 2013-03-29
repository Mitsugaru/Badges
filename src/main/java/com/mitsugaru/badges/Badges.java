package com.mitsugaru.badges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.mitsugaru.badges.command.Commander;
import com.mitsugaru.badges.config.PlayerConfig;
import com.mitsugaru.badges.config.RootConfig;
import com.mitsugaru.badges.config.RootConfigNode;
import com.mitsugaru.badges.module.VaultModule;
import com.mitsugaru.badges.services.IModule;

/**
 * Main plugin class for Badges.
 */
public class Badges extends JavaPlugin {

   /**
    * Plugin tag.
    */
   public static final String TAG = "[Badges]";

   /**
    * Modules.
    */
   private final Map<Class<? extends IModule>, IModule> modules = new HashMap<Class<? extends IModule>, IModule>();

   @Override
   public void onDisable() {
      // Deregister all modules.
      List<Class<? extends IModule>> clazzez = new ArrayList<Class<? extends IModule>>();
      clazzez.addAll(modules.keySet());
      for(Class<? extends IModule> clazz : clazzez) {
         this.deregisterModuleForClass(clazz);
      }
   }

   @Override
   public void onEnable() {
      // Register configuration modules
      RootConfig config = new RootConfig(this);
      registerModule(RootConfig.class, config);
      registerModule(PlayerConfig.class, new PlayerConfig(this));

      //TODO tab list

      setupVault(config.getBoolean(RootConfigNode.DEBUG_VAULT));

      // Create command handler
      this.getCommand("badge").setExecutor(new Commander(this));
   }

   private void setupVault(boolean debug) {
      if(debug) {
         this.getLogger().info("Setting up with Vault");
      }
      // Check vault
      if(getServer().getPluginManager().getPlugin("Vault") == null) {
         if(debug) {
            this.getLogger().info("Vault not found.");
         }
         return;
      } else if(debug) {
         this.getLogger().info("Vault found.");
      }

      RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
      Permission perm = rsp.getProvider();
      if(debug && perm != null) {
         this.getLogger().info("Found permissions provider: " + perm.getName());
      } else if(debug) {
         this.getLogger().info("Permissions null.");
      }

      // Register module
      VaultModule module = new VaultModule(this, perm);
      registerModule(VaultModule.class, module);
   }

   /**
    * Register a module to the API.
    * 
    * @param clazz
    *           - Class of the instance.
    * @param module
    *           - Module instance.
    * @throws IllegalArgumentException
    *            - Thrown if an argument is null.
    */
   public <T extends IModule> void registerModule(Class<T> clazz, T module) {
      // Check arguments.
      if(clazz == null) {
         throw new IllegalArgumentException("Class cannot be null");
      } else if(module == null) {
         throw new IllegalArgumentException("Module cannot be null");
      } else if(modules.containsKey(clazz)) {
         this.getLogger().warning("Overwriting module for class: " + clazz.getName());
      }
      // Add module.
      modules.put(clazz, module);
      // Tell module to start.
      module.starting();
   }

   /**
    * Unregister a module from the API.
    * 
    * @param clazz
    *           - Class of the instance.
    * @return Module that was removed from the API. Returns null if no instance
    *         of the module is registered with the API.
    */
   public <T extends IModule> T deregisterModuleForClass(Class<T> clazz) {
      // Check arguments.
      if(clazz == null) {
         throw new IllegalArgumentException("Class cannot be null");
      }
      // Grab module and tell it its closing.
      T module = clazz.cast(modules.get(clazz));
      if(module != null) {
         module.closing();
      }
      return module;
   }

   /**
    * Retrieve a registered CCModule.
    * 
    * @param clazz
    *           - Class identifier.
    * @return Module instance. Returns null is an instance of the given class
    *         has not been registered with the API.
    */
   public <T extends IModule> T getModuleForClass(Class<T> clazz) {
      return clazz.cast(modules.get(clazz));
   }
}
