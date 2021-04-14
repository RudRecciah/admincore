package dev.rudrecciah.admincore.appeal.data;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Appeal {

    public String id;
    public String ip = "unknown";
    public String name = "unknown";
    public String uuid = "unknown";
    public String reason = "unknown";
    public String type = "unknown";
    public String evidence = "unknown";
    public String punishedBefore = "has not";
    public String email = "unknown";
    public String number = "unknown";
    public String discordName = "unknown";
    public String discordId = "unknown";

    public Appeal(YamlConfiguration yaml, String appealId) {
        yaml.options().copyDefaults(true);
        id = appealId;
        if(!yaml.getString("ip").equalsIgnoreCase("�")) {
            ip = yaml.getString("ip");
        }
        if(!yaml.getString("name").equalsIgnoreCase("�")) {
            name = yaml.getString("name");
        }
        if(!yaml.getString("uuid").equalsIgnoreCase("�")) {
            uuid = yaml.getString("uuid");
        }
        if(!yaml.getString("reason").equalsIgnoreCase("�")) {
            reason = yaml.getString("reason");
        }
        if(!yaml.getString("type").equalsIgnoreCase("�")) {
            type = yaml.getString("type");
        }
        if(!yaml.getString("evidence").equalsIgnoreCase("�")) {
            evidence = yaml.getString("evidence");
        }
        if(yaml.getString("punishedBefore").equalsIgnoreCase("�")) {
            punishedBefore = "unknown";
        }else if(!yaml.getString("punishedBefore").equalsIgnoreCase("no")){
            punishedBefore = "has";
        }
        if(!yaml.getString("email").equalsIgnoreCase("�")) {
            email = yaml.getString("email");
        }
        if(!yaml.getString("number").equalsIgnoreCase("�")) {
            number = yaml.getString("number");
        }
        if(!yaml.getString("discordName").equalsIgnoreCase("�")) {
            discordName = yaml.getString("discordName");
        }
        if(!yaml.getString("discordId").equalsIgnoreCase("�")) {
            discordId = yaml.getString("discordId");
        }
    }
}
