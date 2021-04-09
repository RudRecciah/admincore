package dev.rudrecciah.admincore.report.reviewer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;

import static dev.rudrecciah.admincore.Main.plugin;

public class ReportmodeHandler {
    public static void handleReport(ArrayList<Report> reports, Report report, Player p) {
        if(plugin.getServer().getOfflinePlayer(report.reported).isOnline() && plugin.getConfig().getBoolean("staffmode.punishment.report.teleport")) {
            p.teleport(plugin.getServer().getPlayer(report.reported).getLocation());
        }
        p.setMetadata("reportChecking", new FixedMetadataValue(plugin, report.reported));
        p.setMetadata("reportNum", new FixedMetadataValue(plugin, report.num));
        p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[REPORT REVIEWER]");
        p.sendMessage(ChatColor.YELLOW + "Reported Player: " + plugin.getServer().getOfflinePlayer(report.reported).getName());
        p.sendMessage(ChatColor.YELLOW + "Reason: " + plugin.getConfig().getString("staffmode.punishment.report.reasons." + (report.reason + 1)));
        p.sendMessage(ChatColor.YELLOW + "Reporter: " + plugin.getServer().getOfflinePlayer(report.reporter).getName());
        p.sendMessage(ChatColor.YELLOW + "Use \"/review close\" to close this report!");
    }
}
