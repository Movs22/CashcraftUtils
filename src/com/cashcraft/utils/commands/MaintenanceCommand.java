package com.cashcraft.utils.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.cashcraft.utils.Main;

public class MaintenanceCommand implements CommandExecutor {
	private Main plugin;

	public MaintenanceCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("maintenance").setExecutor(this);
		plugin.getCommand("maintenance").setTabCompleter(new MaintenanceTabComplete());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1) {
			if (args[0].equals("off")) {
				if (plugin.getMaintaince() == true) {
					plugin.setMaintenance(false, "None");
					sender.getServer().getOnlinePlayers().forEach(p -> {
						p.sendMessage(ChatColor.RED + "Maintenance disabled by " + sender.getName() + ".");
					});
				} else {
					sender.sendMessage(ChatColor.RED + "Maintenance is already disabled.");
					return true;
				}

			} else if (args[0].equals("on")) {
				if (plugin.getMaintaince() == false) {
					plugin.setMaintenance(true, "None");
					sender.getServer().getOnlinePlayers().forEach(p -> {
						p.sendMessage(ChatColor.GREEN + "Maintenance enabled by " + sender.getName() + ".");
					});
				} else {
					sender.sendMessage(ChatColor.DARK_RED + "Maintenance is already enabled.");
					return true;

				}
				return true;

			}
		}
		return true;
	}

	private class MaintenanceTabComplete implements TabCompleter {
		public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
			List<String> arguments = new ArrayList<String>();
			if(args.length == 1) {
				arguments.add("on");
				arguments.add("off");
			}
			return arguments;

		}
	}
}
