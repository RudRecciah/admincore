package dev.rudrecciah.admincore.mute;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.staffmode.menus.MainMenu;
import dev.rudrecciah.admincore.staffmode.menus.MuteMenu;
import dev.rudrecciah.admincore.webhook.MuteLogger;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import static dev.rudrecciah.admincore.Main.plugin;

public class Muter implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            plugin.getLogger().info("This command can only be executed by a player!");
        }
        Player p = (Player) sender;
        if(args.length != 1) {
            sender.sendMessage(ChatColor.YELLOW + "You need to specify a player!");
            return true;
        }
        if(!plugin.getServer().getOfflinePlayer(args[0]).hasPlayedBefore()) {
            sender.sendMessage(ChatColor.YELLOW + "This player has never played before!");
            return true;
        }
        if(!DataHandler.getMetaBoolean(p, "staffmode")) {
            p.sendMessage(ChatColor.YELLOW + "You must be in staffmode to mute a player!");
            return true;
        }
        p.setMetadata("staffmodeChecking", new FixedMetadataValue(plugin, plugin.getServer().getOfflinePlayer(args[0]).getUniqueId()));
        MuteMenu.openMenu(p);
        return true;
    }
}
