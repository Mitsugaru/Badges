package com.mitsugaru.badges.config;

import com.mitsugaru.badges.services.ConfigNode;

/**
 * Configuration nodes for the main config.yml.
 */
public enum RootConfigNode implements ConfigNode {
   /**
    * Debug options
    */
   DEBUG_VAULT("debug.vault", VarType.BOOLEAN, false);

   /**
    * Config option path.
    */
   private String path;
   /**
    * Variable type.
    */
   private VarType type;
   /**
    * Default value.
    */
   private Object defaultValue;

   /**
    * Constructor.
    * 
    * @param path
    *           - Config path.
    * @param type
    *           - Variable type.
    * @param def
    *           - Default value.
    */
   private RootConfigNode(String path, VarType type, Object def) {
      this.path = path;
      this.type = type;
      this.defaultValue = def;
   }

   @Override
   public String getPath() {
      return path;
   }

   @Override
   public VarType getVarType() {
      return type;
   }

   @Override
   public Object getDefaultValue() {
      return defaultValue;
   }

}
