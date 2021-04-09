package dev.rudrecciah.admincore.appeal.data;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class AppealDataHandler {
    public static int makeAppeal(HashMap<String, Object> map) {
        AppealDataLoader loader = new AppealDataLoader();
        if(map.get("type").toString().equalsIgnoreCase("IP_BAN")) {
            if(plugin.getServer().getBanList(BanList.Type.IP).isBanned(map.get("ip").toString())) {
                if(!loader.saveDefaultAppealData(map.get("id").toString())) {
                    loader.get().options().copyDefaults(true);
                    loader.get().addDefault("ip", map.get("ip").toString());
                    loader.get().addDefault("name", map.get("name"));
                    loader.get().addDefault("uuid", map.get("uuid").toString());
                    loader.get().addDefault("reason", map.get("reason").toString());
                    loader.get().addDefault("type", map.get("type").toString());
                    loader.get().addDefault("reason", map.get("reason").toString());
                    loader.get().addDefault("evidence", map.get("evidence").toString());
                    loader.get().addDefault("punishedBefore", map.get("punishedBefore").toString());
                    loader.get().addDefault("email", map.get("email").toString());
                    loader.get().addDefault("number", map.get("number").toString());
                    loader.get().addDefault("discordName", map.get("discordName").toString());
                    loader.get().addDefault("discordId", map.get("discordId").toString());
                    loader.saveAppealData();
                    List<Player> players = (List) plugin.getServer().getOnlinePlayers();
                    for (Player player : players) {
                        if (player.hasPermission("admincore.staff")) {
                            player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Admincore " + ChatColor.YELLOW + "Appeal Logger: " + map.get("name") + " just appealed!");
                            if(DataHandler.getBoolean(player, "notifs")) {
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                            }
                        }
                    }
                    return 201;
                }
            }
            return 412;
        }
        if(map.get("type").toString().equalsIgnoreCase("BAN")) {
            if(plugin.getServer().getBanList(BanList.Type.NAME).isBanned(map.get("uuid").toString())) {
                if(!loader.saveDefaultAppealData(map.get("id").toString())) {
                    loader.get().options().copyDefaults(true);
                    loader.get().addDefault("ip", map.get("ip").toString());
                    loader.get().addDefault("name", map.get("name"));
                    loader.get().addDefault("uuid", map.get("uuid").toString());
                    loader.get().addDefault("reason", map.get("reason").toString());
                    loader.get().addDefault("type", map.get("type").toString());
                    loader.get().addDefault("reason", map.get("reason").toString());
                    loader.get().addDefault("evidence", map.get("evidence").toString());
                    loader.get().addDefault("punishedBefore", map.get("punishedBefore").toString());
                    loader.get().addDefault("email", map.get("email").toString());
                    loader.get().addDefault("number", map.get("number").toString());
                    loader.get().addDefault("discordName", map.get("discordName").toString());
                    loader.get().addDefault("discordId", map.get("discordId").toString());
                    loader.saveAppealData();
                    List<Player> players = (List) plugin.getServer().getOnlinePlayers();
                    for (Player player : players) {
                        if (player.hasPermission("admincore.staff")) {
                            player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Admincore " + ChatColor.YELLOW + "Appeal Logger: " + map.get("name") + " just appealed!");
                            if(DataHandler.getBoolean(player, "notifs")) {
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                            }
                        }
                    }
                    return 201;
                }
            }
            return 412;
        }
        if(map.get("type").toString().equalsIgnoreCase("MUTE")) {
            if(plugin.getServer().getPlayer(UUID.fromString(map.get("uuid").toString())) != null) {
                if(PlayerDataHandler.muteExpired(plugin.getServer().getPlayer(UUID.fromString(map.get("uuid").toString()))))  {
                    if(!loader.saveDefaultAppealData(map.get("id").toString())) {
                        loader.get().options().copyDefaults(true);
                        loader.get().addDefault("ip", map.get("ip").toString());
                        loader.get().addDefault("name", map.get("name"));
                        loader.get().addDefault("uuid", map.get("uuid").toString());
                        loader.get().addDefault("reason", map.get("reason").toString());
                        loader.get().addDefault("type", map.get("type").toString());
                        loader.get().addDefault("reason", map.get("reason").toString());
                        loader.get().addDefault("evidence", map.get("evidence").toString());
                        loader.get().addDefault("punishedBefore", map.get("punishedBefore").toString());
                        loader.get().addDefault("email", map.get("email").toString());
                        loader.get().addDefault("number", map.get("number").toString());
                        loader.get().addDefault("discordName", map.get("discordName").toString());
                        loader.get().addDefault("discordId", map.get("discordId").toString());
                        loader.saveAppealData();
                        List<Player> players = (List) plugin.getServer().getOnlinePlayers();
                        for (Player player : players) {
                            if (player.hasPermission("admincore.staff")) {
                                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Admincore " + ChatColor.YELLOW + "Appeal Logger: " + map.get("name") + " just appealed!");
                                if(DataHandler.getBoolean(player, "notifs")) {
                                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                                }
                            }
                        }
                        return 201;
                    }
                }
                return 412;
            }
            return 404;
        }
        return 400;
    }
}
