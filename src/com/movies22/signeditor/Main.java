package com.movies22.signeditor;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Main extends JavaPlugin implements PluginMessageListener {
    public static Main plugin;
    @Override
    public void onEnable() {
        plugin = this;
        System.out.println("Loaded plugin!");
        new SignCommand(this);
        
    }

        @Override
    public void onDisable() {
        System.out.println("Plugin has been disabled.");
    }

		@Override
		public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {
			// TODO Auto-generated method stub
			
		}
}