package dev.rudrecciah.admincore.data;

import com.sun.tools.sjavac.Log;
import dev.rudrecciah.admincore.errors.ErrorHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static dev.rudrecciah.admincore.Main.plugin;

public class DataLoader {
    private static File file;
    private static FileConfiguration customFile;

    public static void saveDefaultdata(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "sd", "data.yml");

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                ErrorHandler.onError(0);
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void saveData(){
        try{
            customFile.save(file);
        }catch (IOException e){
            ErrorHandler.onError(1);
        }
    }
}
