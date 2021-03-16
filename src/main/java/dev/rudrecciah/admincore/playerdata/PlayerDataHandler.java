package dev.rudrecciah.admincore.playerdata;

import dev.rudrecciah.admincore.data.DataLoader;
import org.bukkit.entity.Player;

public class PlayerDataHandler {

    public static void handlePlayerData(Player p) {
        PlayerDataLoader.saveDefaultPlayerData(p);
        PlayerDataLoader.getPlayerData().options().copyDefaults(true);
        PlayerDataLoader.getPlayerData().addDefault("ip", p.getAddress().getHostString());
        PlayerDataLoader.getPlayerData().addDefault("persistToggle.muteEnd", 0L);
        PlayerDataLoader.savePlayerData();
    }

    public static boolean muteExpired(Player p) {
        PlayerDataLoader.saveDefaultPlayerData(p);
        PlayerDataLoader.getPlayerData().options().copyDefaults(true);
        int end = PlayerDataLoader.getPlayerData().getInt("persistToggle.muteEnd");
        if(end > System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    public static void mute(Player p, long l) {
        PlayerDataLoader.saveDefaultPlayerData(p);
        PlayerDataLoader.getPlayerData().options().copyDefaults(true);
        PlayerDataLoader.getPlayerData().set("persistToggle.muteEnd", l);
        PlayerDataLoader.savePlayerData();
    }
}