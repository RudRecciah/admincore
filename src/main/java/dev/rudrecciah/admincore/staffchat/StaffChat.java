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
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 0) {
                staffmodeChat(p, args);
            } else {
                p.sendMessage(ChatColor.YELLOW + "You need to specify a message!");
                return true;
            }
        }else{
            plugin.getLogger().severe("This command can only be executed by a player!");
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
}
