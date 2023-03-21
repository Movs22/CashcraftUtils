package com.cashcraft.utils;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import com.bergerkiller.bukkit.tc.TrainCarts;

public class Main extends JavaPlugin {
	public int _version = 1627;
	//TODO: add more status below
	String[] status = {"Clicking cookies, since 2022", "Traincarts-1.19.3-1624-Cashcraft-NO-CI.jar","Stop destroying maintenance doors"}; 
    public static Main plugin;
    public Boolean _maintenance;
    public TrainCarts traincarts;
    
    public int getCCUVersion() {
    	return this._version;
    }
    
    public void setMaintenance(Boolean m) {
    	this._maintenance = m;
    }
    public Boolean getMaintaince() {
    	return this._maintenance;
    }
    
    @Override
    public void onEnable() {
        plugin = this;
        if(plugin.getServer().getPluginManager().isPluginEnabled("Train_Carts")) {
        	this.traincarts = (TrainCarts) plugin.getServer().getPluginManager().getPlugin("Train_Carts");
        	if(this.traincarts.getTCVersion() != this.getCCUVersion()) {
        		this.traincarts = null;
        		plugin.getLogger().log(Level.WARNING, "Incompatible version of TrainCarts found. Expected build " + this.getCCUVersion() + ", found build " + this.traincarts.getTCVersion() + ".");
        	} else {
        		plugin.getLogger().log(Level.INFO, "Loaded TrainCarts (Build " +  this.traincarts.getTCVersion() + "). Loaded " + traincarts.getPathProvider().getWorld("Main1").getNodes().size() + " nodes in minecraft:Main1.");
        	}
        } else {
        	plugin.getLogger().log(Level.WARNING, "TrainCarts not found. TC Nodes will not be supported.");
        }
        plugin.getLogger().log(Level.INFO, "Loaded Cashcraft Utils (Build " + plugin.getCCUVersion() + ").");
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