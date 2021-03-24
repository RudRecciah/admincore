package dev.rudrecciah.admincore.webhook;

import com.mrpowergamerbr.temmiewebhook.DiscordEmbed;
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import com.mrpowergamerbr.temmiewebhook.embed.FieldEmbed;
import com.mrpowergamerbr.temmiewebhook.embed.FooterEmbed;
import com.mrpowergamerbr.temmiewebhook.embed.ThumbnailEmbed;
import org.bukkit.BanList;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static dev.rudrecciah.admincore.Main.plugin;

public class BanLogger {
    public static void logBan(Player p) {
        if(plugin.getConfig().getBoolean("webhook.banLogger.use")) {
            StringBuilder nameBuilder = new StringBuilder();
            StringBuilder iconBuilder = new StringBuilder();
            StringBuilder reasonBuilder = new StringBuilder();
            StringBuilder typeBuilder = new StringBuilder();
            StringBuilder lengthBuilder = new StringBuilder();
            StringBuilder sourceBuilder = new StringBuilder();
            if(plugin.getConfig().getBoolean("webhook.customName")) {
                nameBuilder.append(plugin.getConfig().getString("webhook.name"));
                iconBuilder.append(plugin.getConfig().getString("webhook.icon"));
            }else{
                nameBuilder.append("Admincore");
                iconBuilder.append("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png");
            }
            if(plugin.getServer().getBanList(BanList.Type.NAME).isBanned(p.getUniqueId().toString())) {
                typeBuilder.append("Player");
                if(plugin.getServer().getBanList(BanList.Type.NAME).getBanEntry(p.getUniqueId().toString()).getReason() != null) {
                    reasonBuilder.append(plugin.getServer().getBanList(BanList.Type.NAME).getBanEntry(p.getUniqueId().toString()).getReason());
                }else{
                    reasonBuilder.append("None");
                }
                if(plugin.getServer().getBanList(BanList.Type.NAME).getBanEntry(p.getUniqueId().toString()).getExpiration() != null) {
                    lengthBuilder.append("Until ").append(plugin.getServer().getBanList(BanList.Type.NAME).getBanEntry(p.getUniqueId().toString()).getExpiration());
                }else{
                    lengthBuilder.append("Forever");
                }
                if(plugin.getServer().getBanList(BanList.Type.NAME).getBanEntry(p.getUniqueId().toString()).getSource() != null) {
                    sourceBuilder.append(plugin.getServer().getBanList(BanList.Type.NAME).getBanEntry(p.getUniqueId().toString()).getSource());
                }else{
                    sourceBuilder.append("Unknown");
                }
            }else{
                typeBuilder.append("IP");
                if(plugin.getServer().getBanList(BanList.Type.IP).getBanEntry(p.getAddress().getHostString()).getReason() != null) {
                    reasonBuilder.append(plugin.getServer().getBanList(BanList.Type.IP).getBanEntry(p.getAddress().getHostString()).getReason());
                }else{
                    reasonBuilder.append("None");
                }
                if(plugin.getServer().getBanList(BanList.Type.IP).getBanEntry(p.getAddress().getHostString()).getExpiration() != null) {
                    lengthBuilder.append("Until ").append(plugin.getServer().getBanList(BanList.Type.IP).getBanEntry(p.getAddress().getHostString()).getExpiration());
                }else{
                    lengthBuilder.append("Forever");
                }
                if(plugin.getServer().getBanList(BanList.Type.IP).getBanEntry(p.getAddress().getHostString()).getSource() != null) {
                    sourceBuilder.append(plugin.getServer().getBanList(BanList.Type.IP).getBanEntry(p.getAddress().getHostString()).getSource());
                }else{
                    sourceBuilder.append("Unknown");
                }
            }
            String type = typeBuilder.toString();
            String reason = reasonBuilder.toString();
            String length = lengthBuilder.toString();
            String source = sourceBuilder.toString();
            String name = nameBuilder.toString();
            String icon = iconBuilder.toString();
            TemmieWebhook webhook = new TemmieWebhook(plugin.getConfig().getString("webhook.banLogger.token"));
            DiscordEmbed de = new DiscordEmbed();
            ThumbnailEmbed te = new ThumbnailEmbed();
            te.setUrl("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/ban.png");
            te.setHeight(96);
            te.setWidth(96);
            de.setThumbnail(te);
            de.setTitle("New Ban!");
            de.setDescription("A player has been banned.");
            de.setFields(Arrays.asList(FieldEmbed.builder().name("Type:").value(type).build(), FieldEmbed.builder().name("IP:").value(p.getAddress().getHostString()).build(), FieldEmbed.builder().name("Reason:").value(reason).build(), FieldEmbed.builder().name("Length:").value(length).build(), FieldEmbed.builder().name("Banned By:").value(source).build()));
            de.setFooter(FooterEmbed.builder().text("Admincore Ban Logger").icon_url("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png").build());
            DiscordMessage dm = new DiscordMessage(name, "", icon);
            dm.getEmbeds().add(de);
            webhook.sendMessage(dm);
        }
    }
}
