package dev.rudrecciah.admincore.bot.announce;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dev.rudrecciah.admincore.Main.plugin;

public class Announce {
    public static void handle(String[] contents, MessageChannel channel) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.black);
        embed.setFooter("Admincore Announcer", "https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png");
        embed.setThumbnail("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/announce.png");
        if(contents.length > 1) {
            StringBuilder contentsList = new StringBuilder();
            for(String content : contents) {
                if(!contents[0].equalsIgnoreCase(content)) {
                    contentsList.append(content + " ");
                }
            }
            java.util.List<Player> players = (List) plugin.getServer().getOnlinePlayers();
            for(Player player : players) {
                player.sendTitle(ChatColor.BLUE + "" + ChatColor.BOLD + plugin.getConfig().getString("announcementtitle"), ChatColor.YELLOW + String.valueOf(contentsList), 0, 80, 20);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1f, 1f);
            }
            embed.setTitle("Announcement Sent!");
            embed.setDescription("Your announcement was sent to all online players!");
            MessageEmbed message = embed.build();
            channel.sendMessage(message).queue();
            return;
        }
        embed.setTitle("Announcement Not Specified!");
        embed.setDescription("You need to specify the announcement you would like to send. For example, try `/announce Admincore is so very Cool and Awesome`.");
        MessageEmbed message = embed.build();
        channel.sendMessage(message).queue();
    }
}
