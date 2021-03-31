package dev.rudrecciah.admincore.bot.freeze;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.awt.*;

import static dev.rudrecciah.admincore.Main.plugin;

public class Unfreeze {
    public static void handle(String[] contents, MessageChannel channel) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.black);
        embed.setFooter("Admincore Unfreezer", "https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png");
        embed.setThumbnail("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/unfreeze.png");
        if(contents.length > 1) {
            if(plugin.getServer().getPlayer(contents[1]) != null) {
                Player t = plugin.getServer().getPlayer(contents[1]);
                t.setMetadata("frozen", new FixedMetadataValue(plugin, false));
                t.sendMessage(ChatColor.YELLOW + "You have been unfrozen! You can now move!");
                embed.setTitle(plugin.getServer().getPlayer(contents[1]).getName() + " has been unfrozen!");
                embed.setDescription("They can now move again!");
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
        embed.setDescription("Specify a player to unfreeze! For example, try `/unfreeze Notch`.");
        MessageEmbed message = embed.build();
        channel.sendMessage(message).queue();
    }
}
