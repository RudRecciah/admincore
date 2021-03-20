package dev.rudrecciah.admincore.playerdata;

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
    private static FileConfiguration customFile;

    public static void saveDefaultPlayerData(Player p){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder()  + File.separator + "pd", p.getUniqueId() + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                //nothing to see here
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void saveDefaultPlayerData(UUID uuid){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder()  + File.separator + "pd", uuid + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                //nothing to see here
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
            plugin.getLogger().severe("Couldn't save changes to playerdata!");
        }
    }

}
