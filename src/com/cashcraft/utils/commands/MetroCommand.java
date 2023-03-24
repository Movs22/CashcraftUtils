package com.cashcraft.utils.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.bergerkiller.bukkit.tc.pathfinding.PathNode;
import com.bergerkiller.generated.net.minecraft.network.protocol.game.PacketPlayInTeleportAcceptHandle.PacketPlayInTeleportAcceptClass;
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
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You can't run this command as a " + (sender instanceof CommandBlock ? "Command Block" : "Server Console"));
				return true;
			}
			Player p = (Player) sender;
			List<String> s = (List<String>) plugin.getConfig().getList("Stations");
			List<String> sc = (List<String>) plugin.getConfig().getList("StationCodes");
			if(sc.contains(args[0])) {
				String t = "a:INVALID";
				if(args.length < 2) {
					t = plugin.getConfig().getString(args[0] + ".Main");
				} else {
					t = plugin.getConfig().getString(args[0] + "." + String.join(" ", Arrays.asList(args).subList(1, args.length)));
				}
				if(t.startsWith("t:")) {
					PathNode a = plugin.traincarts.getPathProvider().getWorld("Main1").getNodeByName(t.split("t:")[1]);
					Bukkit.dispatchCommand(sender, "tp " + sender.getName() + " " +  a.location.x + " " + a.location.y + " " + a.location.z);
					sender.sendMessage(ChatColor.GREEN + "You've been teleported to " + ChatColor.GOLD +  args[0] + ":" + String.join(" ", Arrays.asList(args).subList(1, args.length)) + ChatColor.GREEN + ".");
					return true;
				}
				if(t.startsWith("w:")) {
					Bukkit.dispatchCommand(sender, "warp " + t.split("w:")[1]);
					sender.sendMessage(ChatColor.GREEN + "You've been teleported to " + ChatColor.GOLD +  args[0] + ":" + String.join(" ", Arrays.asList(args).subList(1, args.length)) + ChatColor.GREEN + ".");
					return true;
				}
				if(t.startsWith("h:")) {
					Bukkit.dispatchCommand(sender, "home " + t.split("h:")[1]);
					sender.sendMessage(ChatColor.GREEN + "You've been teleported to " + ChatColor.GOLD +  args[0] + ":" + String.join(" ", Arrays.asList(args).subList(1, args.length)) + ChatColor.GREEN + ".");
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
	private class InvisItemTabComplete implements TabCompleter {
		@SuppressWarnings("unchecked")
		public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
			List<String> arguments = new ArrayList<String>();
			if (args.length == 1) {
				List<String> s = (List<String>) plugin.getConfig().getList("Stations");
				List<String> sc = (List<String>) plugin.getConfig().getList("StationCodes");
				for(int i = 0; i < sc.size(); i++) {
					arguments.add(sc.get(i));
				}
				for(int i = 0; i < s.size(); i++) {
					arguments.add(s.get(i));
				}
				arguments.add("list");
				return arguments;
			} else if(args.length == 2) {
				arguments.add("go away");
				return arguments;
			} else {
				return null;
			}
		}
	}
}