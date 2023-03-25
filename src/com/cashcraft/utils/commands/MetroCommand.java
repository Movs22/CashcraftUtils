package com.cashcraft.utils.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.bergerkiller.bukkit.tc.pathfinding.PathNode;
import com.cashcraft.utils.Main;

public class MetroCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public MetroCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("metro").setExecutor(this);
		plugin.getCommand("metro").setTabCompleter(new MetroTabComplete());
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length < 1) {
			return false;
		}
		if(args[0].equals("list")) {
			List<String> s = (List<String>) plugin.getConfig().getList("Stations");
			if(s == null || s.size() == 0) { 
				sender.sendMessage(ChatColor.GOLD + "Couldn't find any metro stations.");
				return true;
			} else {
				String a = ChatColor.GOLD + "There are " + s.size() + " metro stations: ";
				for(int i = 0; i < s.size(); i++) {
					a = a + ChatColor.GREEN + s.get(i) + ChatColor.GOLD + ", ";
				}
				sender.sendMessage(a);
			}
			return true;
		} else {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You can't run this command as a " + (sender instanceof CommandBlock ? "Command Block" : "Server Console"));
				return true;
			}
			Player p = (Player) sender;
			List<String> s = (List<String>) plugin.getConfig().getList("Stations");
			List<String> sc = (List<String>) plugin.getConfig().getList("StationCodes");
			if(s.contains(args[0])) {
				args[0] = plugin.getConfig().getString(args[0]);
			}
			if(sc.contains(args[0])) {
				String t = "a:INVALID";
				if(args.length < 2) {
					t = plugin.getConfig().getString(args[0] + ".Main");
				} else {
					t = plugin.getConfig().getString(args[0] + "." + String.join(" ", Arrays.asList(args).subList(1, args.length)));
				}
				if(t == null || t.length() < 1) {
					sender.sendMessage(ChatColor.RED + "Location " + ChatColor.GOLD + args[0] + ":" +  String.join(" ", Arrays.asList(args).subList(1, args.length)) + ChatColor.RED  + " wasn't found.");
					return true;
				}
				if(t.startsWith("t:")) {
					PathNode a = plugin.traincarts.getPathProvider().getWorld("Main1").getNodeByName(t.split("t:")[1]);
					if(a == null) {
						sender.sendMessage(ChatColor.RED + "Location " + args[0] + ":" +  String.join(" ", Arrays.asList(args).subList(1, args.length)) + " wasn't found.");
						return true;
					}
					Bukkit.dispatchCommand(sender, "tp " + sender.getName() + " " +  a.location.x + " " + a.location.y + " " + a.location.z);
					sender.sendMessage(ChatColor.GREEN + "Teleporting to " + ChatColor.GOLD +  args[0] + ":" + String.join(" ", Arrays.asList(args).subList(1, args.length)) + ChatColor.GREEN + "...");
					return true;
				}
				if(t.startsWith("w:")) {
					Bukkit.dispatchCommand(sender, "warp " + t.split("w:")[1]);
					sender.sendMessage(ChatColor.GREEN + "Teleporting to " + ChatColor.GOLD +  args[0] + ":" + String.join(" ", Arrays.asList(args).subList(1, args.length)) + ChatColor.GREEN + "...");
					return true;
				}
				if(t.startsWith("h:")) {
					Bukkit.dispatchCommand(sender, "home " + t.split("h:")[1]);
					sender.sendMessage(ChatColor.GREEN + "Teleporting to " + ChatColor.GOLD +  args[0] + ":" + String.join(" ", Arrays.asList(args).subList(1, args.length)) + ChatColor.GREEN + "...");
					return true;
				}
				
				sender.sendMessage(ChatColor.RED + "Location " + args[0] + ":" +  String.join(" ", Arrays.asList(args).subList(1, args.length)) + " wasn't found.");
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Station " + args[0] + " wasn't found.");
				return true;
			}
		}
	}
	private class MetroTabComplete implements TabCompleter {
		@SuppressWarnings("unchecked")
		public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
			List<String> arguments = new ArrayList<String>();
			if (args.length == 1) {
				List<String> s = (List<String>) plugin.getConfig().getList("Stations");
				List<String> sc = (List<String>) plugin.getConfig().getList("StationCodes");
				for(int i = 0; i < sc.size(); i++) {
					if(sc.get(i).toLowerCase().startsWith(args[0].toLowerCase()) || args[0] == "") {
						arguments.add(sc.get(i));
					}
				}
				for(int i = 0; i < s.size(); i++) {
					if(plugin.getConfig().getString(plugin.getConfig().getString(s.get(i))) != null) {
						if(s.get(i).toLowerCase().startsWith(args[0].toLowerCase()) || args[0] == "") {
							arguments.add(s.get(i));
						}
					}
				}
				arguments.add("list");
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