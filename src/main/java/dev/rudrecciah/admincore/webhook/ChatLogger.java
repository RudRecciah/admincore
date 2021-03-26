package dev.rudrecciah.admincore.webhook;

import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import org.bukkit.entity.Player;

import static dev.rudrecciah.admincore.Main.plugin;

public class ChatLogger {
    public static void logChat(Player p, String message) {
        if(plugin.getConfig().getBoolean("chatlink.get.enable")) {
            TemmieWebhook webhook = new TemmieWebhook(plugin.getConfig().getString("chatlink.get.token"));
            DiscordMessage dm = new DiscordMessage(p.getName(), message, "https://mc-heads.net/head/" + p.getUniqueId() + "/100");
            webhook.sendMessage(dm);
        }
    }
}
