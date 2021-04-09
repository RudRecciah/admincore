package dev.rudrecciah.admincore.webhook;

import com.mrpowergamerbr.temmiewebhook.DiscordEmbed;
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import com.mrpowergamerbr.temmiewebhook.embed.FieldEmbed;
import com.mrpowergamerbr.temmiewebhook.embed.FooterEmbed;
import com.mrpowergamerbr.temmiewebhook.embed.ThumbnailEmbed;

import java.util.Arrays;

import static dev.rudrecciah.admincore.Main.plugin;

public class ErrorLogger {
    public static void logError(String code, String type) {
        StringBuilder typeBuilder = new StringBuilder();
        StringBuilder notesBuilder = new StringBuilder();
        switch(type) {
            case "API":
                typeBuilder.append("IPQS API Error");
                notesBuilder.append("This is an IPQS API error. This error lies with grabbing statistics, and means that the IPQS API returned a code other than \"200\" when trying to grab a player's statistics. It's most likely an error with your IPQS acocunt or plan (you probably reached your statistic grabbing limit for the month). Check your console logs using the error code below for more details.");
                break;
            case "HTTP":
                typeBuilder.append("IPQS HTTP/Miscellaneous Error");
                notesBuilder.append("This is an IPQS HTTP/Miscellaneous error. This error lies with grabbing statistics, and is most likely an error with the API endpoint (url) you're using to grab statistics. The url is generated from the settings in config.yml under the \"stats\" section of the config. Check to make sure those settings are correct, and if so, check your console logs using the error code below for more details. If everything is correct, it's probably an error with Admincore, and should be reported to https://rudrecciah.dev/admincore/bugs.");
                break;
            case "DATA_CREATE":
                typeBuilder.append("Data.yml Creation Error");
                notesBuilder.append("This is a plugin data creation error. This error occoured while trying to create data.yml, which saves the notification settings of your staff members. This error can be fatal, and should be checked immediately. Check your console logs using the error code below for more details. If you find this to be an error with Admincore rather than your usage or implementation of the plugin, report this bug to https://rudrecciah.dev/admincore/bugs.");
                break;
            case "DATA_SAVE":
                typeBuilder.append("Data.yml Saving Error");
                notesBuilder.append("This is a plugin data saving error. This error occoured while trying to save data.yml, which saves the notification settings of your staff members. This error can be fatal, and should be checked immediately. Check your console logs using the error code below for more details. If you find this to be an error with Admincore rather than your usage or implementation of the plugin, report this bug to https://rudrecciah.dev/admincore/bugs.");
                break;
            case "PLAYERDATA_CREATE_WITH_NAME":
                typeBuilder.append("Name-Based Playerdata File Creation Error");
                notesBuilder.append("This is a playerdata creation error. This error occoured while trying to create a playerdata file for one of your players using the player's name. Playerdata files save important information about your players that's used to check for aliases, track punishments, and mute players. This error can be fatal, and should be checked immediately. Check your console logs using the error code below for more details. If you find this to be an error with Admincore rather than your usage or implementation of the plugin, report this bug to https://rudrecciah.dev/admincore/bugs.");
                break;
            case "PLAYERDATA_CREATE_WITH_UUID":
                notesBuilder.append("This is a playerdata creation error. This error occoured while trying to create a playerdata file for one of your players using the player's UUID. Playerdata files save important information about your players that's used to check for aliases, track punishments, and mute players. This error can be fatal, and should be checked immediately. Check your console logs using the error code below for more details. If you find this to be an error with Admincore rather than your usage or implementation of the plugin, report this bug to https://rudrecciah.dev/admincore/bugs.");
                typeBuilder.append("UUID-Based Playerdata File Creation Error");
                break;
            case "PLAYERDATA_SAVE":
                notesBuilder.append("This is a playerdata saving error. This error occoured while trying to save a playerdata file for one of your players. Playerdata files save important information about your players that's used to check for aliases, track punishments, and mute players. This error can be fatal, and should be checked immediately. Check your console logs using the error code below for more details. If you find this to be an error with Admincore rather than your usage or implementation of the plugin, report this bug to https://rudrecciah.dev/admincore/bugs.");
                typeBuilder.append("Playerdata File Saving Error");
                break;
            case "REPORT_DATA_CREATE":
                notesBuilder.append("This is a report data creation error. This error occoured while trying to create a report data file for one of your players. Report data files store important information about reports of players. This error can be fatal, and should be checked immediately. Check your console logs using the error code below for more details. If you find this to be an error with Admincore rather than your usage or implementation of the plugin, report this bug to https://rudrecciah.dev/admincore/bugs.");
                typeBuilder.append("Report-Based Playerdata Creation Error");
                break;
            case "REPORT_DATA_SAVE":
                notesBuilder.append("This is a report data saving error. This error occoured while trying to save a report data file for one of your players. Report data files store important information about reports of players. This error can be fatal, and should be checked immediately. Check your console logs using the error code below for more details. If you find this to be an error with Admincore rather than your usage or implementation of the plugin, report this bug to https://rudrecciah.dev/admincore/bugs.");
                typeBuilder.append("Report-Based Playerdata Saving Error");
                break;
            case "APPEAL_DATA_CREATE":
                notesBuilder.append("This is an appeal data creation error. This error occoured while trying to create an appeal data file for a new appeal. Appeal files store important information about punishment appeals. This error can be fatal, and should be checked immediately. Check your console logs using the error code below for more details. If you find this to be an error with Admincore rather than your usage or implementation of the plugin, report this bug to https://rudrecciah.dev/admincore/bugs.");
                typeBuilder.append("Appeal-Based Playerdata Creation Error");
                break;
            case "APPEAL_DATA_SAVE":
                notesBuilder.append("This is an appeal data saving error. This error occoured while trying to save an appeal data file for a new appeal. Appeal files store important information about punishment appeals. This error can be fatal, and should be checked immediately. Check your console logs using the error code below for more details. If you find this to be an error with Admincore rather than your usage or implementation of the plugin, report this bug to https://rudrecciah.dev/admincore/bugs.");
                typeBuilder.append("Appeal-Based Playerdata Saving Error");
                break;
            case "APPEAL_DATA_DELETE":
                notesBuilder.append("This is an appeal data deletion error. This error occoured while trying to delete an appeal data file for a closed appeal. Appeal files store important information about punishment appeals. This error can be fatal, and should be checked immediately. Check your console logs using the error code below for more details. If you find this to be an error with Admincore rather than your usage or implementation of the plugin, report this bug to https://rudrecciah.dev/admincore/bugs.");
                typeBuilder.append("Appeal-Based Playerdata Deletion Error");
                break;
            default:
                typeBuilder.append("Unknown Error");
                notesBuilder.append("This is an unknown error. All we know about this error is that it came from Admincore. Check your console logs using the error code below to determine the best actions to recover from this error. We recommend restarting your server, but that might not be the best option. If this behavoir continues, report this bug to https://rudrecciah.dev/admincore/bugs.");
                break;
        }
        String notes = notesBuilder.toString();
        String finalType = typeBuilder.toString();
        if(plugin.getConfig().getBoolean("webhook.errorLogger.use")) {
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
            TemmieWebhook webhook = new TemmieWebhook(plugin.getConfig().getString("webhook.errorLogger.token"));
            DiscordEmbed de = new DiscordEmbed();
            ThumbnailEmbed te = new ThumbnailEmbed();
            te.setUrl("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/bug.png");
            te.setHeight(96);
            te.setWidth(96);
            de.setThumbnail(te);
            de.setTitle("An Error Occoured!");
            de.setDescription(notes);
            de.setFields(Arrays.asList(FieldEmbed.builder().name("Error Type:").value(finalType).build(), FieldEmbed.builder().name("Error Code:").value(code).build(), FieldEmbed.builder().name("Source:").value("Admincore").build()));
            de.setFooter(FooterEmbed.builder().text("Admincore Error Logger").icon_url("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png").build());
            DiscordMessage dm = new DiscordMessage(name, "", icon);
            dm.getEmbeds().add(de);
            webhook.sendMessage(dm);
        }
    }

