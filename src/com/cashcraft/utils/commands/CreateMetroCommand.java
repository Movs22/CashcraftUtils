package com.cashcraft.utils.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.cashcraft.utils.Main;

public class CreateMetroCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;

	public CreateMetroCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("createmetro").setExecutor(this);
		plugin.getCommand("createmetro").setTabCompleter(new CreateMetroTabComplete());
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length < 3) {
			return false;
		}
		List<String> s = (List<String>) plugin.getConfig().getList("Stations");
		List<String> sc = (List<String>) plugin.getConfig().getList("StationCodes");
		if (s.contains(args[0]) && sc.contains(args[1])) {
			if (plugin.getConfig().getString(args[1] + "." + args[3]) != null) {
				if (args.length < 4) {
					args[3] = "Main";
				}
				plugin.getConfig().set(args[1] + "." + args[3], args[2].replaceAll("\\\\", " "));
				sender.sendMessage(ChatColor.GREEN + "Updated " + ChatColor.GOLD + args[0] + ":" + args[3]
						+ ChatColor.GREEN + " to use " + args[2] + ".");
				plugin.saveConfig();
				return true;
			} else {
				plugin.getConfig().set(args[0], args[1]);
				if (args.length < 4) {
					args[3] = "Main";
				}
				plugin.getConfig().set(args[1] + "." + args[3], args[2].replaceAll("\\\\", " "));
				sender.sendMessage(ChatColor.GREEN + "Created " + ChatColor.GOLD + args[0] + ":" + args[3]
						+ ChatColor.GREEN + " to use " + args[2] + ".");
				plugin.saveConfig();
				return true;
			}
		} else {
			s.add(args[0]);
			sc.add(args[1]);
			plugin.getConfig().set("Stations", s);
			plugin.getConfig().set("StationCodes", sc);
			plugin.getConfig().set(args[0], args[1]);
			if (args.length < 4) {
				args[3] = "Main";
			}
			plugin.getConfig().set(args[1] + "." + args[3], args[2].replaceAll("\\\\", " "));
			sender.sendMessage(ChatColor.GREEN + "Created " + ChatColor.GOLD + args[0] + ":" + args[3]
					+ ChatColor.GREEN + " to use " + args[2] + ".");
			plugin.saveConfig();
			return true;
		}
	}

	private class CreateMetroTabComplete implements TabCompleter {
		@SuppressWarnings("unchecked")
		public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
			List<String> arguments = new ArrayList<String>();
			if (args.length == 1) {
				List<String> s = (List<String>) plugin.getConfig().getList("Stations");
				List<String> sc = (List<String>) plugin.getConfig().getList("StationCodes");
				for (int i = 0; i < s.size(); i++) {
					arguments.add(s.get(i));
				}
				return arguments;
			} else {
				return arguments;
			}
		}
	}
}