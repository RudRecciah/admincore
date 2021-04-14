package dev.rudrecciah.admincore.kick;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.webhook.KickLogger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static dev.rudrecciah.admincore.Main.plugin;

public class Kicker implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length < 1) {
            if(sender instanceof Player) {
                sender.sendMessage(ChatColor.YELLOW + "You need to specify a player!");
                return true;
            }
            plugin.getLogger().severe("You need to specify a player!");
        }
        if(plugin.getServer().getPlayer(args[0]) == null) {
            if(sender instanceof Player) {
                sender.sendMessage(ChatColor.YELLOW + "That player is not online!");
                return true;
            }
            plugin.getLogger().severe("That player is not online!");
            return true;
        }
        if(sender instanceof Player && !DataHandler.getMetaBoolean((Player) sender, "staffmode")) {
            sender.sendMessage(ChatColor.YELLOW + "You must be in staffmode to kick a player!");
            return true;
        }
        if(args.length < 2) {
            if(sender instanceof Player) {
                sender.sendMessage(ChatColor.YELLOW + "You need to specify a reason!");
                return true;
            }
            plugin.getLogger().severe("You need to specify a reason!");
        }
        ArrayList<String> message = new ArrayList<>();
        for(String str: args) {
            message.add(str);
        }
        message.remove(0);
        StringBuilder messageBuilder = new StringBuilder();
        for(String str : message) {
            messageBuilder.append(str + " ");
        }
        plugin.getServer().getPlayer(args[0]).kickPlayer(messageBuilder.toString());
        if(sender instanceof Player) {
            KickLogger.logKick(sender.getName(), messageBuilder.toString(), args[0]);
        }
        return true;
    }
}
