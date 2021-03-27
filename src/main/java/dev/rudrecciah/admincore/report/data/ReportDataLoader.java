package dev.rudrecciah.admincore.report.data;

import dev.rudrecciah.admincore.errors.ErrorHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class ReportDataLoader {
    private static File file;
    private static File dir;
    private static FileConfiguration customFile;

    public static void saveDefaultReportData(UUID uuid){
        dir = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "rd");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        file = new File(dir, uuid + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                ErrorHandler.onError(5);
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static boolean checkForReportData(UUID uuid) {
        dir = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "rd");
        if(!dir.exists()) {
            return false;
        }
        file = new File(dir, uuid + ".yml");
        return file.exists();
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void saveReportData(){
        try{
            customFile.save(file);
        }catch (IOException e){
            ErrorHandler.onError(6);
        }
    }
}
