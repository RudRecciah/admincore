package dev.rudrecciah.admincore.update;

import org.bukkit.Bukkit;

import java.io.File;
import java.lang.reflect.Array;
import java.util.HashMap;

import static dev.rudrecciah.admincore.Main.plugin;

public class ConfigUpdateChecker {

    private HashMap<String, Object[]> ver = new HashMap<>();

    private ConfigUpdateChecker() {
        ver.put("0.1.0", new Object[]{0, true});
        ver.put("0.2.0", new Object[]{1, true});
        ver.put("0.3.0", new Object[]{2, true});
    }

    public static void checkVersion() {
        File override = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "ver", "OVERRIDE");
        if(!override.exists()) {
            ConfigUpdateChecker configUpdateChecker = new ConfigUpdateChecker();
            if(configUpdateChecker.ver.get(plugin.getDescription().getVersion())[0] != (Integer) plugin.getConfig().getInt("version")) {
                if((Boolean) configUpdateChecker.ver.get(plugin.getDescription().getVersion())[1]) {
                    plugin.saveDefaultConfig();
                    plugin.getConfig().set("version", configUpdateChecker.ver.get(plugin.getDescription().getVersion())[0]);
                    plugin.saveConfig();
                    plugin.getLogger().severe("It seems like your config is made for a different version. Admincore cannot run with your current config and said config must be updated. Your server has been stopped to prevent fatal errors. To update, you can either delete your current config and run your server again, or you can check https://rudrecciah.dev/admincore/wiki/updating. Admincore's current version is " + plugin.getDescription().getVersion() + ".");
                    plugin.saveConfig();
                    plugin.getServer().shutdown();
                }
                plugin.getLogger().info("It seems like your config is made for a different version. Admincore will still run fine without a config update, but updating is recommended. To update, you can either delete your current config and run your server again, or you can check https://rudrecciah.dev/admincore/wiki/updating. Admincore's current version is " + plugin.getDescription().getVersion() + ".");
                return;
            }
            return;
        }
        plugin.getLogger().info("Config version checking overridden.");
    }
}
