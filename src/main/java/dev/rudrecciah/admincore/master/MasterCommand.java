package dev.rudrecciah.admincore.master;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.report.data.ReportDataHandler;
import dev.rudrecciah.admincore.webhook.BanLogger;
import dev.rudrecciah.admincore.webhook.MuteLogger;
import dev.rudrecciah.admincore.webhook.ReportLogger;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class MasterCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length >= 2) {
            if(args[2].equalsIgnoreCase("uuid")) {
                args[1] = plugin.getServer().getOfflinePlayer(UUID.fromString(args[2])).getName();
            }
            if(args.length < 4) {
                sender.sendMessage(ChatColor.YELLOW + "Hey, I'm Admincore! Learn more at https://rudrecciah.dev/admincore and find other plugins in the Core family at https://rudrecciah.dev/core!");
            }else if(sender instanceof Player) {
                sender.sendMessage(ChatColor.YELLOW + "Hey, I'm Admincore! Learn more at https://rudrecciah.dev/admincore and find other plugins in the Core family at https://rudrecciah.dev/core!");
            }else if(args[0].equalsIgnoreCase("REPORT") && plugin.getServer().getOfflinePlayer(args[1]).hasPlayedBefore() && Integer.parseInt(args[3]) > 0 && Integer.parseInt(args[3]) < 9) {
                int i = Integer.parseInt(args[3]);
                if(args[2].equalsIgnoreCase("player")) {
                    OfflinePlayer target = plugin.getServer().getOfflinePlayer(args[1]);
                    if(args.length > 4 && args[4].equalsIgnoreCase("api")) {
                        int num = ReportDataHandler.handleAutoReportData(target, i, "AdmincoreReportAPI");
                        ReportLogger.logAutoReport(target, i, "AdmincoreReportAPI", num);
                        plugin.getLogger().info(target.getName() + " was just reported for " + plugin.getConfig().getString("staffmode.punishment.report.reasons." + (i + 1)) + "!");
                        return true;
                    }
                    int num = ReportDataHandler.handleAutoReportData(target, i, "Console");
                    ReportLogger.logAutoReport(target, i, "Console", num);
                    plugin.getLogger().info(target.getName() + " was just reported for " + plugin.getConfig().getString("staffmode.punishment.report.reasons." + (i + 1)) + "!");
                    return true;
                }
                if(args[2].equalsIgnoreCase("uuid")) {
                    OfflinePlayer target = plugin.getServer().getOfflinePlayer(UUID.fromString(args[1]));
                    if(args.length > 4 && args[4].equalsIgnoreCase("api")) {
                        int num = ReportDataHandler.handleAutoReportData(target, i, "AdmincoreReportAPI");
                        ReportLogger.logAutoReport(target, i, "AdmincoreReportAPI", num);
                        plugin.getLogger().info(target.getName() + " was just reported for " + plugin.getConfig().getString("staffmode.punishment.report.reasons." + (i + 1)) + "!");
                        return true;
                    }
                    int num = ReportDataHandler.handleAutoReportData(target, i, "Console");
                    ReportLogger.logAutoReport(target, i, "Console", num);
                    plugin.getLogger().info(target.getName() + " was just reported for " + plugin.getConfig().getString("staffmode.punishment.report.reasons." + (i + 1)) + "!");
                    return true;
                }
                return true;
            }else if(args[0].equalsIgnoreCase("MUTE") && plugin.getServer().getOfflinePlayer(args[1]).hasPlayedBefore() && Integer.parseInt(args[3]) > 0 && Integer.parseInt(args[3]) < 9) {
                int i = Integer.parseInt(args[3]);
                if(args[2].equalsIgnoreCase("player")) {
                    OfflinePlayer target = plugin.getServer().getOfflinePlayer(args[1]);
                    if(args.length > 4 && args[4].equalsIgnoreCase("api")) {
                        int muteLength = plugin.getConfig().getInt("staffmode.punishment.mute.mute-length");
                        if(!PlayerDataHandler.muteExpired(target.getUniqueId())) {
                            long muteEnd = System.currentTimeMillis() + (muteLength * 60000L);
                            PlayerDataHandler.mute(target, muteEnd);
                            MuteLogger.logMute("AdmincoreMuteAPI", muteLength, target, i + 1);
                            if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-mute")) {
                                ReportDataHandler.closeAllReports(target.getUniqueId());
                            }
                            if(target.getPlayer() != null) {
                                target.getPlayer().sendMessage(ChatColor.YELLOW + "You have been muted for " + muteLength + " minutes! Reason: " + plugin.getConfig().getString("staffmode.punishment.mute.reasons." + (i + 1)));
                                if(plugin.getConfig().getBoolean("staffmode.punishment.appeals.mute.allow-appeals")) {
                                    target.getPlayer().sendMessage(ChatColor.YELLOW + plugin.getConfig().getString("staffmode.punishment.appeals.mute.message"));
                                }
                            }
                        }
                        return true;
                    }
                    int muteLength = plugin.getConfig().getInt("staffmode.punishment.mute.mute-length");
                    if(!PlayerDataHandler.muteExpired(target.getUniqueId())) {
                        long muteEnd = System.currentTimeMillis() + (muteLength * 60000L);
                        PlayerDataHandler.mute(target, muteEnd);
                        MuteLogger.logMute("Console", muteLength, target, i + 1);
                        if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-mute")) {
                            ReportDataHandler.closeAllReports(target.getUniqueId());
                        }
                        if(target.getPlayer() != null) {
                            target.getPlayer().sendMessage(ChatColor.YELLOW + "You have been muted for " + muteLength + " minutes! Reason: " + plugin.getConfig().getString("staffmode.punishment.mute.reasons." + (i + 1)));
                            if(plugin.getConfig().getBoolean("staffmode.punishment.appeals.mute.allow-appeals")) {
                                target.getPlayer().sendMessage(ChatColor.YELLOW + plugin.getConfig().getString("staffmode.punishment.appeals.mute.message"));
                            }
                        }
                    }else{
                        plugin.getLogger().severe(target.getName() + " is already muted!");
                    }
                    return true;
                }
                if(args[2].equalsIgnoreCase("uuid")) {
                    OfflinePlayer target = plugin.getServer().getOfflinePlayer(UUID.fromString(args[1]));
                    if(args.length > 4 && args[4].equalsIgnoreCase("api")) {
                        int muteLength = plugin.getConfig().getInt("staffmode.punishment.mute.mute-length");
                        if(!PlayerDataHandler.muteExpired(target.getUniqueId())) {
                            long muteEnd = System.currentTimeMillis() + (muteLength * 60000L);
                            PlayerDataHandler.mute(target, muteEnd);
                            MuteLogger.logMute("AdmincoreMuteAPI", muteLength, target, i + 1);
                            if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-mute")) {
                                ReportDataHandler.closeAllReports(target.getUniqueId());
                            }
                            if(target.getPlayer() != null) {
                                target.getPlayer().sendMessage(ChatColor.YELLOW + "You have been muted for " + muteLength + " minutes! Reason: " + plugin.getConfig().getString("staffmode.punishment.mute.reasons." + (i + 1)));
                                if(plugin.getConfig().getBoolean("staffmode.punishment.appeals.mute.allow-appeals")) {
                                    target.getPlayer().sendMessage(ChatColor.YELLOW + plugin.getConfig().getString("staffmode.punishment.appeals.mute.message"));
                                }
                            }
                        }
                        return true;
                    }
                    int muteLength = plugin.getConfig().getInt("staffmode.punishment.mute.mute-length");
                    if(!PlayerDataHandler.muteExpired(target.getUniqueId())) {
                        long muteEnd = System.currentTimeMillis() + (muteLength * 60000L);
                        PlayerDataHandler.mute(target, muteEnd);
                        MuteLogger.logMute("Console", muteLength, target, i + 1);
                        if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-mute")) {
                            ReportDataHandler.closeAllReports(target.getUniqueId());
                        }
                        if(target.getPlayer() != null) {
                            target.getPlayer().sendMessage(ChatColor.YELLOW + "You have been muted for " + muteLength + " minutes! Reason: " + plugin.getConfig().getString("staffmode.punishment.mute.reasons." + (i + 1)));
                            if(plugin.getConfig().getBoolean("staffmode.punishment.appeals.mute.allow-appeals")) {
                                target.getPlayer().sendMessage(ChatColor.YELLOW + plugin.getConfig().getString("staffmode.punishment.appeals.mute.message"));
                            }
                        }
                    }else{
                        plugin.getLogger().severe(target.getName() + " is already muted!");
                    }
                    return true;
                }
                return true;
            }else if(args[0].equalsIgnoreCase("TEMPBAN") && plugin.getServer().getOfflinePlayer(args[1]).hasPlayedBefore() && Integer.parseInt(args[3]) > 0 && Integer.parseInt(args[3]) < 9) {
                int i = Integer.parseInt(args[3]);
                if(args[2].equalsIgnoreCase("player")) {
                    OfflinePlayer target = plugin.getServer().getOfflinePlayer(args[1]);
                    Date endLD = Date.from(LocalDate.now().plusDays(plugin.getConfig().getLong("staffmode.punishment.ban.tempban-length")).atStartOfDay().toInstant(ZoneOffset.UTC));
                    StringBuilder appeal = new StringBuilder();
                    if(plugin.getConfig().getBoolean("staffmode.punishment.appeals.tempban.allow-appeals")) {
                        appeal.append("\n").append(plugin.getConfig().getString("staffmode.punishment.appeals.ban.message"));
                    }
                    if(args.length > 4 && args[4].equalsIgnoreCase("api")) {
                        plugin.getServer().getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, endLD, "AdmincoreBanAPI");
                        if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-tempban")) {
                            ReportDataHandler.closeAllReports(target.getUniqueId());
                        }
                        if(target.getPlayer() != null) {
                            target.getPlayer().kickPlayer("You have been banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " for " + plugin.getConfig().getLong("staffmode.punishment.ban.tempban-length") + " days!" + appeal.toString());
                        }
                        PlayerDataHandler.ban(target);
                        BanLogger.logBan(target);
                        return true;
                    }
                    plugin.getServer().getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, endLD, "Console");
                    if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-tempban")) {
                        ReportDataHandler.closeAllReports(target.getUniqueId());
                    }
                    if(target.getPlayer() != null) {
                        target.getPlayer().kickPlayer("You have been banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " for " + plugin.getConfig().getLong("staffmode.punishment.ban.tempban-length") + " days!" + appeal.toString());
                    }
                    PlayerDataHandler.ban(target);
                    BanLogger.logBan(target);
                    return true;
                }
                if(args[2].equalsIgnoreCase("uuid")) {
                    OfflinePlayer target = plugin.getServer().getOfflinePlayer(UUID.fromString(args[1]));
                    Date endLD = Date.from(LocalDate.now().plusDays(plugin.getConfig().getLong("staffmode.punishment.ban.tempban-length")).atStartOfDay().toInstant(ZoneOffset.UTC));
                    StringBuilder appeal = new StringBuilder();
                    if(plugin.getConfig().getBoolean("staffmode.punishment.appeals.tempban.allow-appeals")) {
                        appeal.append("\n").append(plugin.getConfig().getString("staffmode.punishment.appeals.ban.message"));
                    }
                    if(args.length > 4 && args[4].equalsIgnoreCase("api")) {
                        plugin.getServer().getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, endLD, "AdmincoreBanAPI");
                        if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-tempban")) {
                            ReportDataHandler.closeAllReports(target.getUniqueId());
                        }
                        if(target.getPlayer() != null) {
                            target.getPlayer().kickPlayer("You have been banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " for " + plugin.getConfig().getLong("staffmode.punishment.ban.tempban-length") + " days!" + appeal.toString());
                        }
                        PlayerDataHandler.ban(target);
                        BanLogger.logBan(target);
                        return true;
                    }
                    plugin.getServer().getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, endLD, "Console");
                    if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-tempban")) {
                        ReportDataHandler.closeAllReports(target.getUniqueId());
                    }
                    if(target.getPlayer() != null) {
                        target.getPlayer().kickPlayer("You have been banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " for " + plugin.getConfig().getLong("staffmode.punishment.ban.tempban-length") + " days!" + appeal.toString());
                    }
                    PlayerDataHandler.ban(target);
                    BanLogger.logBan(target);
                    return true;
                }
                return true;
            }else if(args[0].equalsIgnoreCase("BAN") && plugin.getServer().getOfflinePlayer(args[1]).hasPlayedBefore() && Integer.parseInt(args[3]) > 0 && Integer.parseInt(args[3]) < 9) {
                int i = Integer.parseInt(args[3]);
                if(args[2].equalsIgnoreCase("player")) {
                    OfflinePlayer target = plugin.getServer().getOfflinePlayer(args[1]);
                    StringBuilder appeal = new StringBuilder();
                    if(plugin.getConfig().getBoolean("staffmode.punishment.appeals.ban.allow-appeals")) {
                        appeal.append("\n").append(plugin.getConfig().getString("staffmode.punishment.appeals.ban.message"));
                    }
                    if(args.length > 4 && args[4].equalsIgnoreCase("api")) {
                        plugin.getServer().getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, null, "AdmincoreBanAPI");
                        if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-ban")) {
                            ReportDataHandler.closeAllReports(target.getUniqueId());
                        }
                        if(target.getPlayer() != null) {
                            target.getPlayer().kickPlayer("You have been banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " forever!" + appeal.toString());
                        }
                        PlayerDataHandler.ban(target);
                        BanLogger.logBan(target);
                        return true;
                    }
                    plugin.getServer().getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, null, "Console");
                    if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-ban")) {
                        ReportDataHandler.closeAllReports(target.getUniqueId());
                    }
                    if(target.getPlayer() != null) {
                        target.getPlayer().kickPlayer("You have been banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " forever!" + appeal.toString());
                    }
                    PlayerDataHandler.ban(target);
                    BanLogger.logBan(target);
                    return true;
                }
                if(args[2].equalsIgnoreCase("uuid")) {
                    OfflinePlayer target = plugin.getServer().getOfflinePlayer(UUID.fromString(args[1]));
                    StringBuilder appeal = new StringBuilder();
                    if(plugin.getConfig().getBoolean("staffmode.punishment.appeals.ban.allow-appeals")) {
                        appeal.append("\n").append(plugin.getConfig().getString("staffmode.punishment.appeals.ban.message"));
                    }
                    if(args.length > 4 && args[4].equalsIgnoreCase("api")) {
                        plugin.getServer().getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, null, "AdmincoreBanAPI");
                        if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-ban")) {
                            ReportDataHandler.closeAllReports(target.getUniqueId());
                        }
                        if(target.getPlayer() != null) {
                            target.getPlayer().kickPlayer("You have been banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " forever!" + appeal.toString());
                        }
                        PlayerDataHandler.ban(target);
                        BanLogger.logBan(target);
                        return true;
                    }
                    plugin.getServer().getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, null, "Console");
                    if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-ban")) {
                        ReportDataHandler.closeAllReports(target.getUniqueId());
                    }
                    if(target.getPlayer() != null) {
                        target.getPlayer().kickPlayer("You have been banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " forever!" + appeal.toString());
                    }
                    PlayerDataHandler.ban(target);
                    BanLogger.logBan(target);
                    return true;
                }
                return true;
            }else if(args[0].equalsIgnoreCase("IPBAN") && Integer.parseInt(args[3]) > 0 && Integer.parseInt(args[3]) < 9) {
                int i = Integer.parseInt(args[3]);
                if(args[2].equalsIgnoreCase("player")) {
                    OfflinePlayer target = plugin.getServer().getOfflinePlayer(args[1]);
                    StringBuilder appeal = new StringBuilder();
                    if(plugin.getConfig().getBoolean("staffmode.punishment.appeals.ip-ban.allow-appeals")) {
                        appeal.append("\n").append(plugin.getConfig().getString("staffmode.punishment.appeals.ban.message"));
                    }
                    if(args.length > 4 && args[4].equalsIgnoreCase("api")) {
                        plugin.getServer().getBanList(BanList.Type.IP).addBan(PlayerDataHandler.getIP(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, null, "AdmincoreBanAPI");
                        if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-ip-ban")) {
                            ReportDataHandler.closeAllReports(target.getUniqueId());
                        }
                        if(target.getPlayer() != null) {
                            target.getPlayer().kickPlayer("You have been ip-banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " forever!" + appeal.toString());
                        }
                        PlayerDataHandler.ban(target);
                        BanLogger.logBan(target);
                        return true;
                    }
                    plugin.getServer().getBanList(BanList.Type.IP).addBan(PlayerDataHandler.getIP(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, null, "Console");
                    if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-ip-ban")) {
                        ReportDataHandler.closeAllReports(target.getUniqueId());
                    }
                    if(target.getPlayer() != null) {
                        target.getPlayer().kickPlayer("You have been ip-banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " forever!" + appeal.toString());
                    }
                    PlayerDataHandler.ban(target);
                    BanLogger.logBan(target);
                    return true;
                }
                if(args[2].equalsIgnoreCase("uuid")) {
                    OfflinePlayer target = plugin.getServer().getOfflinePlayer(UUID.fromString(args[1]));
                    StringBuilder appeal = new StringBuilder();
                    if(plugin.getConfig().getBoolean("staffmode.punishment.appeals.ip-ban.allow-appeals")) {
                        appeal.append("\n").append(plugin.getConfig().getString("staffmode.punishment.appeals.ban.message"));
                    }
                    if(args.length > 4 && args[4].equalsIgnoreCase("api")) {
                        plugin.getServer().getBanList(BanList.Type.IP).addBan(PlayerDataHandler.getIP(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, null, "AdmincoreBanAPI");
                        if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-ip-ban")) {
                            ReportDataHandler.closeAllReports(target.getUniqueId());
                        }
                        if(target.getPlayer() != null) {
                            target.getPlayer().kickPlayer("You have been ip-banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " forever!" + appeal.toString());
                        }
                        PlayerDataHandler.ban(target);
                        BanLogger.logBan(target);
                        return true;
                    }
                    plugin.getServer().getBanList(BanList.Type.IP).addBan(PlayerDataHandler.getIP(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, null, "Console");
                    if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-ip-ban")) {
                        ReportDataHandler.closeAllReports(target.getUniqueId());
                    }
                    if(target.getPlayer() != null) {
                        target.getPlayer().kickPlayer("You have been ip-banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " forever!" + appeal.toString());
                    }
                    PlayerDataHandler.ban(target);
                    BanLogger.logBan(target);
                    return true;
                }
                if(args[2].equalsIgnoreCase("ip")) {
                    String targetIp = args[1];
                    StringBuilder uuidBuilder = new StringBuilder();
                    for(OfflinePlayer player : plugin.getServer().getOfflinePlayers()) {
                        if(PlayerDataHandler.getIP(player.getUniqueId()).equalsIgnoreCase(targetIp)) {
                            uuidBuilder.append(player.getUniqueId());
                            break;
                        }
                    }
                    if(uuidBuilder.toString().isEmpty()) {
                        return true;
                    }
                    OfflinePlayer target = plugin.getServer().getOfflinePlayer(UUID.fromString(uuidBuilder.toString()));
                    StringBuilder appeal = new StringBuilder();
                    if(plugin.getConfig().getBoolean("staffmode.punishment.appeals.ip-ban.allow-appeals")) {
                        appeal.append("\n").append(plugin.getConfig().getString("staffmode.punishment.appeals.ban.message"));
                    }
                    if(args.length > 4 && args[4].equalsIgnoreCase("api")) {
                        plugin.getServer().getBanList(BanList.Type.IP).addBan(PlayerDataHandler.getIP(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, null, "AdmincoreBanAPI");
                        if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-ip-ban")) {
                            ReportDataHandler.closeAllReports(target.getUniqueId());
                        }
                        if(target.getPlayer() != null) {
                            target.getPlayer().kickPlayer("You have been ip-banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " forever!" + appeal.toString());
                        }
                        PlayerDataHandler.ban(target);
                        BanLogger.logBan(target);
                        return true;
                    }
                    plugin.getServer().getBanList(BanList.Type.IP).addBan(PlayerDataHandler.getIP(target.getUniqueId()), plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + "\n" + appeal, null, "Console");
                    if(plugin.getConfig().getBoolean("staffmode.punishment.report.autoclose.close-on-ip-ban")) {
                        ReportDataHandler.closeAllReports(target.getUniqueId());
                    }
                    if(target.getPlayer() != null) {
                        target.getPlayer().kickPlayer("You have been ip-banned for " + plugin.getConfig().getString("staffmode.punishment.ban.reasons." + (i + 1)) + " forever!" + appeal.toString());
                    }
                    PlayerDataHandler.ban(target);
                    BanLogger.logBan(target);
                    return true;
                }
                return true;
            }
            sender.sendMessage(ChatColor.YELLOW + "Hey, I'm Admincore! Learn more at https://rudrecciah.dev/admincore and find other plugins in the Core family at https://rudrecciah.dev/core!");
            return true;
        }
        sender.sendMessage(ChatColor.YELLOW + "Hey, I'm Admincore! Learn more at https://rudrecciah.dev/admincore and find other plugins in the Core family at https://rudrecciah.dev/core!");
        return true;
    }
}