    public static void logWarn(String warn) {
        if(plugin.getConfig().getBoolean("webhook.errorLogger.use") && plugin.getConfig().getBoolean("webhook.errorLogger.reload")) {
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
            TemmieWebhook webhook = new TemmieWebhook(plugin.getConfig().getString("webhook.errorLogger.token"));
            DiscordEmbed de = new DiscordEmbed();
            ThumbnailEmbed te = new ThumbnailEmbed();
            te.setUrl("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/warn.png");
            te.setHeight(96);
            te.setWidth(96);
            de.setThumbnail(te);
            de.setTitle("A Warning Occoured!");
            de.setDescription(warn);
            de.setFooter(FooterEmbed.builder().text("Admincore Warning Logger").icon_url("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png").build());
            DiscordMessage dm = new DiscordMessage(name, "", icon);
            dm.getEmbeds().add(de);
            webhook.sendMessage(dm);
        }
    }

    public static void logError(String code, Thread t) {
        if(plugin.getConfig().getBoolean("webhook.errorLogger.use")) {
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
            TemmieWebhook webhook = new TemmieWebhook(plugin.getConfig().getString("webhook.errorLogger.token"));
            DiscordEmbed de = new DiscordEmbed();
            ThumbnailEmbed te = new ThumbnailEmbed();
            te.setUrl("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/bug.png");
            te.setHeight(96);
            te.setWidth(96);
            de.setThumbnail(te);
            de.setTitle("An Error Occoured!");
            de.setDescription("This is an exception. The nature of this error is unknown. It's most likely an error with Admincore, but we're not sure. Check your console logs using the error code below for more details. If you find this to be an error with Admincore, report this bug to https://rudrecciah.dev/admincore/bugs. If you find this error to be an error with something else, check that thing's information/documentation to check what it recommends doing in response to this error.");
            de.setFields(Arrays.asList(FieldEmbed.builder().name("Error Type:").value("Exception").build(), FieldEmbed.builder().name("Error Code:").value(code).build(), FieldEmbed.builder().name("Source:").value("Unknown").build(), FieldEmbed.builder().name("Thread").value(t.getName()).build()));
            de.setFooter(FooterEmbed.builder().text("Admincore Error Logger").icon_url("https://raw.githubusercontent.com/RudRecciah/Admin-Core/main/icons/logo.png").build());
            DiscordMessage dm = new DiscordMessage(name, "", icon);
            dm.getEmbeds().add(de);
            webhook.sendMessage(dm);
        }
    }
}
