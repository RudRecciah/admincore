package dev.rudrecciah.admincore.staffmode.grabber;

import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

import static dev.rudrecciah.admincore.Main.plugin;

public class StatsGrabber {

    public static List<String> grabStats(Player p) {
        List<String> test = null;
        return test;
    }

    public static List<String> grabAliases(Player p) {
        ArrayList<String> aliases = new ArrayList<String>();
        aliases.add(ChatColor.YELLOW + "UUID: " + p.getUniqueId());
        aliases.add("");
        aliases.add(ChatColor.YELLOW + "Aliases:");
        for(OfflinePlayer player:plugin.getServer().getOfflinePlayers()) {
            if(String.valueOf(p.getAddress().getHostString()).equalsIgnoreCase(PlayerDataHandler.getIP(player.getUniqueId()))) {
                aliases.add(ChatColor.YELLOW + player.getName());
            }
        }
        return aliases;
    }

}
