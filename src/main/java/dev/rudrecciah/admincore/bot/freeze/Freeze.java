package dev.rudrecciah.admincore.bot.freeze;

import dev.rudrecciah.admincore.staffmode.grabber.StatsGrabber;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.awt.*;

import static dev.rudrecciah.admincore.Main.plugin;

public class Freeze {
    public static void handle(String[] contents, MessageChannel channel) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.black);
        embed.setFooter("Admincore Freezer", "https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png");
        embed.setThumbnail("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/freeze.png");
        if(contents.length > 1) {
            if(plugin.getServer().getPlayer(contents[1]) != null) {
                Player t = plugin.getServer().getPlayer(contents[1]);
                t.setMetadata("frozen", new FixedMetadataValue(plugin, true));
                t.sendMessage(ChatColor.YELLOW + "You have been frozen! Do not move!");
                embed.setTitle(plugin.getServer().getPlayer(contents[1]).getName() + " has been frozen!");
                embed.setDescription("They can no longer move! Make sure you have a staffmember ready to check the player.");
                MessageEmbed message = embed.build();
                channel.sendMessage(message).queue();
                return;
            }
            embed.setTitle("Player Offline!");
            embed.setDescription("The server doesn't know who the player is, meaning they're most likely offline.");
            MessageEmbed message = embed.build();
            channel.sendMessage(message).queue();
            return;
        }
        embed.setTitle("Player Not Specified!");
        embed.setDescription("Specify a player to freeze! For example, try `/freeze Notch`.");
        MessageEmbed message = embed.build();
        channel.sendMessage(message).queue();
    }
}
