package dev.rudrecciah.admincore.bot.stats;

import dev.rudrecciah.admincore.staffmode.grabber.StatsGrabber;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

import static dev.rudrecciah.admincore.Main.plugin;

public class Stats {
    public static void handle(String[] contents, MessageChannel channel) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.black);
        embed.setFooter("Admincore Statistic Checker", "https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png");
        embed.setThumbnail("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/stats.png");
        if(contents.length > 1) {
            if(plugin.getServer().getPlayer(contents[1]) != null) {
                embed.setTitle(plugin.getServer().getPlayer(contents[1]).getName() + "'s Statistics");
                embed.setDescription("Check " + plugin.getServer().getPlayer(contents[1]).getName() + "'s statistics to check for fraud and suspicious behavior!");
                MessageEmbed message = StatsGrabber.grabStats(embed, plugin.getServer().getPlayer(contents[1]), channel);
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
        embed.setDescription("Specify a player to check the statistics of! For example, try `/stats Notch`.");
        MessageEmbed message = embed.build();
        channel.sendMessage(message).queue();
    }
}
