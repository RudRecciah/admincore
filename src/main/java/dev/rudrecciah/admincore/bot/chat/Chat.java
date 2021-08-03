package dev.rudrecciah.admincore.bot.chat;

import dev.rudrecciah.admincore.data.DataHandler;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

import static dev.rudrecciah.admincore.Main.plugin;

public class Chat {
    public static void handle(String[] contents, MessageChannel channel, User user) {
        StringBuilder content = new StringBuilder();
        for(String str : contents) {
            content.append(str).append(" ");
        }
        plugin.getLogger().info("[Staff Chat Channel] (Discord) " + user.getAsTag() + ": " + content.toString());
        List<Player> players = (List) plugin.getServer().getOnlinePlayers();
        for (Player player : players) {
            if (player.hasPermission("admincore.staff")) {
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Discord " + ChatColor.YELLOW + user.getAsTag() + ": " + content.toString());
                if(DataHandler.getBoolean(player, "notifs")) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
            }
        }
    }
}
