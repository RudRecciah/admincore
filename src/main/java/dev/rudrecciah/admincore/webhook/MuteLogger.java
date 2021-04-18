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
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

import static dev.rudrecciah.admincore.Main.plugin;

public class MuteLogger {
    public static void logMute(Player p, int length, OfflinePlayer t, int reason) {
        List<Player> players = (List) plugin.getServer().getOnlinePlayers();
        PunishmentLogger.logMute(t, plugin.getConfig().getString("staffmode.punishment.mute.reasons." + reason), length, p);
        for (Player player : players) {
            if (player.hasPermission("admincore.staff")) {
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Admincore " + ChatColor.YELLOW + "Mute Logger: " + t.getName() + " was just muted for " + length + " minutes!");
                if(DataHandler.getBoolean(player, "notifs")) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
            }
        }
        if(plugin.getConfig().getBoolean("webhook.muteLogger.use")) {
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
            TemmieWebhook webhook = new TemmieWebhook(plugin.getConfig().getString("webhook.muteLogger.token"));
            DiscordEmbed de = new DiscordEmbed();
            ThumbnailEmbed te = new ThumbnailEmbed();
            te.setUrl("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/mute.png");
            te.setHeight(96);
            te.setWidth(96);
            de.setThumbnail(te);
            de.setTitle("New Mute!");
            de.setDescription("A player has been muted.");
            de.setFields(Arrays.asList(FieldEmbed.builder().name("Player:").value(t.getName()).build(), FieldEmbed.builder().name("Reason:").value(plugin.getConfig().getString("staffmode.punishment.mute.reasons." + reason)).build(), FieldEmbed.builder().name("Length:").value(length + " minutes").build(), FieldEmbed.builder().name("Muted By: ").value(p.getName()).build()));
            de.setFooter(FooterEmbed.builder().text("Admincore Mute Logger").icon_url("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png").build());
            DiscordMessage dm = new DiscordMessage(name, "", icon);
            dm.getEmbeds().add(de);
            webhook.sendMessage(dm);
        }
    }

    public static void logMute(String muter, int length, OfflinePlayer t, int reason) {
        List<Player> players = (List) plugin.getServer().getOnlinePlayers();
        PunishmentLogger.logMute(t, plugin.getConfig().getString("staffmode.punishment.mute.reasons." + reason), length, muter);
        for (Player player : players) {
            if (player.hasPermission("admincore.staff")) {
                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Admincore " + ChatColor.YELLOW + "Mute Logger: " + t.getName() + " was just muted for " + length + " minutes!");
                if(DataHandler.getBoolean(player, "notifs")) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                }
            }
        }
        if(plugin.getConfig().getBoolean("webhook.muteLogger.use")) {
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
            TemmieWebhook webhook = new TemmieWebhook(plugin.getConfig().getString("webhook.muteLogger.token"));
            DiscordEmbed de = new DiscordEmbed();
            ThumbnailEmbed te = new ThumbnailEmbed();
            te.setUrl("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/mute.png");
            te.setHeight(96);
            te.setWidth(96);
            de.setThumbnail(te);
            de.setTitle("New Mute!");
            de.setDescription("A player has been muted.");
            de.setFields(Arrays.asList(FieldEmbed.builder().name("Player:").value(t.getName()).build(), FieldEmbed.builder().name("Reason:").value(plugin.getConfig().getString("staffmode.punishment.mute.reasons." + reason)).build(), FieldEmbed.builder().name("Length:").value(length + " minutes").build(), FieldEmbed.builder().name("Muted By: ").value(muter).build()));
            de.setFooter(FooterEmbed.builder().text("Admincore Mute Logger").icon_url("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png").build());
            DiscordMessage dm = new DiscordMessage(name, "", icon);
            dm.getEmbeds().add(de);
            webhook.sendMessage(dm);
        }
    }
}
