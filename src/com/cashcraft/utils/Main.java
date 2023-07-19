package com.cashcraft.utils;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.plugin.PluginBase;
import org.bukkit.plugin.java.JavaPlugin;

import com.cashcraft.utils.commands.AnnounceCommand;
//import com.bergerkiller.bukkit.tc.TrainCarts;
import com.cashcraft.utils.commands.CreateCommand;
import com.cashcraft.utils.commands.CreateMetroCommand;
import com.cashcraft.utils.commands.DeleteMetroCommand;
import com.cashcraft.utils.commands.InvisItemCommand;
import com.cashcraft.utils.commands.MaintenanceCommand;
import com.cashcraft.utils.commands.MetroCommand;
import com.cashcraft.utils.commands.SignCommand;
import com.cashcraft.utils.events.PlayerJoin;

public class Main extends JavaPlugin {
	public int _version = 1637;
	//TODO: add more status below
	String[] status = {"Clicking cookies, since 2022", "Traincarts-1.19.3-1603-Cashcraft-NO-CI.jar","Stop destroying maintenance doors","Cyan line service to Broad Street", "Blue Line Shuttle"}; 
    public static Main plugin;
    public Boolean _maintenance = false;
    public String _maintenanceReason = "";
    //TrainCarts traincarts;
    public int getCCUVersion() {
    	return this._version;
    }
    
    public void setMaintenance(Boolean m, String r) {
    	this._maintenance = m;
    	this._maintenanceReason = r;
    }
    public Boolean getMaintaince() {
    	return this._maintenance;
    }
    
    @Override
    public void onEnable() {
        plugin = this;
        //TODO: fix this code.
        
        plugin.getLogger().log(Level.INFO, "[Cashcraft Utils] Loaded Cashcraft Utils (Build " + plugin.getCCUVersion() + ").");
        plugin.getLogger().log(Level.INFO, "[Cashcraft Utils] " + status[(int) Math.round(Math.random()*(status.length - 1))]);
        if(getConfig().getString("version") == null) {
        	saveDefaultConfig();
        	plugin.getLogger().log(Level.INFO, "[Cashcraft Utils] Created config.yml");
        }
        if(!getConfig().getString("version").equals("1.0")) {
        	plugin.getLogger().log(Level.SEVERE, "Config.yml was created on an older version. Plugin has been disabled to prevent any damage.");
        	getServer().getPluginManager().disablePlugin(this);
        }
        if(!(getConfig().getList("Stations") instanceof List) || getConfig().getList("Stations") == null) {
        	plugin.getLogger().log(Level.WARNING, "[Cashcraft Utils] Config.yml is corrupted. Failed to load stations.");
        } else {
        	plugin.getLogger().log(Level.INFO, "[Cashcraft Utils] Loaded " + getConfig().getList("Stations").size() + " stations.");
        }
        
        if(plugin.getServer().getPluginManager().getPlugin("Train_Carts") != null) {
        	/*this.traincarts = (TrainCarts) plugin.getServer().getPluginManager().getPlugin("Train_Carts");
        	if(this.traincarts.getTCVersion() != this.getCCUVersion()) {
        		this.traincarts = null;
        		plugin.getLogger().log(Level.WARNING, "[Cashcraft Utils] Incompatible version of TrainCarts found. Expected build " + this.getCCUVersion() + ", found build " + this.traincarts.getTCVersion() + ".");
        	} else {
        		this.traincarts.getPathProvider().getWorld("Main1").rerouteAll();
        		plugin.getLogger().log(Level.INFO, "[Cashcraft Utils] Loaded TrainCarts (Build " +  this.traincarts.getTCVersion() + "). Loaded " + traincarts.getPathProvider().getWorld("Main1").getNodes().size() + " nodes in minecraft:Main1.");
        	}*/
        	plugin.getLogger().log(Level.WARNING, "[Cashcraft Utils] TrainCarts not found. TC Nodes will not be supported.");
        } else {
        	plugin.getLogger().log(Level.WARNING, "[Cashcraft Utils] TrainCarts not found. TC Nodes will not be supported.");
        }
        
        plugin.getServer().getPluginManager().registerEvents(new PlayerJoin(this), plugin);
        new SignCommand(this);
        new CreateCommand(this);
        new InvisItemCommand(this);
        new MaintenanceCommand(this);
        new AnnounceCommand(this);
    }

    @Override
    public void onDisable() {
    	saveConfig();
        plugin.getLogger().log(Level.INFO, "Disabling Cashcraft Utils...");
    }
}