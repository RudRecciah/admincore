package dev.rudrecciah.admincore.playerdata;

import dev.rudrecciah.admincore.errors.ErrorHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class PlayerDataLoader {
    private static File file;
    private static File dir;
    private static FileConfiguration customFile;

    public static void saveDefaultPlayerData(Player p){
        dir = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "pd");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        file = new File(dir, p.getUniqueId() + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                ErrorHandler.onError(2);
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void saveDefaultPlayerData(UUID uuid){
        dir = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "pd");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        file = new File(dir, uuid + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                ErrorHandler.onError(3);
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getPlayerData(){
        return customFile;
    }

    public static void savePlayerData() {
        try {
            customFile.save(file);
        } catch(IOException e) {
            ErrorHandler.onError(4);
        }
    }

}
