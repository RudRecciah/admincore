package dev.rudrecciah.admincore.broadcasts;

import dev.rudrecciah.admincore.errors.SilentErrorHandler;
import dev.rudrecciah.admincore.webhook.BroadcastLogger;
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

public class BroadcastHandler {
    public static void handleBroadcast() {
        try {
            ReadableByteChannel readChannel = Channels.newChannel(new URL("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/other/BROADCAST").openStream());
            File dir = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "broadcasts");
            File file = new File(dir, "ORIGIN_BROADCAST");
            if(!dir.exists()) {
                dir.mkdirs();
            }
            if(!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOS = new FileOutputStream(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "broadcasts" + File.separator + "ORIGIN_BROADCAST");
            FileChannel writeChannel = fileOS.getChannel();
            writeChannel.transferFrom(readChannel, 0, Long.MAX_VALUE);
        } catch(IOException e) {
            SilentErrorHandler.onSilentError(e);
        }
        File dir = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "broadcasts");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "LOCAL_BROADCAST");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                SilentErrorHandler.onSilentError(e);
            }
        }
        Path mainPth = Paths.get(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "broadcasts" + File.separator + "ORIGIN_BROADCAST");
        byte[] mBytes = null;
        try {
            mBytes = Files.readAllBytes(mainPth);
        } catch (IOException e) {
            SilentErrorHandler.onSilentError(e);
        }
        String main = new String(mBytes, StandardCharsets.UTF_8);
        Path localPth = Paths.get(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "broadcasts" + File.separator + "LOCAL_BROADCAST");
        byte[] lBytes = null;
        try {
            lBytes = Files.readAllBytes(localPth);
        } catch (IOException e) {
            SilentErrorHandler.onSilentError(e);
        }
        String local = new String(lBytes, StandardCharsets.UTF_8);
        if(!main.equalsIgnoreCase(local)) {
            plugin.getLogger().info(main);
            if(plugin.getConfig().getBoolean("webhook.broadcastLogger.use")) {
                BroadcastLogger.logBroadcast(main);
            }
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(main);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            SilentErrorHandler.onSilentError(e);
        }
    }
}
