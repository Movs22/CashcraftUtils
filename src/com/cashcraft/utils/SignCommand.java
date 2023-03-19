package com.cashcraft.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.bergerkiller.bukkit.tc.utils.StationParser;

public class SignCommand implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	public SignCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("sign").setExecutor(this);
		plugin.getCommand("sign").setTabCompleter(new SignTabComplete());
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You can't run this command as a Console/Command Block.");
			return true;
		}
		if(args.length < 2) {
			sender.sendMessage(ChatColor.RED + "Missing arguments.");
			return false;
		}
		Player p = (Player) sender;
		Block b = p.getTargetBlock(null, 5);
		if(!(b.getState() instanceof Sign)) {
			sender.sendMessage(ChatColor.RED + "Block is not a valid sign.");
			return false;
		}
		Sign s = (Sign) b.getState();
		if(args[0].equals("glow")) {
			if(args[1].equals("glow")) {
				s.setGlowingText(true);
				sender.sendMessage(ChatColor.GREEN + "The text on the sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + " will now be glowing.");
				s.update();
				return true;
			} else if(args[1].equals("normal")){
				s.setGlowingText(false);
				sender.sendMessage(ChatColor.GREEN + "The text on the sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + " will no longer be glowing.");
				s.update();
				return true;
			}
		}
		if(args[0].equals("clear")) {
			if(args[1].equals("sign")) {
				s.setLine(0, "");
				s.setLine(1, "");
				s.setLine(2, "");
				s.setLine(3, "");
				sender.sendMessage(ChatColor.GREEN + "Cleared the text on the sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
				s.update();
				return true;
			} else if(args[1].equals("1")) {
				s.setLine(0, "");
				sender.sendMessage(ChatColor.GREEN + "Cleared the text on " + ChatColor.YELLOW + "line 1" + ChatColor.GREEN + " on the sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
				s.update();
				return true;
			} else if(args[1].equals("2")) {
				s.setLine(1, "");
				sender.sendMessage(ChatColor.GREEN + "Cleared the text on " + ChatColor.YELLOW + "line 2" + ChatColor.GREEN + " on the sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
				s.update();
				return true;
			} else if(args[1].equals("3")) {
				s.setLine(2, "");
				sender.sendMessage(ChatColor.GREEN + "Cleared the text on " + ChatColor.YELLOW + "line 3" + ChatColor.GREEN + " on the sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
				s.update();
				return true;
			} else if(args[1].equals("4")) {
				s.setLine(3, "");
				sender.sendMessage(ChatColor.GREEN + "Cleared the text on " + ChatColor.YELLOW + "line 4" + ChatColor.GREEN + " on the sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
				s.update();
				return true;
			}
		}
		if(args[0].equals("colour")) {
			if(StationParser.convertColor(args[1]).equals(args[1])) {
				if(args[1].equals("sign")) {
					for(int i = 0; i < 4; i++) {
						s.setLine(i, "§" + StationParser.convertColor("$" + args[2]) + ChatColor.translateAlternateColorCodes('&', s.getLine(i)).replaceAll("&#(?:[A-Fa-f0-9]{1,6})", "").replaceAll("&(?:[A-Fa-f0-9]{1,1})", "") );
					}
					sender.sendMessage(ChatColor.GREEN + "Change the colour of the sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + " to " +ChatColor.GREEN + ".");
					s.update();
					return true;
				} else if(args[1].equals("1")) {
					s.setLine(0, "§" + StationParser.convertColor("$" + args[2]) + ChatColor.translateAlternateColorCodes('&', s.getLine(0)).replaceAll("&#(?:[A-Fa-f0-9]{1,6})", "").replaceAll("&(?:[A-Fa-f0-9]{1,1})", "") );
					s.update();
					sender.sendMessage(ChatColor.GREEN + "Change the colour of line 1 of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + " to " +ChatColor.GREEN + ".");
					return true;
				} else if(args[1].equals("2")) {
					s.setLine(1, "§" + StationParser.convertColor("$" + args[2]) + ChatColor.translateAlternateColorCodes('&', s.getLine(1)).replaceAll("&#(?:[A-Fa-f0-9]{1,6})", "").replaceAll("&(?:[A-Fa-f0-9]{1,1})", "") );
					sender.sendMessage(ChatColor.GREEN + "Change the colour of line 2 of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + " to " +ChatColor.GREEN + ".");
					s.update();
					return true;
				} else if(args[1].equals("3")) {
					s.setLine(2, "§" + StationParser.convertColor("$" + args[2]) + ChatColor.translateAlternateColorCodes('&', s.getLine(2)).replaceAll("&#(?:[A-Fa-f0-9]{1,6})", "").replaceAll("&(?:[A-Fa-f0-9]{1,1})", "") );
					sender.sendMessage(ChatColor.GREEN + "Change the colour of line 3 of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + " to " +ChatColor.GREEN + ".");
					s.update();
					return true;
				} else if(args[1].equals("4")) {
					s.setLine(3, "§" + StationParser.convertColor("$" + args[2]) + ChatColor.translateAlternateColorCodes('&', s.getLine(3)).replaceAll("&#(?:[A-Fa-f0-9]{1,6})", "").replaceAll("&(?:[A-Fa-f0-9]{1,1})", "") );
					sender.sendMessage(ChatColor.GREEN + "Change the colour of line 4 of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + " to " + ChatColor.GREEN + ".");
					s.update();
					return true;
				}
			}
		}
		if(args[0].equals("set")) {
			if(StationParser.convertColor(args[1]).equals(args[1])) {
				String[] st = Arrays.copyOfRange(args, 2, args.length);
				String ctx = "";
				for(int i = 0; i < st.length; i++) {
					ctx = ctx + st[i] + " ";
				}
				ctx = ctx.replaceAll("&", "§").replaceAll("§§", "&");
				if(args[1].equals("sign")) {
					for(int i = 0; i < 4; i++) {
						s.setLine(i, ctx);
					}
					sender.sendMessage(ChatColor.GREEN + "Updated all lines of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
					s.update();
					return true;
				} else if(args[1].equals("1")) {
					s.setLine(0, ctx);
					sender.sendMessage(ChatColor.GREEN + "Updated line 1 of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
					s.update();
					return true;
				} else if(args[1].equals("2")) {
					s.setLine(1, ctx);
					sender.sendMessage(ChatColor.GREEN + "Updated line 2 of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
					s.update();
					return true;
				} else if(args[1].equals("3")) {
					s.setLine(2, ctx);
					sender.sendMessage(ChatColor.GREEN + "Updated line 3 of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
					s.update();
					return true;
				} else if(args[1].equals("4")) {
					s.setLine(3, ctx);
					sender.sendMessage(ChatColor.GREEN + "Updated line 4 of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
					s.update();
					return true;
				}
			}
		}
		
		if(args[0].equals("clear")) {
			if(args[1].equals("sign")) {
				s.setLine(0, "");
				s.setLine(1, "");
				s.setLine(2, "");
				s.setLine(3, "");
				sender.sendMessage(ChatColor.GREEN + "Cleared sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
				s.update();
				return true;
			} else if(args[1].equals("1")) {
				s.setLine(0, "");
				sender.sendMessage(ChatColor.GREEN + "Cleared line 1 of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
				s.update();
				return true;
			} else if(args[1].equals("2")) {
				s.setLine(1, "");
				sender.sendMessage(ChatColor.GREEN + "Cleared line 2 of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
				s.update();
				return true;
			} else if(args[1].equals("3")) {
				s.setLine(2, "");
				sender.sendMessage(ChatColor.GREEN + "Cleared line 3 of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
				s.update();
				return true;
			} else if(args[1].equals("4")) {
				s.setLine(3, "");
				sender.sendMessage(ChatColor.GREEN + "Cleared line 4 of sign at " + b.getX() + "/" + b.getY() + "/" + b.getZ() + ".");
				s.update();
				return true;
			}
		}
		return false;
	}
	
	private class SignTabComplete implements TabCompleter {
			public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
				if(!(sender instanceof Player)) return null;
				Player p = (Player) sender;
				Block b = p.getTargetBlock(null, 5);
				if(!(b.getState() instanceof Sign)) {
					sender.sendMessage(ChatColor.RED + "Block is not a valid sign.");
					return null;
				}
				Sign s = (Sign) b.getState();
				ArrayList<String> arguments = new ArrayList<String>();
				if (args.length == 1) {
					arguments.add("colour");
					arguments.add("glow");
					arguments.add("set");
					arguments.add("clear");
				} else {
					if(args.length == 2) {
						if(args[0].equals("glow")) {
							arguments.add("glow");
							arguments.add("normal");
						} else if(args[0].equals("colour") || args[0].equals("set") || args[0].equals("clear")) {
							arguments.add("1");
							arguments.add("2");
							arguments.add("3");
							arguments.add("4");
							arguments.add("sign");
						}
					} else if(args.length == 3 && args[0].equals("colour")) {
						for(int i = 0; i < StationParser.listColor().length; i++) {
							arguments.add(StationParser.listColor()[i]);
						};
					} else if(args.length == 3 && args[0].equals("set")) {
						if(args[1] == "1") {
							arguments.add(s.getLine(0));
						} else if(args[1] == "2") {
							arguments.add(s.getLine(1));
						} else if(args[1] == "3") {
							arguments.add(s.getLine(2));
						} else if(args[1] == "4") {
							arguments.add(s.getLine(3));
						}
					} else {
						return null;
					}
				}
				return arguments;
			}
	}
}
