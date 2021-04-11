package dev.rudrecciah.admincore.unban;

import dev.rudrecciah.admincore.punishlogs.PunishmentLogger;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static dev.rudrecciah.admincore.Main.plugin;

public class Unipbanner implements CommandExecutor {
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
        if(plugin.getServer().getBanList(BanList.Type.IP).isBanned(args[0])) {
            PunishmentLogger.logIpPardon(args[0], sender.getName());
            plugin.getServer().getBanList(BanList.Type.IP).pardon(args[0]);
            return true;
        }
        if(sender instanceof Player) {
            sender.sendMessage(ChatColor.YELLOW + "That IP address isn't banned!");
            return true;
        }
        plugin.getLogger().severe("That IP address isn't banned!");
        return true;
    }
}
