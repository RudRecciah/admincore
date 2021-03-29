package dev.rudrecciah.admincore.bot;

import dev.rudrecciah.admincore.bot.listener.Listener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

import static dev.rudrecciah.admincore.Main.plugin;

public class Bot {
    private static JDA api;

    public static void enableBot() throws LoginException {
        api = JDABuilder.createLight(plugin.getConfig().getString("bot.token"), GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES).addEventListeners(new Listener()).build();
    }

    public static void disableBot() {
        api.getPresence().setPresence(OnlineStatus.OFFLINE, false);
    }
}
