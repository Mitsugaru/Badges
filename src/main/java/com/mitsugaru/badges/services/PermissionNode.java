package com.mitsugaru.badges.services;

public enum PermissionNode {
   USE("use");

   private static final String prefix = "Sarcasm.";

   private String node;

   private PermissionNode(String child) {
      this.node = prefix + child;
   }

   public String getNode() {
      return node;
   }
}
