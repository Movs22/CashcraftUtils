package com.cashcraft.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import java.util.ArrayList;

public class CreateCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public CreateCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("create").setExecutor(this);
		plugin.getCommand("create").setTabCompleter(new CreateTabComplete());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
				if (args[0].equals("lift")) {
					Player p = (Player) sender;
					Location loc = p.getLocation();
					sender.sendMessage(ChatColor.YELLOW + "Lift Command Block created at: " + loc.getBlockX() + "/"
							+ loc.getBlockY() + "/" + loc.getBlockZ());
					ItemStack command = new ItemStack(Material.COMMAND_BLOCK);
					ItemMeta meta = command.getItemMeta();
					meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Lift Command Block");
					command.setItemMeta(meta);
					NBTItem commandnbt = new NBTItem(command);
					NBTCompound commandnbt2 = commandnbt.getOrCreateCompound("BlockEntityTag");
					commandnbt2.setString("Command",
							"tp @p " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());
					p.getInventory().setItemInMainHand(commandnbt.getItem());
					return true;
				} else {
					return false;
				}

		} else {
			sender.sendMessage(ChatColor.DARK_RED + "You cant use this command in the server console.");
			return true;
		}
	}

	private class CreateTabComplete implements TabCompleter {

		@Override
		public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
			ArrayList<String> arguments= new ArrayList<String>();
			arguments.add("lift");
			return arguments;
		}
	}
}