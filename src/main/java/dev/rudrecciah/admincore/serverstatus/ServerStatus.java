package dev.rudrecciah.admincore.serverstatus;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static dev.rudrecciah.admincore.Main.plugin;

public class ServerStatus implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            String version = plugin.getServer().getVersion();
            String bukkitVersion = plugin.getServer().getBukkitVersion();
            String ip = plugin.getServer().getIp();
            int port = plugin.getServer().getPort();
            int players = plugin.getServer().getOnlinePlayers().size();
            String tps1MinPlaceholder = "%server_tps_1%";
            String tps5MinPlaceholder = "%server_tps_5%";
            String tps15MinPlaceholder = "%server_tps_15%";
            String uptimePlaceholder = "%server_uptime%";
            String ramUsedPlaceholder = "%server_ram_used%";
            String ramFreePlaceholder = "%server_ram_free%";
            String ramTotalPlaceholder = "%server_ram_total%";
            String pingPlaceholder = "%player_ping%";
            tps1MinPlaceholder = PlaceholderAPI.setPlaceholders(p, tps1MinPlaceholder);
            tps5MinPlaceholder = PlaceholderAPI.setPlaceholders(p, tps5MinPlaceholder);
            tps15MinPlaceholder = PlaceholderAPI.setPlaceholders(p, tps15MinPlaceholder);
            uptimePlaceholder = PlaceholderAPI.setPlaceholders(p, uptimePlaceholder);
            ramUsedPlaceholder = PlaceholderAPI.setPlaceholders(p, ramUsedPlaceholder);
            ramFreePlaceholder = PlaceholderAPI.setPlaceholders(p, ramFreePlaceholder);
            ramTotalPlaceholder = PlaceholderAPI.setPlaceholders(p, ramTotalPlaceholder);
            pingPlaceholder = PlaceholderAPI.setPlaceholders(p, pingPlaceholder);
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[SERVER STATUS]");
            if(plugin.getConfig().getBoolean("serverstatus.serverVersion.log")) {
                p.sendMessage(ChatColor.YELLOW + "Version: " + bukkitVersion);
            }if(plugin.getConfig().getBoolean("serverstatus.buildInformation.log")) {
                p.sendMessage(ChatColor.YELLOW + "Build: " + version);
            }if(plugin.getConfig().getBoolean("serverstatus.ip.log")) {
                if(plugin.getConfig().getBoolean("serverstatus.protect-sensitive")) {
                    if(p.hasPermission("admincore.trusted")) {
                        p.sendMessage(ChatColor.YELLOW + "IP Address: " + ip);
                    }
                }else{
                    p.sendMessage(ChatColor.YELLOW + "IP Address: " + ip);
                }
            }if(plugin.getConfig().getBoolean("serverstatus.port.log")) {
                if(plugin.getConfig().getBoolean("serverstatus.protect-sensitive")) {
                    if(p.hasPermission("admincore.trusted")) {
                        p.sendMessage(ChatColor.YELLOW + "Port: " + port);
                    }
                }else{
                    p.sendMessage(ChatColor.YELLOW + "Port: " + port);
                }
            }if(plugin.getConfig().getBoolean("serverstatus.playerCount.log")) {
                p.sendMessage(ChatColor.YELLOW + "Playercount: " + players + " players");
            }if(plugin.getConfig().getBoolean("serverstatus.ram.log")) {
                p.sendMessage(ChatColor.YELLOW + "Ram: " + ramUsedPlaceholder + " MB (used) " + ramFreePlaceholder + " MB (free) " + ramTotalPlaceholder + " MB (total)");
            }if(plugin.getConfig().getBoolean("serverstatus.ping.log")) {
                p.sendMessage(ChatColor.YELLOW + "Ping: " + pingPlaceholder);
            }if(plugin.getConfig().getBoolean("serverstatus.uptime.log")) {
                p.sendMessage(ChatColor.YELLOW + "Server Uptime: " + uptimePlaceholder);
            }
            StringBuilder tps = new StringBuilder();
            tps.append(ChatColor.YELLOW);
            if(plugin.getConfig().getBoolean("serverstatus.tps.log.one-minute")) {
                tps.append("TPS: ");
                char[] tps1MinPlaceholderCharArray = tps1MinPlaceholder.toCharArray();
                tps.append(tps1MinPlaceholderCharArray[0] + tps1MinPlaceholderCharArray[1] + tps1MinPlaceholderCharArray[2] + tps1MinPlaceholderCharArray[3]);
                tps.append(" (1 minute) ");
            }if(plugin.getConfig().getBoolean("serverstatus.tps.log.five-minutes")) {
                char[] tps5MinPlaceholderCharArray = tps5MinPlaceholder.toCharArray();
                tps.append(tps5MinPlaceholderCharArray[0] + tps5MinPlaceholderCharArray[1] + tps5MinPlaceholderCharArray[2] + tps5MinPlaceholderCharArray[3]);
                tps.append(" (5 minutes) ");
            }if(plugin.getConfig().getBoolean("serverstatus.tps.log.fifteen-minutes")) {
                char[] tps15MinPlaceholderCharArray = tps15MinPlaceholder.toCharArray();
                tps.append(tps15MinPlaceholderCharArray[0] + tps15MinPlaceholderCharArray[1] + tps15MinPlaceholderCharArray[2] + tps15MinPlaceholderCharArray[3]);
                tps.append(" (15 minutes)");
            }
            p.sendMessage(String.valueOf(tps));
        }else{
            plugin.getLogger().severe("This command can only be executed by a player!");
        }
        return true;
    }
}
