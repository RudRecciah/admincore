package dev.rudrecciah.admincore.webhook;

import com.mrpowergamerbr.temmiewebhook.DiscordEmbed;
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import com.mrpowergamerbr.temmiewebhook.embed.FieldEmbed;
import com.mrpowergamerbr.temmiewebhook.embed.FooterEmbed;
import com.mrpowergamerbr.temmiewebhook.embed.ThumbnailEmbed;
import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.punishlogs.PunishmentLogger;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

import static dev.rudrecciah.admincore.Main.plugin;

public class KickLogger {
    public static void logKick(String p, String reason, String t) {
        List<Player> players = (List) plugin.getServer().getOnlinePlayers();
        PunishmentLogger.logKick(p, reason, t);
        for (Player player : players) {
            if (player.hasPermission("admincore.staff")) {
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Admincore " + ChatColor.YELLOW + "Kick Logger: " + t + " was just kicked for " + reason);
                if(DataHandler.getBoolean(player, "notifs")) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1f, 1f);
                }
            }
        }
        if(plugin.getConfig().getBoolean("webhook.kickLogger.use")) {
            StringBuilder nameBuilder = new StringBuilder();
            StringBuilder iconBuilder = new StringBuilder();
            if(plugin.getConfig().getBoolean("webhook.customName")) {
                nameBuilder.append(plugin.getConfig().getString("webhook.name"));
                iconBuilder.append(plugin.getConfig().getString("webhook.icon"));
            }else{
                nameBuilder.append("Admincore");
                iconBuilder.append("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png");
            }
            String name = nameBuilder.toString();
            String icon = iconBuilder.toString();
            TemmieWebhook webhook = new TemmieWebhook(plugin.getConfig().getString("webhook.kickLogger.token"));
            DiscordEmbed de = new DiscordEmbed();
            ThumbnailEmbed te = new ThumbnailEmbed();
            te.setUrl("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/kick.png");
            te.setHeight(96);
            te.setWidth(96);
            de.setThumbnail(te);
            de.setTitle("New Kick!");
            de.setDescription("A player has been muted.");
            de.setFields(Arrays.asList(FieldEmbed.builder().name("Player:").value(t).build(), FieldEmbed.builder().name("Reason:").value(reason).build(), FieldEmbed.builder().name("Kicked By: ").value(p).build()));
            de.setFooter(FooterEmbed.builder().text("Admincore Kick Logger").icon_url("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png").build());
            DiscordMessage dm = new DiscordMessage(name, "", icon);
            dm.getEmbeds().add(de);
            webhook.sendMessage(dm);
        }
    }
}
