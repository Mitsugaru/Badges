package com.mitsugaru.badges.services;

public enum PermissionNode {
   ADMIN("admin"), IGNORE("ignore");

   private static final String prefix = "Badges.";

   private String node;

   private PermissionNode(String child) {
      this.node = prefix + child;
   }

   public String getNode() {
      return node;
   }
}
