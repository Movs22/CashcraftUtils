package com.cashcraft.utils;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	//TODO: add more status below
	String[] status = {"Clicking cookies, since 2022", "Traincarts-1.19.3-1624-Cashcraft-NO-CI.jar","Stop destroying maintenance doors"}; 
    public static Main plugin;
    public Boolean _maintenance;
    
    public void setMaintenance(Boolean m) {
    	this._maintenance = m;
    }
    public Boolean getMaintaince() {
    	return this._maintenance;
    }
    @Override
    public void onEnable() {
        plugin = this;
        plugin.getLogger().log(Level.INFO, "Loaded Cashcraft Utils (Build 1624)");
        plugin.getLogger().log(Level.INFO, status[(int) Math.round(Math.random()*status.length)]);
        if(getConfig().options() == null) {
        	plugin.getLogger().log(Level.INFO, "Created config.yml");
        	saveDefaultConfig();
        }
        if(getConfig().getString("version") != "1.0") {
        	plugin.getLogger().log(Level.SEVERE, "Config.yml was created on an older version. Plugin has been disabled to prevent any damage.");
        	getServer().getPluginManager().disablePlugin(this);
        }
        if((getConfig().getList("Stations") instanceof List) || getConfig().getList("Stations") == null) {
        	plugin.getLogger().log(Level.WARNING, "Config.yml is corrupted. Failed to load stations.");
        } else {
        	plugin.getLogger().log(Level.INFO, "Loaded " + getConfig().getList("Stations").size() + " stations.");
        }
        new SignCommand(this);
        new CreateCommand(this);
        new InvisItemCommand(this);
        new MetroCommand(this);
    }

    @Override
    public void onDisable() {
        plugin.getLogger().log(Level.INFO, "Disabling Cashcraft Utils...");
    }
}