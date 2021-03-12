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

public final class Main extends JavaPlugin implements CommandExecutor, Listener {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        plugin.saveDefaultConfig();
        this.getLogger().info("hallo");
        getServer().getPluginManager().registerEvents(this, this);
        DataLoader.saveDefaultdata();
        DataLoader.get().options().copyDefaults(true);
        getCommand("staffchat").setExecutor(new StaffChat());
        getCommand("status").setExecutor(new ServerStatus());
        getCommand("announce").setExecutor(new AnnouncementHandler());
        getCommand("staffmode").setExecutor(new StaffmodeHandler());
        getLogger().info("Admin Core Enabled");
    }

    public static void rud() {
        return;
    }

    /*
    * Things you can do in staffmode:
    * /spectate
    * /invsee
    * fly
    * no hitbox (not spectator mode)
    * Invulnerable
    * Either saturation or nohunger task
    *
    * How it will work:
    * When you enter, you can fly and become invulnerable, you also get added to saturation/food tasks
    * When you punch a player, the hit won't register but you will open a gui with 5 buttons, report player, inventory see, name/uuid etc, full stats (ip, location, etc), and ban
    *
    * Report Player:
    * to be handled by report module
    *
    * Invsee:
    * Opens up a bigger gui with armor, inventory, and hotbar see that changes with the player's changes (might outsource this, idk)
    *
    * Name/uuid stats:
    * Name, UUID
    *
    * Full stats:
    * Opens another gui with name, UUID, IP, all the important stuff from ipqs api, ban history, aliases, anything else i can think of
    *
    * Ban:
    * to be handled by ban module
    * */

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
