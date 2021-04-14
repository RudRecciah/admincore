package dev.rudrecciah.admincore.appeal.cmd;

import dev.rudrecciah.admincore.appeal.data.Appeal;
import dev.rudrecciah.admincore.data.DataHandler;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.File;

import static dev.rudrecciah.admincore.Main.plugin;

public class Rejector {
    public static void reject(Appeal appeal, Player p) {
        if(appeal != null) {
            File file = new File(plugin.getDataFolder() + File.separator + "data" + File.separator + "ad", appeal.id + ".yml");
            if(file.exists()) {
                file.delete();
            }
            return;
        }
        p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE] " + ChatColor.YELLOW + "That appeal either does not exist or was already closed!");
        if(DataHandler.getBoolean(p, "notifs")) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
        }
    }
}
