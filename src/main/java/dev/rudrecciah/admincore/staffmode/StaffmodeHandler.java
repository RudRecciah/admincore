package dev.rudrecciah.admincore.staffmode;

import dev.rudrecciah.admincore.data.DataHandler;
import dev.rudrecciah.admincore.staffchat.StaffChat;
import dev.rudrecciah.admincore.staffmode.menus.MainMenu;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.List;

import static dev.rudrecciah.admincore.Main.plugin;

public class StaffmodeHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender,  Command command,  String label,  String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasMetadata("staffmode")) {
                List staffmode = p.getMetadata("staffmode");
                FixedMetadataValue staffmodeValue = (FixedMetadataValue) staffmode.get(0);
                if(staffmodeValue.asBoolean()) {
                    update((Player) sender, false);
                }else{
                    update((Player) sender, true);
                }
            }else{
                update((Player) sender, true);
            }
        }else{
            plugin.getLogger().severe("This command can only be executed by a player!");
        }
        return true;
    }


    //enables/disables staffmode
    public void update(Player p, boolean toggle) {
        if(toggle) {
            p.setMetadata("staffmode", new FixedMetadataValue(plugin, true));
            hide();
            if(plugin.getConfig().getBoolean("staffmode.pseudodisconnect.send-message"))  {
                String configMessage = plugin.getConfig().getString("staffmode.pseudodisconnect.message");
                if(!configMessage.isEmpty()) {
                    configMessage = configMessage.replaceAll("%p%", p.getName());
                    configMessage = ChatColor.translateAlternateColorCodes('&', configMessage);
                    for(Player player : plugin.getServer().getOnlinePlayers()) {
                        if(p != player && !player.hasPermission("admincore.staff")) {
                            player.sendMessage(configMessage);
                        }
                    }
                }else{
                    p.sendMessage(ChatColor.YELLOW + "There is no pseudo disconnect message in config.yml, but pseudo disconnect messages are set to send. Please aleart an admin about this issue! For now, you can enter staffmode, but no disconnect message will be sent. This may compromise your secrecy.");
                }
                for(Player player : plugin.getServer().getOnlinePlayers()) {
                    if(p != player && player.hasPermission("admincore.staff")) {
                        player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE]" + ChatColor.YELLOW + "" + p.getName() + " has entered staffmode!");
                        if(DataHandler.getBoolean(player, "notifs")) {
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        }
                    }
                }
            }
            enterStaffmode(p);
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE]" + ChatColor.YELLOW + " You have entered staffmode!");
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
        }else{
            spawn(p);
            p.removeMetadata("staffmode", plugin);
            show(p);
            if(plugin.getConfig().getBoolean("staffmode.pseudojoin.send-message")) {
                String configMessage = plugin.getConfig().getString("staffmode.pseudojoin.message");
                if(!configMessage.isEmpty()) {
                    configMessage = configMessage.replaceAll("%p%", p.getName());
                    configMessage = ChatColor.translateAlternateColorCodes('&', configMessage);
                    for(Player player : plugin.getServer().getOnlinePlayers()) {
                        if(p != player && !player.hasPermission("admincore.staff")) {
                            player.sendMessage(configMessage);
                        }
                    }
                }else{
                    p.sendMessage(ChatColor.YELLOW + "There is no pseudo join message in config.yml, but pseudo join messages are set to send. Please aleart an admin about this issue! For now, you can leave staffmode, but no join message will be sent. This may compromise your secrecy.");
                }
                for(Player player : plugin.getServer().getOnlinePlayers()) {
                    if(p != player && player.hasPermission("admincore.staff")) {
                        player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE]" + ChatColor.YELLOW + "" + p.getName() + " has left staffmode!");
                        if(DataHandler.getBoolean(player, "notifs")) {
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                        }
                    }
                }
            }
            leaveStaffmode(p);
            p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[STAFFMODE]" + ChatColor.YELLOW + " You have left staffmode!");
            if(DataHandler.getBoolean(p, "notifs")) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
            }
        }
    }

    //hides players when staffmode on
    public static void hide() {
        for(Player playerToCheck : plugin.getServer().getOnlinePlayers()) {
            if(playerToCheck.hasMetadata("staffmode")) {
                List staffmode = playerToCheck.getMetadata("staffmode");
                FixedMetadataValue staffmodeValue = (FixedMetadataValue) staffmode.get(0);
                if(staffmodeValue.asBoolean()) {
                    for(Player player : plugin.getServer().getOnlinePlayers()) {
                        if(!player.hasPermission("admincore.staff")) {
                            player.hidePlayer(plugin, playerToCheck);
                        }
                    }
                }
            }
        }
    }

    //shows players when staffmode off
    public void show(Player p) {
        for(Player player : plugin.getServer().getOnlinePlayers()) {
            player.showPlayer(plugin, p);
        }
    }

    //clears players metadata when they leave the game without leaving staffmode
    public static void clear(Player p, PlayerQuitEvent e) {
        if(p.hasMetadata("staffmode")) {
            p.removeMetadata("staffmode", plugin);
            if(p.hasMetadata("preStaffmodeGamemode")) {
                p.removeMetadata("preStaffmodeGamemode", plugin);
            }
            if(p.hasMetadata("staffmodeChecking")) {
                p.removeMetadata("staffmodeChecking", plugin);
            }
            if(plugin.getConfig().getBoolean("staffmode.pseudodisconnect.send-message")) {
                e.setQuitMessage(null);
            }
        }
    }

    //clears players metadata when they join if something went wrong when they left
    public static void clearJoin(Player p) {
        if(p.hasMetadata("staffmode")) {
            p.removeMetadata("staffmode", plugin);
        }
        if(p.hasMetadata("preStaffmodeGamemode")) {
            p.removeMetadata("preStaffmodeGamemode", plugin);
        }
        if(p.hasMetadata("staffmodeChecking")) {
            p.removeMetadata("staffmodeChecking", plugin);
        }
    }

    //sends messages from players in staffmode in staff channel
    public static boolean checkStaffmodeChat(Player p, String message) {
        if(p.hasMetadata("staffmode")) {
            List staffmode = p.getMetadata("staffmode");
            FixedMetadataValue staffmodeValue = (FixedMetadataValue) staffmode.get(0);
            if(staffmodeValue.asBoolean()) {
                String[] args = message.split(" ");
                StaffChat.staffmodeChat(p, args);
                return true;
            }
            return false;
        }
        return false;
    }

    //stores players previous gamemode as metadata when they enter staffmode
    public void enterStaffmode(Player p) {
        if(plugin.getConfig().getBoolean("staffmode.resetgamemode")) {
            p.setMetadata("preStaffmodeGamemode", new FixedMetadataValue(plugin, p.getGameMode()));
        }
        p.setGameMode(GameMode.SPECTATOR);
    }

    //applies the metadata stored in enterStaffmode()
    public void leaveStaffmode(Player p) {
        if(plugin.getConfig().getBoolean("staffmode.resetgamemode") && p.hasMetadata("preStaffmodeGamemode")) {
            List gamemode = p.getMetadata("preStaffmodeGamemode");
            FixedMetadataValue gamemodeValue = (FixedMetadataValue) gamemode.get(0);
            String gamemodeString = gamemodeValue.asString();
            switch(gamemodeString) {
                case "CREATIVE":
                    p.setGameMode(GameMode.CREATIVE);
                    break;
                case "SURVIVAL":
                    p.setGameMode(GameMode.SURVIVAL);
                    break;
                case "ADVENTURE":
                    p.setGameMode(GameMode.ADVENTURE);
                    break;
            }
            p.removeMetadata("preStaffmodeGamemode", plugin);
        }else{
            p.setGameMode(GameMode.ADVENTURE);
            if(p.hasMetadata("preStaffmodeGamemode")) {
                p.removeMetadata("preStaffmodeGamemode", plugin);
            }
        }
    }

    //opens the staffmode menu for the player interacted with
    public static void interactWithPlayer(Player p, PlayerTeleportEvent e) {
        if(e.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE) {
            if(p.hasMetadata("staffmode")) {
                List meta = p.getMetadata("staffmode");
                FixedMetadataValue metaValue = (FixedMetadataValue) meta.get(0);
                if(metaValue.asBoolean()) {
                    if(p.getSpectatorTarget() != null && p.getSpectatorTarget().getType() == EntityType.PLAYER && !p.getSpectatorTarget().hasPermission("staffmode.staff")) {
                        Location loc = e.getFrom();
                        e.setTo(loc);
                        p.setMetadata("staffmodeChecking", new FixedMetadataValue(plugin, p.getSpectatorTarget().getUniqueId()));
                        MainMenu.openMenu(p);
                        p.setSpectatorTarget(null);
                    }else if(p.getSpectatorTarget().hasPermission("staffmode.staff")) {
                        p.sendMessage(ChatColor.YELLOW + "You can't inspect a staff member!");
                    }
                }
            }
        }
    }

    public void spawn(Player p) {
        if(plugin.getConfig().getBoolean("staffmode.tptospawn.teleport")) {
            if(plugin.getConfig().getBoolean("staffmode.tptospawn.use-spawn-cmd")) {
                p.performCommand(plugin.getConfig().getString("staffmode.tptospawn.spawn-cmd"));
            }else{
                Location loc = new Location(plugin.getServer().getWorld(plugin.getConfig().getString("staffmode.tptospawn.location.world")), plugin.getConfig().getInt("staffmode.tptospawn.location.coordinates.x"), plugin.getConfig().getInt("staffmode.tptospawn.location.coordinates.y"), plugin.getConfig().getInt("staffmode.tptospawn.location.coordinates.z"), plugin.getConfig().getInt("staffmode.tptospawn.location.coordinates.yaw"), plugin.getConfig().getInt("staffmode.tptospawn.location.coordinates.pitch"));
                p.teleport(loc);
            }
        }
    }
}
