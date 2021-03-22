package dev.rudrecciah.admincore.errors;

import static dev.rudrecciah.admincore.Main.plugin;

public class ErrorHandler {
    public static void onError(int code) {
        switch(code) {
            case 0:
                plugin.getLogger().severe("Data.yml could not be created! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                break;
            case 1:
                plugin.getLogger().severe("Data.yml could not be saved! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                break;
            case 2:
                plugin.getLogger().severe("A playerdata file could not be created using a player object! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                break;
            case 3:
                plugin.getLogger().severe("A playerdata file could not be created using a player uuid! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                break;
            case 4:
                plugin.getLogger().severe("A playerdata file could not be saved! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                break;
            case 5:
                plugin.getLogger().severe("A report file could not be created! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                break;
            case 6:
                plugin.getLogger().severe("A report file could not be saved! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
        }
    }
}
