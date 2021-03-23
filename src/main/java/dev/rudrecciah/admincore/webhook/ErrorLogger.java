package dev.rudrecciah.admincore.webhook;

import com.mrpowergamerbr.temmiewebhook.DiscordEmbed;
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import com.mrpowergamerbr.temmiewebhook.embed.ThumbnailEmbed;

public class ErrorLogger {
    public static void logError(String code, String type) {
        switch(type) {
            case "API":
                break;
            case "HTTP":
                break;
            case "DATA_CREATE":
                break;
            case "DATA_SAVE":
                break;
            case "PLAYERDATA_CREATE_WITH_NAME":
                break;
            case "PLAYERDATA_CREATE_WITH_UUID":
                break;
            case "PLAYERDATA_SAVE":
                break;
            case "REPORT_DATA_CREATE":
                break;
            case "REPORT_DATA_SAVE":
                break;
        }
        TemmieWebhook webhook = new TemmieWebhook("Your Webhook URL here (Get it by going to your server configurations -> Webhook");
        DiscordEmbed de = new DiscordEmbed();
        ThumbnailEmbed te = new ThumbnailEmbed();
        te.setUrl("http://vignette3.wikia.nocookie.net/undertale/images/9/9c/Temmie.gif/revision/latest?cb=20151206115948");
        te.setHeight(96);
        te.setWidth(96);
        de.setThumbnail(te);
        DiscordMessage dm = new DiscordMessage("Temmie", "", "https://img04.deviantart.net/360e/i/2015/300/9/d/temmie_by_ilovegir64-d9elpal.png");
        dm.getEmbeds().add(de);
        webhook.sendMessage(dm);
    }

    public static void logError(String code, Thread t, Throwable e) {

    }
}
