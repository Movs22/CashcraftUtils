package com.cashcraft.utils.commands;

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

import com.cashcraft.utils.Main;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;

public class InvisItemCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public InvisItemCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("invisitem").setExecutor(this);
		plugin.getCommand("invisitem").setTabCompleter(new InvisItemTabComplete());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1 && args[0].equals("glow")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					ItemStack gitem = new ItemStack(Material.GLOW_ITEM_FRAME);
					ItemMeta meta = gitem.getItemMeta();
					meta.setDisplayName(ChatColor.YELLOW + "Glow Invisible Item Frame");
					gitem.setItemMeta(meta);
					NBTItem gitemnbt = new NBTItem(gitem);
					NBTCompound gitemnbt2 = gitemnbt.getOrCreateCompound("EntityTag");
					gitemnbt2.setByte("Invisible", (byte) 1);
					p.getInventory().setItemInMainHand(gitemnbt.getItem());
					return true;
				} else {
					sender.sendMessage(ChatColor.DARK_RED + "You can`t use this command in the server console.");
					return true;
				}
		} else {
			if (sender instanceof Player) {
					Player p = (Player) sender;
					ItemStack item = new ItemStack(Material.ITEM_FRAME);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.YELLOW + "Invisible Item Frame");
					item.setItemMeta(meta);
					NBTItem itemnbt = new NBTItem(item);
					NBTCompound itemnbt2 = itemnbt.getOrCreateCompound("EntityTag");
					itemnbt2.setByte("Invisible", (byte) 1);
					p.getInventory().setItemInMainHand(itemnbt.getItem());
					return true;
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "You can`t use this command in the server console");
				return true;
			}
		}
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