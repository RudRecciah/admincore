package dev.rudrecciah.admincore.mute;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.punishlogs.PunishmentLogger;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static dev.rudrecciah.admincore.Main.plugin;

public class Unmuter implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length != 1) {
            if(sender instanceof Player) {
                sender.sendMessage(ChatColor.YELLOW + "You need to specify a player!");
                return true;
            }
            plugin.getLogger().severe("You need to specify a player!");
            return true;
        }
        if(!plugin.getServer().getOfflinePlayer(args[0]).hasPlayedBefore()) {
            if(sender instanceof Player) {
                sender.sendMessage(ChatColor.YELLOW + "This player has never joined the server!");
                return true;
            }
            plugin.getLogger().severe("This player has never joined the server!");
            return true;
        }
        if(sender instanceof Player && !DataHandler.getMetaBoolean((Player) sender, "staffmode")) {
            sender.sendMessage(ChatColor.YELLOW + "You must be in staffmode to unmute a player!");
            return true;
        }
        PlayerDataHandler.unmute(plugin.getServer().getOfflinePlayer(args[0]));
        PunishmentLogger.logUnmute(plugin.getServer().getOfflinePlayer(args[0]), sender.getName());
        if(plugin.getServer().getPlayer(args[0]) != null) {
            plugin.getServer().getPlayer(args[0]).sendMessage(ChatColor.YELLOW + "You have been unmuted!");
        }
        if(sender instanceof ConsoleCommandSender) {
            plugin.getLogger().info(args[0] + " has been unmuted!");
        }else{
            Player p = (Player) sender;
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + args[0] + " has been unmuted!");
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1f, 1f);
            }
        }
        return true;
    }
}
