package dev.rudrecciah.admincore.announcements;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static dev.rudrecciah.admincore.Main.plugin;

public class AnnouncementHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0) {
            StringBuilder message = new StringBuilder();
            for(String arg : args) {
                message.append(arg).append(" ");
            }
            List<Player> players = (List) plugin.getServer().getOnlinePlayers();
            for(Player player : players) {
                player.sendTitle(ChatColor.BLUE + "" + ChatColor.BOLD + plugin.getConfig().getString("announcementtitle"), ChatColor.YELLOW + String.valueOf(message), 0, 80, 20);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
            return true;
        }
        return false;
    }
}
