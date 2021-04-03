package dev.rudrecciah.admincore.history;

import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Locale;

import static dev.rudrecciah.admincore.Main.plugin;

public class HistoryLogger implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            plugin.getLogger().severe("This command can only be executed by a player!");
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "You need to specify a player!");
            return true;
        }
        if(!plugin.getServer().getOfflinePlayer(args[0]).hasPlayedBefore()) {
            sender.sendMessage(ChatColor.YELLOW + "Either this player does not exist, the player has not joined, or the server doesn't know about the player!");
            return true;
        }
        int mutes = PlayerDataHandler.getMutes(plugin.getServer().getOfflinePlayer(args[0]));
        int bans = PlayerDataHandler.getBans(plugin.getServer().getOfflinePlayer(args[0]));
        sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[" + args[0].toUpperCase(Locale.ROOT) + "'S PUNISHMENT HISTORY]");
        sender.sendMessage(ChatColor.YELLOW + "Mutes: " + mutes);
        sender.sendMessage(ChatColor.YELLOW + "Bans: " + bans);
        return true;
    }
}
