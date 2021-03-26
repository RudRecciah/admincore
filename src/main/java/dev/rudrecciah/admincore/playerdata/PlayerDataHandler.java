package dev.rudrecciah.admincore.playerdata;

import org.bukkit.entity.Player;

import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class PlayerDataHandler {

    public static void handlePlayerData(Player p) {
        PlayerDataLoader.saveDefaultPlayerData(p);
        PlayerDataLoader.getPlayerData().options().copyDefaults(true);
        PlayerDataLoader.getPlayerData().addDefault("ip", p.getAddress().getHostString());
        PlayerDataLoader.getPlayerData().set("ip", p.getAddress().getHostString());
        PlayerDataLoader.getPlayerData().addDefault("persistToggle.muteEnd", 0L);
        PlayerDataLoader.getPlayerData().addDefault("mutes", 0);
        PlayerDataLoader.getPlayerData().addDefault("bans", 0);
        PlayerDataLoader.savePlayerData();
    }

    public static boolean muteExpired(Player p) {
        PlayerDataLoader.saveDefaultPlayerData(p);
        PlayerDataLoader.getPlayerData().options().copyDefaults(true);
        long end = PlayerDataLoader.getPlayerData().getLong("persistToggle.muteEnd");
        if(end <= System.currentTimeMillis()) {
            return false;
        }else{
            return true;
        }
    }

    public static void mute(Player p, long l) {
        PlayerDataLoader.saveDefaultPlayerData(p);
        PlayerDataLoader.getPlayerData().options().copyDefaults(true);
        PlayerDataLoader.getPlayerData().set("persistToggle.muteEnd", l);
        PlayerDataLoader.getPlayerData().set("mutes", PlayerDataLoader.getPlayerData().getInt("mutes")+ 1);
        PlayerDataLoader.savePlayerData();
    }

    public static void unmute(Player p) {
        PlayerDataLoader.saveDefaultPlayerData(p);
        PlayerDataLoader.getPlayerData().options().copyDefaults(true);
        PlayerDataLoader.getPlayerData().set("persistToggle.muteEnd", 0);
        PlayerDataLoader.savePlayerData();
    }

    public static void ban(Player p) {
        PlayerDataLoader.saveDefaultPlayerData(p);
        PlayerDataLoader.getPlayerData().options().copyDefaults(true);
        PlayerDataLoader.getPlayerData().set("bans", PlayerDataLoader.getPlayerData().getInt("bans")+ 1);
        PlayerDataLoader.savePlayerData();
    }

    public static int getBans(Player p) {
        PlayerDataLoader.saveDefaultPlayerData(p);
        PlayerDataLoader.getPlayerData().options().copyDefaults(true);
        return PlayerDataLoader.getPlayerData().getInt("bans");
    }

    public static int getMutes(Player p) {
        PlayerDataLoader.saveDefaultPlayerData(p);
        PlayerDataLoader.getPlayerData().options().copyDefaults(true);
        return PlayerDataLoader.getPlayerData().getInt("mutes");
    }

    public static String getIP(UUID uuid) {
        PlayerDataLoader.saveDefaultPlayerData(uuid);
        PlayerDataLoader.getPlayerData().options().copyDefaults(true);
        return (String) PlayerDataLoader.getPlayerData().get("ip");
    }
}