package dev.rudrecciah.admincore.playerdata;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class PlayerDataHandler {

    public static void handlePlayerData(Player p) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(p);
        loader.getPlayerData().options().copyDefaults(true);
        loader.getPlayerData().addDefault("ip", p.getAddress().getHostString());
        loader.getPlayerData().set("ip", p.getAddress().getHostString());
        loader.getPlayerData().addDefault("persistToggle.muteEnd", 0L);
        loader.getPlayerData().addDefault("mutes", 0);
        loader.getPlayerData().addDefault("bans", 0);
        loader.savePlayerData();
    }

    public static boolean muteExpired(Player p) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(p);
        loader.getPlayerData().options().copyDefaults(true);
        long end = loader.getPlayerData().getLong("persistToggle.muteEnd");
        if(end <= System.currentTimeMillis()) {
            return false;
        }else{
            return true;
        }
    }

    public static void mute(Player p, long l) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(p);
        loader.getPlayerData().options().copyDefaults(true);
        loader.getPlayerData().set("persistToggle.muteEnd", l);
        loader.getPlayerData().set("mutes", loader.getPlayerData().getInt("mutes")+ 1);
        loader.savePlayerData();
    }

    public static void mute(OfflinePlayer p, long l) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(p.getUniqueId());
        loader.getPlayerData().options().copyDefaults(true);
        loader.getPlayerData().set("persistToggle.muteEnd", l);
        loader.getPlayerData().set("mutes", loader.getPlayerData().getInt("mutes")+ 1);
        loader.savePlayerData();
    }

    public static void unmute(OfflinePlayer p) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(p.getUniqueId());
        loader.getPlayerData().options().copyDefaults(true);
        loader.getPlayerData().set("persistToggle.muteEnd", 0);
        loader.savePlayerData();
    }

    public static void ban(Player p) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(p);
        loader.getPlayerData().options().copyDefaults(true);
        loader.getPlayerData().set("bans", loader.getPlayerData().getInt("bans")+ 1);
        loader.savePlayerData();
    }

    public static void ban(OfflinePlayer p) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(p.getUniqueId());
        loader.getPlayerData().options().copyDefaults(true);
        loader.getPlayerData().set("bans", loader.getPlayerData().getInt("bans")+ 1);
        loader.savePlayerData();
    }

    public static int getBans(Player p) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(p);
        loader.getPlayerData().options().copyDefaults(true);
        return loader.getPlayerData().getInt("bans");
    }

    public static int getMutes(Player p) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(p);
        loader.getPlayerData().options().copyDefaults(true);
        return loader.getPlayerData().getInt("mutes");
    }

    public static int getBans(OfflinePlayer p) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(p.getUniqueId());
        loader.getPlayerData().options().copyDefaults(true);
        return loader.getPlayerData().getInt("bans");
    }

    public static int getMutes(OfflinePlayer p) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(p.getUniqueId());
        loader.getPlayerData().options().copyDefaults(true);
        return loader.getPlayerData().getInt("mutes");
    }

    public static String getIP(UUID uuid) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(uuid);
        loader.getPlayerData().options().copyDefaults(true);
        return (String) loader.getPlayerData().get("ip");
    }

    public static boolean muteExpired(UUID uuid) {
        PlayerDataLoader loader = new PlayerDataLoader();
        loader.saveDefaultPlayerData(uuid);
        loader.getPlayerData().options().copyDefaults(true);
        long end = loader.getPlayerData().getLong("persistToggle.muteEnd");
        if(end <= System.currentTimeMillis()) {
            return false;
        }else{
            return true;
        }
    }
}