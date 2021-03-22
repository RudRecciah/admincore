package dev.rudrecciah.admincore;

import dev.rudrecciah.admincore.announcements.AnnouncementHandler;
import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.data.DataLoader;
import dev.rudrecciah.admincore.errors.ExceptionHandler;
import dev.rudrecciah.admincore.freeze.FreezeChecker;
import dev.rudrecciah.admincore.freeze.PlayerFreezer;
import dev.rudrecciah.admincore.freeze.PlayerUnfreezer;
import dev.rudrecciah.admincore.master.MasterCommand;
import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import dev.rudrecciah.admincore.report.ReportCommandHandler;
import dev.rudrecciah.admincore.report.meta.ReportMetaCleaner;
import dev.rudrecciah.admincore.serverstatus.ServerStatus;
import dev.rudrecciah.admincore.staffchat.StaffChat;
import dev.rudrecciah.admincore.staffmode.StaffmodeHandler;
import fr.minuskube.inv.InventoryManager;
import jdk.jfr.events.ExceptionThrownEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements CommandExecutor, Listener {

    public static Main plugin;
    private InventoryManager invManager;

    @Override
    public void onEnable() {
        ExceptionHandler handler = new ExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
        plugin = this;
        plugin.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        DataLoader.saveDefaultdata();
        DataLoader.get().options().copyDefaults(true);
        getCommand("staffchat").setExecutor(new StaffChat());
        getCommand("status").setExecutor(new ServerStatus());
        getCommand("announce").setExecutor(new AnnouncementHandler());
        getCommand("staffmode").setExecutor(new StaffmodeHandler());
        getCommand("admincore").setExecutor(new MasterCommand());
        getCommand("freeze").setExecutor(new PlayerFreezer());
        getCommand("unfreeze").setExecutor(new PlayerUnfreezer());
        getCommand("report").setExecutor(new ReportCommandHandler());
        getLogger().info("Admin Core Enabled");
        invManager = new InventoryManager(this);
        invManager.init();
    }

    public InventoryManager getInvManager() {
        return invManager;
    }

    public static void rud() {
        return;
    }

    /*
    * Other things:
    * die
    * be gay
    * do crime
    * pogchamp
    * TODO killwhenoutdated in config
    *
    * Bugs to fix:
    * none yey
    * TODO: discord integration
    * */

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        StaffmodeHandler.hide();
        StaffmodeHandler.clearJoin(e.getPlayer());
        PlayerDataHandler.handlePlayerData(e.getPlayer());
        ReportMetaCleaner.cleanReportMeta(e.getPlayer());
        FreezeChecker.playerFrozen(e.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        StaffmodeHandler.clear(e.getPlayer(), e);
        if(e.getPlayer().isBanned()) {
            PlayerDataHandler.ban(e.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        StaffmodeHandler.interactWithPlayer(e.getPlayer(), e);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if(e.getPlayer().hasPermission("admincore.staff")) {
            boolean b = StaffmodeHandler.checkStaffmodeChat(e.getPlayer(), e.getMessage());
            if(b) {
                e.setCancelled(true);
            }
        }else if(PlayerDataHandler.muteExpired(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.YELLOW + "You're currently muted! Reason: " + getConfig().getString("staffmode.punishment.mute.reason"));
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(e.getPlayer().hasMetadata("frozen")) {
            boolean b = DataHandler.getMetaBoolean(e.getPlayer(), "frozen");
            if(b) {
                e.setCancelled(true);
            }
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Admin Core Disabled");
    }
}
