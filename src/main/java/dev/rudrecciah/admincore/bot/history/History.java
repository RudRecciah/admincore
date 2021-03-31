package dev.rudrecciah.admincore.bot.history;

import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.awt.*;

import static dev.rudrecciah.admincore.Main.plugin;

public class History {
    public static void handle(String[] contents, MessageChannel channel) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.black);
        embed.setFooter("Admincore Punishment History Checker", "https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png");
        embed.setThumbnail("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/history.png");
        if(contents.length > 1) {
            OfflinePlayer target = plugin.getServer().getOfflinePlayer(contents[1]);
            int bans = PlayerDataHandler.getBans(target);
            int mutes = PlayerDataHandler.getMutes(target);
            embed.setTitle(target.getName() + "'s Punishment History");
            embed.setDescription("Check " + target.getName() + "'s punishemnt history for occourences of fraud!");
            embed.addField("Bans:", String.valueOf(bans), false);
            embed.addField("Mutes:", String.valueOf(mutes), false);
            MessageEmbed message = embed.build();
            channel.sendMessage(message).queue();
            return;
        }
        embed.setTitle("Player Not Specified!");
        embed.setDescription("Specify a player to check the punishment history of! For example, try `/history Notch`.");
        MessageEmbed message = embed.build();
        channel.sendMessage(message).queue();
    }
}
