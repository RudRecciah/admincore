package dev.rudrecciah.admincore.bot.help;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Help {
    public static void handle(String[] contents, MessageChannel channel) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.black);
        embed.setTitle("Need Help?");
        embed.setDescription("Find all you need to know about Admincore at https://rudrecciah.dev/admincore including guides, documentation, and more!");
        embed.setThumbnail("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/help.png");
        embed.setFooter("Admincore Bot Helper", "https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png");
        MessageEmbed message = embed.build();
        channel.sendMessage(message).queue();
    }
}
