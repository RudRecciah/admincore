package dev.rudrecciah.admincore.mute;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.staffmode.menus.MainMenu;
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
        if(args.length != 1 || plugin.getServer().getPlayer(args[0]) == null) {
            return false;
        }
        if(!DataHandler.getMetaBoolean((Player) sender, "staffmode")) {
            p.sendMessage(ChatColor.YELLOW + "You must be in staffmode to mute a player!");
        }
        if(!plugin.getServer().getPlayer(args[0]).hasPermission("admincore.staff")) {
            p.sendMessage(ChatColor.YELLOW + "You can't mute a staff member!");
        }
        p.setMetadata("staffmodeChecking", new FixedMetadataValue(plugin, plugin.getServer().getPlayer(args[0]).getUniqueId()));
        Player t = plugin.getServer().getPlayer(args[0]);
        int muteLength = plugin.getConfig().getInt("staffmode.punishment.mute.mute-length");
        if(!PlayerDataHandler.muteExpired(t)) {
            long muteEnd = System.currentTimeMillis() + (muteLength * 60000L);
            PlayerDataHandler.mute(t, muteEnd);
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + t.getName() + " has been muted for " + muteLength + " minutes!");
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
            t.sendMessage(ChatColor.YELLOW + "You have been muted for " + muteLength + " minutes! Reason: " + plugin.getConfig().getString("staffmode.punishment.mute.reason"));
        }else{
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + t.getName() + " is already muted!");
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
        }
        return true;
    }
}
