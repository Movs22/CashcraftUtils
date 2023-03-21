package com.cashcraft.utils;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class MaintenanceCommand implements CommandExecutor {
    @SuppressWarnings("unused")
    private Main plugin;

    public MaintenanceCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("maintenance").setExecutor(this);
        plugin.getCommand("maintenance").setTabCompleter(new MaintenanceTabComplete());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (args[0] == "off") {
                if(plugin.getMaintaince() == true) {
                    plugin.setMaintenance(false);
                    p.sendMessage(ChatColor.RED + "Maintenance disabled by " + sender + ".");
                } else {
                    sender.sendMessage(ChatColor.RED + "Maintenance is already disabled");
                }

            }
            return true;

        } else {
            return false;
        }

    }

    private class MaintenanceTabComplete implements TabCompleter {
        public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
            return null;

        }
    }
}
