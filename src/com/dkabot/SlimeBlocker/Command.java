package com.dkabot.SlimeBlocker;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
	private SlimeBlocker plugin;
	public Command(SlimeBlocker plugin) {
		this.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		String player = null;
		if (sender instanceof Player) {
			player = sender.getName();
		}
		if (cmd.getName().equalsIgnoreCase("slimeblocker")) {
			if (player == null) {
				sender.sendMessage("This command can only be run by a player.");
				return true;
			}
			else {
				if (sender.isOp() | sender.hasPermission("slimeblocker.use")) {
					if (args.length > 0) {
						sender.sendMessage(ChatColor.RED + "Too many arguments");
						sender.sendMessage(ChatColor.RED + "Use '/slimeblocker' to toggle the chunk you are in.");
						return true;
					}
					else {
						Player cmdsender = (Player) sender;
						String chunk = cmdsender.getLocation().getChunk().toString();
						Persistance cmdClass = plugin.getDatabase().find(Persistance.class).where().ieq("chunk", chunk).findUnique();
						if(cmdClass == null) {
							cmdClass = new Persistance();
							cmdClass.setChunk(chunk);
							plugin.getDatabase().save(cmdClass);
							sender.sendMessage(ChatColor.GREEN + "Slimes are now disabled in this chunk.");
							sender.sendMessage(ChatColor.GREEN + "(Assuming they spawn naturally)");
							return true;
						}
						else {
							plugin.getDatabase().delete(cmdClass);
							sender.sendMessage(ChatColor.GREEN + "Slimes are now reenabled in this chunk.");
							sender.sendMessage(ChatColor.GREEN + "(Assuming they spawn naturally)");
							return true;
						}
					}
				}
				else {
					sender.sendMessage(ChatColor.RED + "You lack permission to do this.");
					return true;
				}
			}
		}
		return false;
	}
}