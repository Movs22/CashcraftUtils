package com.cashcraft.utils;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;

public class MetroCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public MetroCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("metro").setExecutor(this);
		plugin.getCommand("metro").setTabCompleter(new InvisItemTabComplete());
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args[0] == "list") {
			List<String> s = (List<String>) plugin.getConfig().getList("Stations");
			if(s == null || s.size() == 0) { 
				sender.sendMessage(ChatColor.GOLD + "Couldn't find any metro stations	.");
				return true;
			}
			return true;
		}
		return false;
	}
	private class InvisItemTabComplete implements TabCompleter {
		public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
			if (args.length == 1) {
				ArrayList<String> arguments = new ArrayList<String>();
				arguments.add("glow");
				return arguments;
			} else {
				return null;
			}
		}
	}
}