package dev.rudrecciah.admincore.staffchat;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.webhook.ChatLogger;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static dev.rudrecciah.admincore.Main.plugin;

public class StaffChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0) {
            if(sender instanceof Player) {
                staffmodeChat((Player) sender, args);
            }else{
                staffmodeChat(args);
            }
        }else{
            if(sender instanceof Player) {
                sender.sendMessage(ChatColor.YELLOW + "You need to specify a message!");
            }else{
                plugin.getLogger().severe("You need to specify a message!");
            }
        }
        return true;
    }

    public static void staffmodeChat(Player p, String[] args) {
        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }
        plugin.getLogger().info("[Staff Chat Channel] " + p.getName() + ": " + message);
        ChatLogger.logChat(p, message.toString());
        List<Player> players = (List) plugin.getServer().getOnlinePlayers();
        for (Player player : players) {
            if (player.hasPermission("admincore.staff")) {
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Minecraft " + ChatColor.YELLOW + p.getName() + ": " + message);
                if(DataHandler.getBoolean(player, "notifs") && p != player) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
            }
        }
    }

    public static void staffmodeChat(String[] args) {
        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }
        plugin.getLogger().info("[Staff Chat Channel] " + "Console: " + message);
        ChatLogger.logChat(message.toString());
        List<Player> players = (List) plugin.getServer().getOnlinePlayers();
        for (Player player : players) {
            if (player.hasPermission("admincore.staff")) {
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Minecraft " + ChatColor.YELLOW + "Console: " + message);
                if(DataHandler.getBoolean(player, "notifs")) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
            }
        }
    }
}
