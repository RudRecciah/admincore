package dev.rudrecciah.admincore.data;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

import static dev.rudrecciah.admincore.Main.plugin;

public class DataHandler {
    public static void handlePlayerData(Player p) {
        DataLoader.get().addDefault("players." + p.getUniqueId() + ".notifs", true);
        DataLoader.saveData();
    }

    public static void toggleNotifs(Player p) {
        DataLoader.saveDefaultdata();
        DataLoader.get().options().copyDefaults(true);
        if(DataLoader.get().getBoolean("players." + p.getUniqueId() + ".notifs")) {
            DataLoader.get().set("players." + p.getUniqueId() + ".notifs", false);
            p.sendMessage(ChatColor.YELLOW + "You will no longer recieve auditory notifications!");
            DataLoader.saveData();
        }else{
            DataLoader.get().set("players." + p.getUniqueId() + ".notifs", true);
            p.sendMessage(ChatColor.YELLOW + "You will now recieve auditory notifications!");
            DataLoader.saveData();
        }
    }

    public static boolean getBoolean(Player p, String path) {
        DataLoader.saveDefaultdata();
        DataLoader.get().options().copyDefaults(true);
        boolean exists = DataLoader.get().isBoolean("players." + p.getUniqueId() + "." + path);
        if(exists) {
            return DataLoader.get().getBoolean("players." + p.getUniqueId() + "." + path);
        }else{
            DataLoader.get().addDefault("players." + p.getUniqueId() + "." + path, true);
            DataLoader.saveData();
            return true;
        }
    }

    public static String getMetaString(Player p, String metadata) {
        List meta = p.getMetadata(metadata);
        FixedMetadataValue metaValue = (FixedMetadataValue) meta.get(0);
        return metaValue.asString();
    }

    public static boolean getMetaBoolean(Player p, String metadata) {
        List meta = p.getMetadata(metadata);
        if(meta.size() == 0) {
            return false;
        }
        FixedMetadataValue metaValue = (FixedMetadataValue) meta.get(0);
        return metaValue.asBoolean();
    }
}
