package dev.rudrecciah.admincore.appeal.cmd;

import dev.rudrecciah.admincore.appeal.data.Appeal;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Locale;
import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class AppealmodeHandler {
    public static void handleAppeal(Appeal appeal, Player p) {
        p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[APPEAL REVIEWER]");
        p.sendMessage(ChatColor.YELLOW + "Appeal ID: " + appeal.id);
        p.sendMessage(ChatColor.YELLOW + "Player: " + plugin.getServer().getOfflinePlayer(UUID.fromString(appeal.uuid)).getName());
        p.sendMessage(ChatColor.YELLOW + "Punishment Type: " + appeal.type.toLowerCase(Locale.ROOT).replaceAll("_", " "));
        p.sendMessage(ChatColor.YELLOW + "Reason to Unpunish: " + appeal.reason);
        p.sendMessage(ChatColor.YELLOW + "Evidence: " + appeal.evidence);
        if(!appeal.email.equalsIgnoreCase("unknown")) {
            p.sendMessage(ChatColor.YELLOW + "Email: " + appeal.email);
        }
        if(!appeal.number.equalsIgnoreCase("unknown")) {
            p.sendMessage(ChatColor.YELLOW + "Phone Number: " + appeal.number);
        }
        if(!appeal.discordName.equalsIgnoreCase("unknown")) {
            p.sendMessage(ChatColor.YELLOW + "Discord Name/Tag: " + appeal.discordName);
        }
        if(!appeal.discordId.equalsIgnoreCase("unknown")) {
            p.sendMessage(ChatColor.YELLOW + "Discord ID: " + appeal.discordId);
        }
        if(!appeal.punishedBefore.equalsIgnoreCase("unknown")) {
            p.sendMessage(ChatColor.YELLOW + plugin.getServer().getOfflinePlayer(UUID.fromString(appeal.uuid)).getName() + " " + appeal.punishedBefore + " been punished before!");
        }
    }
}