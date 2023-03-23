package com.cashcraft.utils.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.bergerkiller.bukkit.tc.pathfinding.PathNode;
import com.cashcraft.utils.Main;

public class MetroCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public MetroCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("metro").setExecutor(this);
		plugin.getCommand("metro").setTabCompleter(new InvisItemTabComplete());
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args[0].equals("list")) {
			List<String> s = (List<String>) plugin.getConfig().getList("Stations");
			if(s == null || s.size() == 0) { 
				sender.sendMessage(ChatColor.GOLD + "Couldn't find any metro stations.");
				return true;
			} else {
				String a = ChatColor.GOLD + "There are " + s.size() + " metro stations: ";
				for(int i = 0; i < s.size() - 1; i++) {
					a = ChatColor.GREEN + s.get(i) + ChatColor.GOLD + ", ";
				}
			}
			return true;
		} else {
			List<String> s = (List<String>) plugin.getConfig().getList("Stations");
			List<String> sc = (List<String>) plugin.getConfig().getList("StationCodes");
			if(sc.contains(args[0])) {
				String t;
				if(args.length < 2) {
					t = plugin.getConfig().getString(args[0] + ".Main");
				} else {
					t = plugin.getConfig().getString(args[0] + "." + String.join(" ", Arrays.asList(args).subList(1, args.length)));
				}
				plugin.getLogger().log(Level.INFO, args[0] + "." + String.join(" ", Arrays.asList(args).subList(1, args.length)));
				plugin.getLogger().log(Level.INFO, "[Cashcraft Utils] (Debug) Found station: " + t.toString() + " - " + (t.contains("t:") ? "[TC]" : "[Other node]")+ " for arg " + args[0]);
				PathNode a = plugin.traincarts.getPathProvider().getWorld("Main1").getNodeByName(t.split("t:")[1]);
				plugin.getLogger().log(Level.INFO, "[Cashcraf Utils] (Debug) Station " + t + " - Found node " + a.getName() + " - " + a.location + " for " + t);
				if(t == null) {
					sender.sendMessage(ChatColor.RED + "Station " + args[0] + " wasn't found.");
					return true;
				}
				if(t.contains("h:")) {
					
				} else if(t.contains("w:")) {
					
				} else if(t.contains("t:")) {
					a = plugin.traincarts.getPathProvider().getWorld("Main1").getNodeByName(t.split("t:")[1]);
					plugin.getLogger().log(Level.INFO, "[Cashcraf Utils] (Debug) Station " + t + " - Found node " + a.getName() + " - " + a.location + " for " + t);
					return true;
				}
				
			} else {
				sender.sendMessage(ChatColor.RED + "Station " + args[0] + " wasn't found.");
				return true;
			}
		}
		return false;
	}
	private class InvisItemTabComplete implements TabCompleter {
		@SuppressWarnings("unchecked")
		public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
			if (args.length == 1) {
				List<String> s = (List<String>) plugin.getConfig().getList("Stations");
				List<String> sc = (List<String>) plugin.getConfig().getList("StationCodes");
				List<String> arguments = new ArrayList<String>();
				for(int i = 0; i < sc.size(); i++) {
					arguments.add(sc.get(i));
				}
				for(int i = 0; i < s.size(); i++) {
					arguments.add(s.get(i));
				}
				return arguments;
			} else {
				return null;
			}
		}
	}
}