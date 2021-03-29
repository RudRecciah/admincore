package dev.rudrecciah.admincore.bot.listener;

import dev.rudrecciah.admincore.bot.aliases.Aliases;
import dev.rudrecciah.admincore.bot.announce.Announce;
import dev.rudrecciah.admincore.bot.freeze.Freeze;
import dev.rudrecciah.admincore.bot.history.History;
import dev.rudrecciah.admincore.bot.stats.Stats;
import dev.rudrecciah.admincore.bot.status.Status;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e)
    {
        if(e.getAuthor().isBot()) return;
        Message message = e.getMessage();
        if(message.getContentRaw().contains("/")) {
            String[] content = message.getContentRaw().split(" ");
            MessageChannel channel = e.getChannel();
            switch(content[0]) {
                //TODO: commands
                case "/alias":
                    Aliases.handle(content, channel);
                    break;
                case "/announce":
                    Announce.handle(content, channel);
                    break;
                case "/freeze":
                    Freeze.handle(content, channel);
                    break;
                case "/history":
                    History.handle(content, channel);
                    break;
                case "/stats":
                    Stats.handle(content, channel);
                    break;
                case "/status":
                    Status.handle(content, channel);
                    break;
            }
        }
    }
}
