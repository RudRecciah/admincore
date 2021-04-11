package dev.rudrecciah.admincore.appeal.http;

import com.sun.imageio.plugins.tiff.TIFFOldJPEGDecompressor;
import dev.rudrecciah.admincore.appeal.data.AppealDataHandler;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;

import static dev.rudrecciah.admincore.Main.plugin;

public class AppealHttpHandler implements Runnable {
    private final Undertow server = Undertow.builder().addHttpListener(plugin.getConfig().getInt("staffmode.punishment.appeals.api.port"), plugin.getConfig().getString("staffmode.punishment.appeals.api.hostname")).setHandler(new HttpHandler() {
        @Override
        public void handleRequest(final HttpServerExchange exchange) throws Exception {
            int code = handle(exchange);
            exchange.setStatusCode(code);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
            if(code == 201) {
                exchange.getResponseSender().send("SUCCESS");
            }else{
                exchange.getResponseSender().send("FAILURE");
            }
        }
    }).build();

    /*
    * HEADERS:
    *
    * id:
    * name - string
    * uuid - string
    *
    * type of punishment:
    * mute, ban (both temp and perm), ipban - string
    *
    * why to unpunish:
    * appeal text - string
    *
    * evidence:
    * appeal evidence - string
    *
    * questions:
    * have you been punished before - string
    *
    * contact:
    * email - string
    * phone number - string
    * discord name/tag - string
    * discord id - string
    * */

    @Override
    public void run() {
        server.start();
        plugin.getServer().getLogger().info("[Admincore] Admincore HTTP Server Enabled");
    }

    private int handle(HttpServerExchange e) {
        String ip = e.getSourceAddress().getHostString();
        HashMap<String, Object> map = new HashMap<>();
        try {
            map.put("ip", ip);
            map.put("id", e.getRequestHeaders().get("id").toString().substring(2, e.getRequestHeaders().get("id").toString().length() - 2));
            map.put("name", e.getRequestHeaders().get("name").toString().substring(2, e.getRequestHeaders().get("name").toString().length() - 2));
            map.put("uuid", e.getRequestHeaders().get("uuid").toString().substring(2, e.getRequestHeaders().get("uuid").toString().length() - 2));
            map.put("type", e.getRequestHeaders().get("type").toString().substring(2, e.getRequestHeaders().get("type").toString().length() - 2));
            map.put("reason", e.getRequestHeaders().get("reason").toString().substring(2, e.getRequestHeaders().get("reason").toString().length() - 2));
            map.put("evidence", e.getRequestHeaders().get("evidence").toString().substring(2, e.getRequestHeaders().get("evidence").toString().length() - 2));
            map.put("punishedBefore", e.getRequestHeaders().get("punishedBefore").toString().substring(2, e.getRequestHeaders().get("punishedBefore").toString().length() - 2));
            map.put("email", e.getRequestHeaders().get("email").toString().substring(2, e.getRequestHeaders().get("email").toString().length() - 2));
            map.put("number", e.getRequestHeaders().get("number").toString().substring(2, e.getRequestHeaders().get("number").toString().length() - 2));
            map.put("discordName", e.getRequestHeaders().get("discordName").toString().substring(2, e.getRequestHeaders().get("discordName").toString().length() - 2));
            map.put("discordId", e.getRequestHeaders().get("discordId").toString().substring(2, e.getRequestHeaders().get("discordId").toString().length() - 2));
        } catch(NullPointerException exception) {
            return 400;
        }
        int code = AppealDataHandler.makeAppeal(map);
        return code;
    }

    public void end() {
        server.stop();
        plugin.getServer().getLogger().info("[Admincore] Admincore HTTP Server Disabled");
    }
}
