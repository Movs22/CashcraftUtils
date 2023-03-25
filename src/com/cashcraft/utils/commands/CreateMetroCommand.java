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
		if(args.length < 3) {
			return false;
		}
		List<String> s = (List<String>) plugin.getConfig().getList("Stations");
		List<String> sc = (List<String>) plugin.getConfig().getList("StationCodes");
		
		return false;
	}
	private class CreateMetroTabComplete implements TabCompleter {
		@SuppressWarnings("unchecked")
		public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
			return null;
		}
	}
}