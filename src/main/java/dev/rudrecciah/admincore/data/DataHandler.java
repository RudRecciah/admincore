package dev.rudrecciah.admincore.data;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import static dev.rudrecciah.admincore.Main.plugin;

public class DataHandler {
    public static void handlePlayerData(Player p) {
        DataLoader.get().addDefault("players." + p.getUniqueId() + ".notifs", true);
        DataLoader.saveData();
    }

    public static boolean getBoolean(Player p, String path) {
        boolean exists = DataLoader.get().isBoolean("players." + p.getUniqueId() + "." + path);
        if(exists) {
            return DataLoader.get().getBoolean("players." + p.getUniqueId() + "." + path);
        }else{
            DataLoader.get().addDefault("players." + p.getUniqueId() + "." + path, true);
            DataLoader.saveData();
            return true;
        }
    }
}
