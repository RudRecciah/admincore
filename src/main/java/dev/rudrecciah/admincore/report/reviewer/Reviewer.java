package dev.rudrecciah.admincore.report.reviewer;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.report.data.ReportDataHandler;
import dev.rudrecciah.admincore.report.data.ReportDataLoader;
import dev.rudrecciah.admincore.webhook.ReportLogger;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class Reviewer implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            plugin.getLogger().severe("This command can only be executed by a player!");
            return true;
        }
        if(!DataHandler.getMetaBoolean((Player) sender, "staffmode")) {
            sender.sendMessage(ChatColor.YELLOW + "You must be in staffmode to review a player's reports!");
            return true;
        }
        if(args.length != 0 && !args[0].equalsIgnoreCase("last") && !args[0].equalsIgnoreCase("close") && plugin.getServer().getOfflinePlayer(args[0]).getUniqueId().toString() == null) {
            sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "This player either does not exist, or the server has no record of them stored!");
            Player p = (Player) sender;
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
            return true;
        }
        Player p = (Player) sender;
        UUID t = null;
        if(args.length != 0 && args[0].equalsIgnoreCase("last")) {
            if(p.hasMetadata("reportChecking")) {
                t = UUID.fromString(DataHandler.getMetaString(p, "reportChecking"));
            }else{
                p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "You have no player to continue checking reports for!");
                if(DataHandler.getBoolean(p, "notifs")) {
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
                return true;
            }
        }
        if(args.length != 0 && args[0].equalsIgnoreCase("close")) {
            if(p.hasMetadata("reportChecking") && p.hasMetadata("reportNum")) {
                ReportDataHandler.closeReport(UUID.fromString(DataHandler.getMetaString(p, "reportChecking")), DataHandler.getMetaInt(p, "reportNum"));
                ReportLogger.logReportClose(UUID.fromString(DataHandler.getMetaString(p, "reportChecking")), DataHandler.getMetaInt(p, "reportNum"));
                p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "Report closed!");
                if(DataHandler.getBoolean(p, "notifs")) {
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
            }else{
                p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "You have no report to close!");
                if(DataHandler.getBoolean(p, "notifs")) {
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
            }
            return true;
        }
        if(args.length != 0) {
            ArrayList<Report> reports;
            if(t != null) {
                reports = Getter.getReports(t);
            }else{
                reports = Getter.getReports(plugin.getServer().getOfflinePlayer(args[0]).getUniqueId());
            }
            if(reports == null) {
                p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "There aren't any open reports for that player!");
                if(DataHandler.getBoolean(p, "notifs")) {
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
                return true;
            }
            ReportmodeHandler.handleReport(reports, reports.get(0), p);
            return true;
        }
        UUID uuid = Getter.getRandUUID(t, p);
        if(uuid != null) {
            ArrayList<Report> reports = Getter.getReports(uuid);
            if(reports == null) {
                p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "There aren't any open reports!");
                if(DataHandler.getBoolean(p, "notifs")) {
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
                return true;
            }
            ReportmodeHandler.handleReport(reports, reports.get(0), p);
            return true;
        }
        p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "There aren't any open reports!");
        if(DataHandler.getBoolean(p, "notifs")) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
        }
        return true;
    }
}
