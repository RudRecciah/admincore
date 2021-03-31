package dev.rudrecciah.admincore.bot;

import dev.rudrecciah.admincore.bot.listener.Listener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

import static dev.rudrecciah.admincore.Main.plugin;

public class Bot {
    private static JDA api;

    public static void enableBot() throws Exception {
        api = JDABuilder.createLight(plugin.getConfig().getString("bot.token"), GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES).addEventListeners(new Listener()).build();
        int action = plugin.getConfig().getInt("bot.presence.action");
        switch(action) {
            case 0:
                api.getPresence().setPresence(Activity.watching(plugin.getConfig().getString("bot.presence.status")), false);
                break;
            case 1:
                api.getPresence().setPresence(Activity.listening(plugin.getConfig().getString("bot.presence.status")), false);
                break;
            case 2:
                api.getPresence().setPresence(Activity.playing(plugin.getConfig().getString("bot.presence.status")), false);
                break;
            case 3:
                api.getPresence().setPresence(Activity.streaming(plugin.getConfig().getString("bot.presence.status"), plugin.getConfig().getString("bot.presence.url")), false);
                break;
        }
    }

    public static void disableBot() {
        api.getPresence().setPresence(OnlineStatus.OFFLINE, false);
    }
}
