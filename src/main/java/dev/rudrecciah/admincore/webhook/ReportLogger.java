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
import java.util.UUID;

import static dev.rudrecciah.admincore.Main.plugin;

public class ReportLogger {
    public static void logReport(OfflinePlayer t, int reason, Player p, int n) {
        if(plugin.getConfig().getBoolean("webhook.reportLogger.use")) {
            StringBuilder nameBuilder = new StringBuilder();
            StringBuilder iconBuilder = new StringBuilder();
            if(plugin.getConfig().getBoolean("webhook.customName")) {
                nameBuilder.append(plugin.getConfig().getString("webhook.name"));
                iconBuilder.append(plugin.getConfig().getString("webhook.icon"));
            }else{
                nameBuilder.append("Admincore");
                iconBuilder.append("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png");
            }
            StringBuilder e = new StringBuilder();
            char[] ns = String.valueOf(n).toCharArray();
            if(n == 11 || n == 12 || n == 13) {
                e.append("th");
            }else{
                switch(ns[ns.length - 1]) {
                    case '1':
                        e.append("st");
                        break;
                    case '2':
                        e.append("nd");
                        break;
                    case '3':
                        e.append("rd");
                        break;
                    default:
                        e.append("th");
                        break;
                }
            }
            String name = nameBuilder.toString();
            String icon = iconBuilder.toString();
            TemmieWebhook webhook = new TemmieWebhook(plugin.getConfig().getString("webhook.reportLogger.token"));
            DiscordEmbed de = new DiscordEmbed();
            ThumbnailEmbed te = new ThumbnailEmbed();
            te.setUrl("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/report.png");
            te.setHeight(96);
            te.setWidth(96);
            de.setThumbnail(te);
            de.setTitle("Report Opened!");
            de.setDescription("A player has been reported.");
            de.setFields(Arrays.asList(FieldEmbed.builder().name("Player:").value(t.getName()).build(), FieldEmbed.builder().name("UUID:").value(String.valueOf(t.getUniqueId())).build(), FieldEmbed.builder().name("Occurance:").value("This is " + t.getName() + "'s " + n + e.toString() + " report.").build(), FieldEmbed.builder().name("Reason:").value(plugin.getConfig().getString("staffmode.punishment.report.reasons." + (reason + 1))).build(), FieldEmbed.builder().name("Reported By: ").value(p.getName()).build()));
            de.setFooter(FooterEmbed.builder().text("Admincore Report Logger").icon_url("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png").build());
            DiscordMessage dm = new DiscordMessage(name, "", icon);
            dm.getEmbeds().add(de);
            webhook.sendMessage(dm);
            List<Player> players = (List) plugin.getServer().getOnlinePlayers();
            for (Player player : players) {
                if(player.hasPermission("admincore.staff")) {
                    player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFF CHANNEL] " + ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Admincore " + ChatColor.YELLOW + "Report Logger: " + t.getName() + " was just reported!");
                    if(DataHandler.getBoolean(player, "notifs")) {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                    }
                }
            }
        }
    }

    //not going to use for now, but might reimplement later idk we'll see
    public static void logReportClose(UUID uuid, int n) {
        if(plugin.getConfig().getBoolean("webhook.reportLogger.use")) {
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
            StringBuilder e = new StringBuilder();
            char[] ns = String.valueOf(n).toCharArray();
            if(n == 11 || n == 12 || n == 13) {
                e.append("th");
            }else{
                switch(ns[ns.length - 1]) {
                    case '1':
                        e.append("st");
                        break;
                    case '2':
                        e.append("nd");
                        break;
                    case '3':
                        e.append("rd");
                        break;
                    default:
                        e.append("th");
                        break;
                }
            }
            OfflinePlayer t = plugin.getServer().getOfflinePlayer(uuid);
            TemmieWebhook webhook = new TemmieWebhook(plugin.getConfig().getString("webhook.reportLogger.token"));
            DiscordEmbed de = new DiscordEmbed();
            ThumbnailEmbed te = new ThumbnailEmbed();
            te.setUrl("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/report.png");
            te.setHeight(96);
            te.setWidth(96);
            de.setThumbnail(te);
            de.setTitle("Report Closed!");
            de.setDescription("A player's report has been closed.");
            de.setFields(Arrays.asList(FieldEmbed.builder().name("Player:").value((t.getName())).build(), FieldEmbed.builder().name("UUID:").value(t.getUniqueId().toString()).build(), FieldEmbed.builder().name("Report:").value(t.getName() + "'s " + e.toString() + " report.").build()));
            de.setFooter(FooterEmbed.builder().text("Admincore Report Logger").icon_url("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png").build());
            DiscordMessage dm = new DiscordMessage(name, "", icon);
            dm.getEmbeds().add(de);
            webhook.sendMessage(dm);
        }
    }
}
