package dev.rudrecciah.admincore.report.data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class ReportDataLoader {
    private static File file;
    private static FileConfiguration customFile;

    public static void saveDefaultReportData(UUID uuid){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder()  + File.separator + "rd", uuid + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                //nothing to see here
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void saveReportData(){
        try{
            customFile.save(file);
        }catch (IOException e){
            plugin.getLogger().severe("Couldn't save changes to reports.yml!");
        }
    }
}
