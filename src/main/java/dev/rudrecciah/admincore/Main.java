package dev.rudrecciah.admincore;

import dev.rudrecciah.admincore.announcements.AnnouncementHandler;
import dev.rudrecciah.admincore.data.DataLoader;
import dev.rudrecciah.admincore.serverstatus.ServerStatus;
import dev.rudrecciah.admincore.staffchat.StaffChat;
import dev.rudrecciah.admincore.staffmode.StaffmodeHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import dev.rudrecciah.admincore.staffmode.StaffmodeHandler;

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
        getCommand("staffmode").setExecutor(new StaffmodeHandler());
        getLogger().info("Admin Core Enabled");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        StaffmodeHandler.hide();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        StaffmodeHandler.clear(e.getPlayer());
    }

    @Override
    public void onDisable() {
        getLogger().info("Admin Core Disabled");
    }
}
