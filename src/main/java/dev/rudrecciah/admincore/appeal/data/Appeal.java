package dev.rudrecciah.admincore.appeal.data;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Appeal {

    public String id = null;
    public String ip = null;
    public String name = null;
    public String uuid = null;
    public String reason = null;
    public String type = null;
    public String evidence = null;
    public String punishedBefore = null;
    public String email = null;
    public String number = null;
    public String discordName = null;
    public String discordId = null;

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
        if(!yaml.getString("punishedBefore").equalsIgnoreCase("�")) {
            punishedBefore = yaml.getString("punishedBefore");
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
