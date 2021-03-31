package dev.rudrecciah.admincore.bot.listener;

import dev.rudrecciah.admincore.bot.aliases.Aliases;
import dev.rudrecciah.admincore.bot.announce.Announce;
import dev.rudrecciah.admincore.bot.freeze.Freeze;
import dev.rudrecciah.admincore.bot.freeze.Unfreeze;
import dev.rudrecciah.admincore.bot.help.Help;
import dev.rudrecciah.admincore.bot.history.History;
import dev.rudrecciah.admincore.bot.stats.Stats;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static dev.rudrecciah.admincore.Main.plugin;

public class Listener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if(e.getAuthor().isBot()) return;
        Message message = e.getMessage();
        if(message.getChannel().getId().equalsIgnoreCase(plugin.getConfig().getString("bot.channels.admin")) || message.getChannel().getId().equalsIgnoreCase(plugin.getConfig().getString("bot.channels.trusted")) || message.getChannel().getId().equalsIgnoreCase(plugin.getConfig().getString("bot.channels.staff")) && message.getContentRaw().charAt(0) == plugin.getConfig().getString("bot.prefix").charAt(0)) {
            String[] content = message.getContentRaw().split(" ");
            content[0] = content[0].replaceFirst(plugin.getConfig().getString("bot.prefix"), "");
            MessageChannel channel = e.getChannel();
            switch(content[0]) {
                case "alias":
                    if(plugin.getConfig().getBoolean("bot.commands.aliases")) {
                        Aliases.handle(content, channel);
                    }
                    break;
                case "announce":
                    if(plugin.getConfig().getBoolean("bot.commands.announce") && message.getChannel().getId().equalsIgnoreCase(plugin.getConfig().getString("bot.channels.admin"))) {
                        Announce.handle(content, channel);
                    }
                    break;
                case "freeze":
                    if(plugin.getConfig().getBoolean("bot.commands.freeze")) {
                        Freeze.handle(content, channel);
                    }
                    break;
                case "history":
                    if(plugin.getConfig().getBoolean("bot.commands.history")) {
                        History.handle(content, channel);
                    }
                    break;
                case "stats":
                    if(plugin.getConfig().getBoolean("bot.commands.stats") && message.getChannel().getId().equalsIgnoreCase(plugin.getConfig().getString("bot.channels.trusted"))) {
                        Stats.handle(content, channel);
                    }
                    break;
                case "unfreeze":
                    if(plugin.getConfig().getBoolean("bot.commands.unfreeze")) {
                        Unfreeze.handle(content, channel);
                    }
                    break;
                case "help":
                    Help.handle(content, channel);
                    break;
            }
        }
    }
}
