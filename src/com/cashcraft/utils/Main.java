package com.cashcraft.utils;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main plugin;
    @Override
    public void onEnable() {
        plugin = this;
        System.out.println("Loaded plugin!");
        new SignCommand(this);
        new CreateCommand(this);
        new InvisItemCommand(this);
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin has been disabled.");
    }

}