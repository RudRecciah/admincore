package dev.rudrecciah.admincore.appeal.data;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.webhook.AppealLogger;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class AppealDataHandler {
    public static int makeAppeal(HashMap<String, Object> map) {
        AppealDataLoader loader = new AppealDataLoader();
        if(map.get("type").toString().equalsIgnoreCase("IP_BAN")) {
            if(plugin.getServer().getBanList(BanList.Type.IP).isBanned(map.get("ip").toString()) && !appealExists("IP_BAN",  map.get("ip").toString())) {
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
                            player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Admincore " + ChatColor.YELLOW + "Appeal Logger: " + map.get("name") + " just appealed!" + "\n" + "ID: " + map.get("id"));
                            if(DataHandler.getBoolean(player, "notifs")) {
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                            }
                        }
                    }
                    String[] data = {map.get("name").toString(), map.get("uuid").toString(), "IP Ban", map.get("id").toString()};
                    AppealLogger.logAppeal(data);
                    return 201;
                }
                return 302;
            }
            return 412;
        }
        if(map.get("type").toString().equalsIgnoreCase("BAN") && !appealExists("BAN",  map.get("uuid").toString())) {
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
                            player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Admincore " + ChatColor.YELLOW + "Appeal Logger: " + map.get("name") + " just appealed!" + "\n" + "ID: " + map.get("id"));
                            if(DataHandler.getBoolean(player, "notifs")) {
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                            }
                        }
                    }
                    String[] data = {map.get("name").toString(), map.get("uuid").toString(), "Ban", map.get("id").toString()};
                    AppealLogger.logAppeal(data);
                    return 201;
                }
                return 302;
            }
            return 412;
        }
        if(map.get("type").toString().equalsIgnoreCase("MUTE") && !appealExists("MUTE",  map.get("uuid").toString())) {
            if(PlayerDataHandler.muteExpired(UUID.fromString(map.get("uuid").toString()))) {
                if(!loader.saveDefaultAppealData(map.get("id").toString())) {
                    loader.get().options().copyDefaults(true);
                    loader.get().addDefault("ip", map.get("ip").toString());
                    loader.get().addDefault("name", map.get("name"));
                    loader.get().addDefault("uuid", map.get("uuid").toString());
                    loader.get().addDefault("reason", map.get("reason").toString());
                    loader.get().addDefault("type", map.get("type").toString());
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
                            player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Admincore " + ChatColor.YELLOW + "Appeal Logger: " + map.get("name") + " just appealed!" + "\n" + "ID: " + map.get("id"));
                            if(DataHandler.getBoolean(player, "notifs")) {
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                            }
                        }
                    }
                    String[] data = {map.get("name").toString(), map.get("uuid").toString(), "Mute", map.get("id").toString()};
                    AppealLogger.logAppeal(data);
                    return 201;
                }
                return 302;
            }
            return 412;
        }
        return 400;
    }

    public static Appeal getRandAppeal() {
        File dir = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "ad");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String[] files = dir.list();
        for(String fileName : files) {
            File file = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "ad", fileName);
            if(file.exists()) {
                return new Appeal(YamlConfiguration.loadConfiguration(file), fileName.replaceAll(".yml", ""));
            }
        }
        return null;
    }

    public static Appeal getAppeal(String id) {
        File file = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "ad", id + ".yml");
        if(file.exists()) {
            return new Appeal(YamlConfiguration.loadConfiguration(file), id);
        }
        return null;
    }

    private static boolean appealExists(String type, String id) {
        File dir = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "ad");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String[] files = dir.list();
        for(String fileName : files) {
            if(fileName.replaceAll(".yml", "").equalsIgnoreCase(id)) {
                File file = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "ad", fileName);
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
                yaml.options().copyDefaults(true);
                if(yaml.getString("type").equalsIgnoreCase(type)) {
                    return true;
                }
            }
        }
        return false;
    }
}
