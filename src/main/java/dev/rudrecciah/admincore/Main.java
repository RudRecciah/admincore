package dev.rudrecciah.admincore;

import dev.rudrecciah.admincore.announcements.AnnouncementHandler;
import dev.rudrecciah.admincore.data.DataLoader;
import dev.rudrecciah.admincore.serverstatus.ServerStatus;
import dev.rudrecciah.admincore.staffchat.StaffChat;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements CommandExecutor, Listener {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        plugin.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        DataLoader.saveDefaultdata();
        DataLoader.get().options().copyDefaults(true);
        getCommand("staffchat").setExecutor(new StaffChat());
        getCommand("status").setExecutor(new ServerStatus());
        getCommand("announce").setExecutor(new AnnouncementHandler());
        getLogger().info("Admin Core Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Admin Core Disabled");
    }
}
