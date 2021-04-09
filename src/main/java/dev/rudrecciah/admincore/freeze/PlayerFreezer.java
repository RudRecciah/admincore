package dev.rudrecciah.admincore.freeze;

import dev.rudrecciah.admincore.data.DataHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import static dev.rudrecciah.admincore.Main.plugin;

public class PlayerFreezer implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            plugin.getLogger().warning("You can execute this command from the console, but make sure there's a staff member online ready to inspect the frozen player!");
        }
        if(plugin.getServer().getPlayer(args[0]) == sender) {
            sender.sendMessage(ChatColor.YELLOW + "You can't freeze yourself!");
        }
        if(args.length != 1) {
            if(sender instanceof Player) {
                sender.sendMessage(ChatColor.YELLOW + "You need to specify a player!");
                return true;
            }
            plugin.getLogger().severe("You need to specify a player!");
            return true;
        }
        if(plugin.getServer().getPlayer(args[0]) == null) {
            if(sender instanceof Player) {
                sender.sendMessage(ChatColor.YELLOW + "This player is offline!");
                return true;
            }
            plugin.getLogger().severe("This player is offline!");
            return true;
        }
        String tName = args[0];
        Player t = plugin.getServer().getPlayer(tName);
        t.setMetadata("frozen", new FixedMetadataValue(plugin, true));
        t.sendMessage(ChatColor.YELLOW + "You have been frozen! Do not move!");
        if(sender instanceof Player) {
            sender.sendMessage(ChatColor.YELLOW + tName + " has been frozen!");
        }
        return true;
    }
}
