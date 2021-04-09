package dev.rudrecciah.admincore.notifs;

import dev.rudrecciah.admincore.data.DataHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static dev.rudrecciah.admincore.Main.plugin;

public class NotificationHandler implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            plugin.getLogger().info("This command can only be executed by a player!");
            return true;
        }
        DataHandler.toggleNotifs((Player) sender);
        return true;
    }
}
