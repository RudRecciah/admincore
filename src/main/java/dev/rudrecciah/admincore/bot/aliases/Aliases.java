package dev.rudrecciah.admincore.bot.aliases;

import dev.rudrecciah.admincore.staffmode.grabber.StatsGrabber;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

import static dev.rudrecciah.admincore.Main.plugin;

import java.awt.*;
import java.util.List;

public class Aliases {
    public static void handle(String[] contents, MessageChannel channel) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.black);
        embed.setFooter("Admincore Alias Checker", "https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png");
        embed.setThumbnail("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/alias.png");
        if(contents.length > 1) {
            if(plugin.getServer().getPlayer(contents[1]) != null) {
                List<String> aliases = StatsGrabber.grabAliases(plugin.getServer().getPlayer(contents[1]), 1);
                embed.setTitle(plugin.getServer().getPlayer(contents[1]).getName() + "'s Aliases");
                embed.setDescription("Check " + plugin.getServer().getPlayer(contents[1]).getName() + "'s aliases to check for alternate accounts!");
                embed.addField("Aliases:", aliases.toString(), false);
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
        embed.setDescription("Specify a player to check the aliases of! For example, try `/alias Notch`.");
        MessageEmbed message = embed.build();
        channel.sendMessage(message).queue();
    }
}
