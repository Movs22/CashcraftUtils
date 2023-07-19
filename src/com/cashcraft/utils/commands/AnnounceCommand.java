package com.cashcraft.utils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cashcraft.utils.Main;

import java.util.ArrayList;

public class AnnounceCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public AnnounceCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("announce").setExecutor(this);
		plugin.getCommand("announce").setTabCompleter(new AnnounceTabComplete());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp()) {
			List<String> messages = new ArrayList<String>();
			messages.add("§8-------------------------------------------------");
			messages.add("§8» §b§lSERVER ANNOUNCEMENT");
			messages.add("§8§oget reading %PLAYERNAME%, it's maybe important");
			messages.add("§r §r");
			messages.add("§8» §7" + String.join(" ", args).replaceAll("&", "§").replaceAll("§§", "&"));
			messages.add("§r §r");
			messages.add("§8-------------------------------------------------");
			plugin.getServer().getOnlinePlayers().forEach(p -> {
				p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
				p.sendMessage(String.join("\n", messages).replace("%PLAYERNAME%", p.getName()));
			});
			return true;
		} else {
			sender.sendMessage(ChatColor.RED + "You can't run this command.");
			return true;
		}
	}

	private class AnnounceTabComplete implements TabCompleter {

		@Override
		public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
			return null;
		}
	}
}