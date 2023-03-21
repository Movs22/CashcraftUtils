package com.cashcraft.utils.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.cashcraft.utils.Main;

public class PlayerJoin implements Listener {
	private Main plugin;
	
	public PlayerJoin(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if(plugin.getMaintaince()) {
			if(!e.getPlayer().isOp()) {
				e.getPlayer().kickPlayer(ChatColor.RED + "The server is currently under maintenance: " + ChatColor.YELLOW + plugin._maintenanceReason + ".");
				return;
			}
		}
	}
}

