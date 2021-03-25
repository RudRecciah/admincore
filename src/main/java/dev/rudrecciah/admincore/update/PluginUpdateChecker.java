package dev.rudrecciah.admincore.update;

import dev.rudrecciah.admincore.errors.SilentErrorHandler;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static dev.rudrecciah.admincore.Main.plugin;

public class PluginUpdateChecker {
    public static void checkForUpdates() {
        //gets and saves version from origin
        try {
            ReadableByteChannel readChannel = Channels.newChannel(new URL("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/PLUGIN_VERSION").openStream());
            File dir = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "ver");
            File file = new File(dir, "ORIGIN_PLUGIN_VERSION");
            if(!dir.exists()) {
                dir.mkdirs();
            }
            if(!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOS = new FileOutputStream(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "ver" + File.separator + "ORIGIN_PLUGIN_VERSION");
            FileChannel writeChannel = fileOS.getChannel();
            writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
        } catch(IOException e) {
            SilentErrorHandler.onSilentError(e);
        }
        //creates and saves local version
        File dir = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "ver");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "LOCAL_PLUGIN_VERSION");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                SilentErrorHandler.onSilentError(e);
            }
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(plugin.getDescription().getVersion());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            SilentErrorHandler.onSilentError(e);
        }
        //gets origin version
        Path mainPth = Paths.get(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "ver" + File.separator + "ORIGIN_PLUGIN_VERSION");
        byte[] mBytes = null;
        try {
            mBytes = Files.readAllBytes(mainPth);
        } catch (IOException e) {
            SilentErrorHandler.onSilentError(e);
        }
        //gets local version
        String main = new String(mBytes, StandardCharsets.UTF_8);
        Path localPth = Paths.get(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "ver" + File.separator + "LOCAL_PLUGIN_VERSION");
        byte[] lBytes = null;
        try {
            lBytes = Files.readAllBytes(localPth);
        } catch (IOException e) {
            SilentErrorHandler.onSilentError(e);
        }
        String local = new String(lBytes, StandardCharsets.UTF_8);
        //compares origin with local
        if(!main.equalsIgnoreCase(local)) {
            File override = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "ver", "OVERRIDE");
            main = main.replaceAll("\\n", "");
            plugin.getLogger().info("Admincore has a new version which you can update to! (" + main + ")");
            if(override.exists()) {
                plugin.getLogger().info("Ignore the version update message above.");
            }
        }
    }
}
