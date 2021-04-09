package dev.rudrecciah.admincore.appeal.data;

import dev.rudrecciah.admincore.errors.ErrorHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class AppealDataLoader {
    private static File file;
    private static File dir;
    private static FileConfiguration customFile;

    public static boolean saveDefaultAppealData(String id) {
        dir = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "ad");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        file = new File(dir, id + ".yml");
        if(!file.exists()) {
            try{
                file.createNewFile();
            }catch (IOException e){
                ErrorHandler.onError(7);
            }
            customFile = YamlConfiguration.loadConfiguration(file);
            return false;
        }
        return true;
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void saveAppealData() {
        try{
            customFile.save(file);
        }catch (IOException e){
            ErrorHandler.onError(8);
        }
    }

    public static void deleteAppealData() {
        try {
            file.delete();
        }catch(Exception e) {
            ErrorHandler.onError(9);
        }
    }
}
