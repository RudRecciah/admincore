package dev.rudrecciah.admincore.data;

import org.bukkit.entity.Player;

public class DataHandler {
    public static void handlePlayerData(Player p) {
        DataLoader.get().addDefault("players." + p.getUniqueId() + ".notifs", true);
        DataLoader.get().addDefault("players." + p.getUniqueId() + ".pings", true);
        DataLoader.saveData();
    }
}
