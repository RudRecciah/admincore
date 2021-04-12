package dev.rudrecciah.admincore.appeal.cmd;

import dev.rudrecciah.admincore.appeal.data.Appeal;
import dev.rudrecciah.admincore.appeal.data.AppealDataHandler;
import dev.rudrecciah.admincore.data.DataHandler;
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

public class AppealReviewHandler implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            plugin.getLogger().info("This command can only be executed by a player!");
            return true;
        }
        Player p = (Player) sender;
        if(!DataHandler.getMetaBoolean((Player) sender, "staffmode")) {
            sender.sendMessage(ChatColor.YELLOW + "You must be in staffmode to review appeals!");
            return true;
        }
        if(args.length == 0) {
            Appeal appeal = AppealDataHandler.getRandAppeal();
            if(appeal == null || appeal.uuid == null) {
                p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "There aren't any open appeals!");
                if(DataHandler.getBoolean(p, "notifs")) {
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
                return true;
            }
            p.setMetadata("appealChecking", new FixedMetadataValue(plugin, appeal.id));
            AppealmodeHandler.handleAppeal(appeal, p);
            return true;
        }
        if(args[0].equalsIgnoreCase("last")) {
            if(p.hasMetadata("appealChecking")) {
                String id = DataHandler.getMetaString(p, "reportChecking");
                Appeal appeal = AppealDataHandler.getAppeal(id);
                if(appeal == null || appeal.uuid == null) {
                    p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "That appeal was already closed!");
                    if(DataHandler.getBoolean(p, "notifs")) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                    }
                    return true;
                }
                AppealmodeHandler.handleAppeal(appeal, p);
            }
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE]" + ChatColor.YELLOW + "You have no open appeal to review!");
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("accept")) {
            if(p.hasMetadata("appealChecking")) {
                //TODO: accept appeal
                p.removeMetadata("appealChecking", plugin);
                //TODO: acceptance logger (appeal logger class)
            }
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE]" + ChatColor.YELLOW + "You have no open appeal to accept!");
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("reject")) {
            if(p.hasMetadata("appealChecking")) {
                //TODO: accept appeal
                p.removeMetadata("appealChecking", plugin);
                //TODO: rejection logger (appeal logger class)
            }
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE]" + ChatColor.YELLOW + "You have no open appeal to reject!");
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
            return true;
        }
        String id = args[0];
        Appeal appeal = AppealDataHandler.getAppeal(id);
        if(appeal == null || appeal.uuid == null) {
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "That appeal either does not exist or was already closed!");
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
            return true;
        }
        p.setMetadata("appealChecking", new FixedMetadataValue(plugin, appeal.id));
        AppealmodeHandler.handleAppeal(appeal, p);
        return true;
    }
}
