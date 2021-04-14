package dev.rudrecciah.admincore.appeal.cmd;

import dev.rudrecciah.admincore.appeal.data.Appeal;
import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.punishlogs.PunishmentLogger;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class Acceptor {
    public static boolean accept(Appeal appeal, Player p) {
        if(appeal == null || appeal.uuid.equalsIgnoreCase("unknown")) {
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "That appeal either does not exist or was already closed!");
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
            if(appeal != null) {
                File file = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "ad", appeal.id + ".yml");
                if(file.exists()) {
                    file.delete();
                }
            }
            return false;
        }
        if(appeal.type.equalsIgnoreCase("IP_BAN") && !appeal.ip.equalsIgnoreCase("unknown")) {
            plugin.getServer().getBanList(BanList.Type.IP).pardon(appeal.ip);
            PunishmentLogger.logIpPardon(appeal.ip, p.getName());
            File file = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "ad", appeal.id + ".yml");
            if(file.exists()) {
                file.delete();
            }
            return true;
        }else if(appeal.type.equalsIgnoreCase("MUTE")) {
            PlayerDataHandler.unmute(plugin.getServer().getOfflinePlayer(UUID.fromString(appeal.uuid)));
            PunishmentLogger.logUnmute(plugin.getServer().getOfflinePlayer(UUID.fromString(appeal.uuid)), p.getName());
            if(plugin.getServer().getPlayer(UUID.fromString(appeal.uuid)) != null) {
                plugin.getServer().getPlayer(UUID.fromString(appeal.uuid)).sendMessage(ChatColor.YELLOW + "You have been unmuted!");
            }
            File file = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "ad", appeal.id + ".yml");
            if(file.exists()) {
                file.delete();
            }
            return true;
        }else if(appeal.type.equalsIgnoreCase("BAN")) {
            plugin.getServer().getBanList(BanList.Type.NAME).pardon(appeal.uuid);
            PunishmentLogger.logPardon(plugin.getServer().getOfflinePlayer(UUID.fromString(appeal.uuid)).getName(), p.getName());
            File file = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "ad", appeal.id + ".yml");
            if(file.exists()) {
                file.delete();
            }
            return true;
        }else{
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "That appeal is invalid and you cannot accept it! It will automatically be rejected.");
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
            File file = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "ad", appeal.id + ".yml");
            if(file.exists()) {
                file.delete();
            }
            return false;
        }
    }
}
