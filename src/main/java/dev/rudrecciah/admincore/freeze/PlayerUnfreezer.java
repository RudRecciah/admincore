package dev.rudrecciah.admincore.freeze;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import static dev.rudrecciah.admincore.Main.plugin;

public class PlayerUnfreezer implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length != 1 || plugin.getServer().getPlayer(args[0]) == null) {
            return false;
        }
        String tName = args[0];
        Player t = plugin.getServer().getPlayer(tName);
        t.setMetadata("frozen", new FixedMetadataValue(plugin, false));
        t.sendMessage(ChatColor.YELLOW + "You have been unfrozen! You can now move!");
        if(sender instanceof Player) {
            sender.sendMessage(ChatColor.YELLOW + tName + " has been unfrozen!");
        }
        return true;
    }
}
