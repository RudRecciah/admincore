package dev.rudrecciah.admincore.update;

import org.bukkit.Bukkit;

import java.io.File;
import java.util.HashMap;

import static dev.rudrecciah.admincore.Main.plugin;

public class ConfigUpdateChecker {

    private HashMap<String, Integer> ver = new HashMap<>();

    private ConfigUpdateChecker() {
        ver.put("0.1.0", 0);
        ver.put("0.2.0", 1);
    }

    public static void checkVersion() {
        File override = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "ver", "OVERRIDE");
        if(!override.exists()) {
            ConfigUpdateChecker configUpdateChecker = new ConfigUpdateChecker();
            if(configUpdateChecker.ver.get(plugin.getDescription().getVersion()) != plugin.getConfig().getInt("version")) {
                plugin.getLogger().info("It seems like your config is made for a different version. It cannot be used in this state and may generate errors. Your server has been shut down to prevent a potantially fatal error. You should copy your config file, delete ./Admincore/ in your plugins folder, and start your server again. When it shuts down, set up your config again. Any errors that you've experienced right now shouldn't be worried about, and should only be investigated if the behavior continues. Thank you!");
                plugin.getServer().shutdown();
                return;
            }
            return;
        }
        plugin.getLogger().info("Config version checking overridden.");
    }
}
