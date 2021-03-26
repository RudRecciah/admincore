package dev.rudrecciah.admincore.errors;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SilentErrorHandler {
    public static void onSilentError(Exception e, int i) {
        File dir = new File(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "log");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, "SILENT_ERRORS.acl");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                if(i >= 4) {
                    return;
                }
                SilentErrorHandler.onSilentError(e, i + 1);
            }
        }
        writeError(e, file);
    }

    public static void writeError(Exception e, File file) {
        FileWriter writer;
        StringBuilder errBuilder = new StringBuilder();
        errBuilder.append("-EPOCCH ").append(System.currentTimeMillis()).append("-").append(System.lineSeparator()).append("*ERR*").append(System.lineSeparator()).append("CODE: SILENT-").append(System.currentTimeMillis()).append(System.lineSeparator()).append("MESSAGE: ").append(e.getMessage()).append(System.lineSeparator()).append(e.getStackTrace()).append(System.lineSeparator()).append(e.getCause());
        try {
            StringBuilder logs = new StringBuilder();
            logs.append(new String(Files.readAllBytes(Paths.get(Bukkit.getServer().getPluginManager().getPlugin("Admincore").getDataFolder() + File.separator + "data" + File.separator + "sd" + File.separator + "log" + File.separator + "SILENT_ERRORS.acl")), StandardCharsets.UTF_8)).append(System.lineSeparator()).append("------").append(System.lineSeparator()).append(errBuilder.toString());
            writer = new FileWriter(file);
            writer.write(logs.toString());
            writer.flush();
            writer.close();
        } catch (IOException ignored) {}
    }

    public static void onSilentError(Exception e) {
        onSilentError(e, 0);
    }
}
