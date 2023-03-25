package com.cashcraft.utils.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.cashcraft.utils.Main;

import net.md_5.bungee.api.ChatColor;

public class DeleteMetroCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public DeleteMetroCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("deletemetro").setExecutor(this);
		plugin.getCommand("deletemetro").setTabCompleter(new DeleteMetroTabComplete());
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length < 1) {
			return false;
		}
		List<String> s = (List<String>) plugin.getConfig().getList("Stations");
		List<String> sc = (List<String>) plugin.getConfig().getList("StationCodes");
		if(s.contains(args[0])) {
			args[0] = plugin.getConfig().getString(args[0]);
		}
		if(sc.contains(args[0])) {
			if(args.length == 2) {
				plugin.getConfig().set(args[0] + "." + args[1], null);
				sender.sendMessage(ChatColor.GREEN + "Deleted station " + ChatColor.GOLD + args[0] +":" + args[1] + ChatColor.GREEN + ".");
				return true;
			} else {
				sc.remove(sc.indexOf(args[0]));
				plugin.getConfig().set(args[0], null);
				plugin.getConfig().set("Stations", s);
				plugin.getConfig().set("StationCodes", sc);
				sender.sendMessage(ChatColor.GREEN + "Deleted station " + ChatColor.GOLD + args[0] + ChatColor.GREEN + ".");
				return true;
			}
		}
		sender.sendMessage(ChatColor.RED + "Station " + args[0] + " wasn't found.");
		return true;
	}
	private class DeleteMetroTabComplete implements TabCompleter {
		@SuppressWarnings("unchecked")
		public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
			List<String> arguments = new ArrayList<String>();
			if (args.length == 1) {
				List<String> s = (List<String>) plugin.getConfig().getList("Stations");
				List<String> sc = (List<String>) plugin.getConfig().getList("StationCodes");
				for(int i = 0; i < sc.size(); i++) {
					if(sc.get(i).startsWith(args[0]) || args[0] == "") {
						arguments.add(sc.get(i));
					}
				}
				for(int i = 0; i < s.size(); i++) {
					if(plugin.getConfig().getString(plugin.getConfig().getString(s.get(i))) != null) {
						if(s.get(i).startsWith(args[0]) || args[0] == "") {
							arguments.add(s.get(i));
						}
					}
				}
				return arguments;
			} else if(args.length == 2) {
				List<String> s = (List<String>) plugin.getConfig().getList("Stations");
				List<String> sc = (List<String>) plugin.getConfig().getList("StationCodes");
				if(s.contains(args[0])) {
					args[0] = plugin.getConfig().getString(args[0]);
				}
				if(sc.contains(args[0])) {
					 plugin.getConfig().getConfigurationSection(args[0]).getKeys(false).forEach(k -> {
						 arguments.add(k);
					 });
					 return arguments;
				} else {
					return arguments;
				}
			} else {
				return arguments;
			}
		}
	}
}