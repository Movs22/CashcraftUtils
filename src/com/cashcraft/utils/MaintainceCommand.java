package com.cashcraft.utils;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class MaintainceCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;

	public MaintainceCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("maintaince").setExecutor(this);
		plugin.getCommand("maintaince").setTabCompleter(new MaintainceTabComplete());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		
		
		
		
		return false;
	}

	private class MaintainceTabComplete implements TabCompleter {
		public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
			return null;

		}
	}
}
