package dev.rudrecciah.admincore.freeze;

import dev.rudrecciah.admincore.data.DataHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FreezeChecker {
    public static void playerFrozen(Player p) {
        if(p.hasMetadata("frozen")) {
            boolean b = DataHandler.getMetaBoolean(p, "frozen");
            if(b) {
                p.sendMessage(ChatColor.YELLOW + "You are still frozen! Don't leave again!");
            }
        }
    }
}
