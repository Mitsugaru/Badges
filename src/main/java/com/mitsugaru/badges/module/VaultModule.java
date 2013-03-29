package com.mitsugaru.badges.module;

import java.util.Collection;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;

import com.mitsugaru.badges.Badges;
import com.mitsugaru.badges.config.PlayerConfig;
import com.mitsugaru.badges.services.IModule;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

public class VaultModule extends Chat implements IModule {

   private Badges plugin;

   private Chat alt;

   public VaultModule(Badges plugin, Permission perms) {
      super(perms);

      this.plugin = plugin;
   }

   @Override
   public boolean getGroupInfoBoolean(String arg0, String arg1, String arg2, boolean arg3) {
      return getAlternateProvider().getGroupInfoBoolean(arg0, arg1, arg2, arg3);
   }

   @Override
   public double getGroupInfoDouble(String arg0, String arg1, String arg2, double arg3) {
      return getAlternateProvider().getGroupInfoDouble(arg0, arg1, arg2, arg3);
   }

   @Override
   public int getGroupInfoInteger(String arg0, String arg1, String arg2, int arg3) {
      return getAlternateProvider().getGroupInfoInteger(arg0, arg1, arg2, arg3);
   }

   @Override
   public String getGroupInfoString(String arg0, String arg1, String arg2, String arg3) {
      return getAlternateProvider().getGroupInfoString(arg0, arg1, arg2, arg3);
   }

   @Override
   public String getGroupPrefix(String arg0, String arg1) {
      // TODO Auto-generated method stub
      return "gp";
   }

   @Override
   public String getGroupSuffix(String arg0, String arg1) {
      // TODO Auto-generated method stub
      return "gs";
   }

   @Override
   public String getName() {
      return "Badges";
   }

   @Override
   public boolean getPlayerInfoBoolean(String arg0, String arg1, String arg2, boolean arg3) {
      return getAlternateProvider().getPlayerInfoBoolean(arg0, arg1, arg2, arg3);
   }

   @Override
   public double getPlayerInfoDouble(String arg0, String arg1, String arg2, double arg3) {
      return getAlternateProvider().getPlayerInfoDouble(arg0, arg1, arg2, arg3);
   }

   @Override
   public int getPlayerInfoInteger(String arg0, String arg1, String arg2, int arg3) {
      return getAlternateProvider().getPlayerInfoInteger(arg0, arg1, arg2, arg3);
   }

   @Override
   public String getPlayerInfoString(String arg0, String arg1, String arg2, String arg3) {
      return getAlternateProvider().getPlayerInfoString(arg0, arg1, arg2, arg3);
   }

   @Override
   public String getPlayerPrefix(String world, String player) {
      PlayerConfig config = plugin.getModuleForClass(PlayerConfig.class);
      return config.getPrefixForPlayer(world, player);
   }

   @Override
   public String getPlayerSuffix(String world, String player) {
      PlayerConfig config = plugin.getModuleForClass(PlayerConfig.class);
      return config.getSuffixForPlayer(world, player);
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public void setGroupInfoBoolean(String arg0, String arg1, String arg2, boolean arg3) {
      getAlternateProvider().setGroupInfoBoolean(arg0, arg1, arg2, arg3);
   }

   @Override
   public void setGroupInfoDouble(String arg0, String arg1, String arg2, double arg3) {
      getAlternateProvider().setGroupInfoDouble(arg0, arg1, arg2, arg3);
   }

   @Override
   public void setGroupInfoInteger(String arg0, String arg1, String arg2, int arg3) {
      getAlternateProvider().setGroupInfoInteger(arg0, arg1, arg2, arg3);
   }

   @Override
   public void setGroupInfoString(String arg0, String arg1, String arg2, String arg3) {
      getAlternateProvider().setGroupInfoString(arg0, arg1, arg2, arg3);
   }

   @Override
   public void setGroupPrefix(String arg0, String arg1, String arg2) {
      // TODO Auto-generated method stub

   }

   @Override
   public void setGroupSuffix(String arg0, String arg1, String arg2) {
      // TODO Auto-generated method stub

   }

   @Override
   public void setPlayerInfoBoolean(String arg0, String arg1, String arg2, boolean arg3) {
      getAlternateProvider().setPlayerInfoBoolean(arg0, arg1, arg2, arg3);
   }

   @Override
   public void setPlayerInfoDouble(String arg0, String arg1, String arg2, double arg3) {
      getAlternateProvider().setPlayerInfoDouble(arg0, arg1, arg2, arg3);
   }

   @Override
   public void setPlayerInfoInteger(String arg0, String arg1, String arg2, int arg3) {
      getAlternateProvider().setPlayerInfoInteger(arg0, arg1, arg2, arg3);
   }

   @Override
   public void setPlayerInfoString(String arg0, String arg1, String arg2, String arg3) {
      getAlternateProvider().setPlayerInfoString(arg0, arg1, arg2, arg3);
   }

   @Override
   public void setPlayerPrefix(String world, String player, String prefix) {
      PlayerConfig config = plugin.getModuleForClass(PlayerConfig.class);
      config.setPrefixForPlayer(world, player, prefix);
   }

   @Override
   public void setPlayerSuffix(String world, String player, String suffix) {
      PlayerConfig config = plugin.getModuleForClass(PlayerConfig.class);
      config.setSuffixForPlayer(world, player, suffix);
   }

   @Override
   public void starting() {
      // TODO figure out what priority is necessary
      plugin.getServer().getServicesManager().register(Chat.class, this, plugin, ServicePriority.Highest);
   }

   @Override
   public void closing() {
      plugin.getServer().getServicesManager().unregister(Chat.class, this);
   }

   private Chat getAlternateProvider() {
      if(alt == null) {
         Collection<RegisteredServiceProvider<Chat>> rsp = plugin.getServer().getServicesManager().getRegistrations(Chat.class);
         for(RegisteredServiceProvider<Chat> providers : rsp) {
            if(!this.getName().equals(providers.getProvider().getName())) {
               alt = providers.getProvider();
               break;
            }
         }
      }
      return alt;
   }
}
