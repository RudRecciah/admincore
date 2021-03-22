package dev.rudrecciah.admincore.staffmode.grabber;

import dev.rudrecciah.admincore.playerdata.PlayerDataHandler;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import static dev.rudrecciah.admincore.Main.plugin;

public class StatsGrabber {

    public static List<String> grabStats(Player p, Player t) {
        String key = plugin.getConfig().getString("staffmode.stats.api-key");
        boolean pub = plugin.getConfig().getBoolean("staffmode.stats.public-networks");
        boolean strict = plugin.getConfig().getBoolean("staffmode.stats.strict");
        StringBuilder path = new StringBuilder();
        path.append(key + "/" + t.getAddress().getHostString()).append("?");
        if(strict) {
            path.append("strictness=1");
        }else{
            path.append("strictness=0");
        }
        if(pub) {
            path.append("&allow_public_access_points=true");
        }
        return api(path.toString(), p);
    }

    public static List<String> grabAliases(Player p) {
        ArrayList<String> aliases = new ArrayList<String>();
        aliases.add(ChatColor.YELLOW + "UUID: " + p.getUniqueId());
        aliases.add("");
        aliases.add(ChatColor.YELLOW + "Aliases:");
        for(OfflinePlayer player:plugin.getServer().getOfflinePlayers()) {
            if(String.valueOf(p.getAddress().getHostString()).equalsIgnoreCase(PlayerDataHandler.getIP(player.getUniqueId())) && p.getUniqueId() != player.getUniqueId()) {
                aliases.add(ChatColor.YELLOW + player.getName());
            }
        }
        return aliases;
    }

    private static List<String> api(String path, Player p) {
        try {
            URL url = new URL("https://ipqualityscore.com/api/json/ip/" + path);
            plugin.getLogger().info(String.valueOf(url));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);
                ArrayList<String> lore = new ArrayList<>();
                boolean success = (boolean) data_obj.get("success");
                if(success) {
                    String city = (String) data_obj.get("city");
                    String velocity = (String) data_obj.get("abuse_velocity");
                    String timezone = (String) data_obj.get("timezone");
                    long lat = (long) data_obj.get("latitude");
                    long score = (long) data_obj.get("fraud_score");
                    boolean recent_abuse = (boolean) data_obj.get("recent_abuse");
                    String ip = (String) data_obj.get("host");
                    long lon = (long) data_obj.get("longitude");
                    boolean vpn_active = (boolean) data_obj.get("active_vpn");
                    String conn_type = (String) data_obj.get("connection_type");
                    boolean bot = (boolean) data_obj.get("bot_status");
                    String isp = (String) data_obj.get("ISP");
                    String country = (String) data_obj.get("country_code");
                    boolean proxy = (boolean) data_obj.get("proxy");
                    boolean vpn = (boolean) data_obj.get("vpn");
                    String region = (String) data_obj.get("region");
                    if(p.hasPermission("admincore.trusted")) {
                        lore.add(ChatColor.YELLOW + "City: " + city);
                        lore.add(ChatColor.YELLOW + "Region/State: " + region);
                        lore.add(ChatColor.YELLOW + "Country: " + country);
                        lore.add(ChatColor.YELLOW + "Longitude: " + lon);
                        lore.add(ChatColor.YELLOW + "Latitude: " + lat);
                    }
                    lore.add(ChatColor.YELLOW + "IP/Hostname: " + ip);
                    lore.add(ChatColor.YELLOW + "Timezone: " + timezone);
                    lore.add(ChatColor.YELLOW + "ISP: " + isp);
                    lore.add(ChatColor.YELLOW + "Fraud Score: " + score);
                    lore.add(ChatColor.YELLOW + "Recent Abuse: " + recent_abuse);
                    lore.add(ChatColor.YELLOW + "Abuse Velocity: " + velocity);
                    lore.add(ChatColor.YELLOW + "Is A Proxy: " + proxy);
                    lore.add(ChatColor.YELLOW + "Is A VPN: " + vpn_active);
                    lore.add(ChatColor.YELLOW + "Was A VPN: " + vpn);
                    lore.add(ChatColor.YELLOW + "Was A Bot: " + bot);
                    lore.add(ChatColor.YELLOW + "LAN Type: " + conn_type);
                }else{
                    plugin.getLogger().severe("An error occured while trying to get this player's statistics!");
                    long code = System.currentTimeMillis();
                    plugin.getLogger().severe("Error Code: API-" + code);
                    plugin.getLogger().severe("This is an API error. Message:");
                    plugin.getLogger().severe((String) data_obj.get("message"));
                    lore.add(ChatColor.YELLOW + "An error occoured!");
                    lore.add(ChatColor.YELLOW + "Aleart an admin about this issue and tell them to check the console or an error log via Discord.");
                    lore.add(ChatColor.YELLOW + "Error Code: API-" + code);
                }
                return lore;
            }
        } catch (Exception e) {
            plugin.getLogger().severe("An error occured while trying to get this player's statistics!");
            long code = System.currentTimeMillis();
            plugin.getLogger().severe("Error Code: O-" + code);
            plugin.getLogger().severe("This is most likely an HTTP error. For debuggung purposes, here's the stack trace. If it exists, the HttpResponseCode will tell you what happened when trying to connect to the IPQS API. If you think this is an error with Admincore rather than your usage or implementation of the plugin, please report this at https://rudrecciah.dev/admincore/bugs.");
            e.printStackTrace();
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.YELLOW + "An error occoured!");
            lore.add(ChatColor.YELLOW + "Aleart an admin about this issue and tell them to check the console or an error log via Discord.");
            lore.add(ChatColor.YELLOW + "Error Code: O-" + code);
            return lore;
        }
    }

}
