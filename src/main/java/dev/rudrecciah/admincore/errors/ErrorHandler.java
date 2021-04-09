package dev.rudrecciah.admincore.errors;

import dev.rudrecciah.admincore.webhook.ErrorLogger;

import static dev.rudrecciah.admincore.Main.plugin;

public class ErrorHandler {
    public static void onError(int code) {
        long errorcode = System.currentTimeMillis();
        switch(code) {
            case 0:
                plugin.getLogger().severe("Data.yml could not be created! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                plugin.getLogger().severe("Error Code: DC-" + errorcode);
                ErrorLogger.logError("DC-" + errorcode, "DATA_CREATE");
                break;
            case 1:
                plugin.getLogger().severe("Data.yml could not be saved! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                plugin.getLogger().severe("Error Code: DS-" + errorcode);
                ErrorLogger.logError("DS-" + errorcode, "DATA_SAVE");
                break;
            case 2:
                plugin.getLogger().severe("A playerdata file could not be created using a player object! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                plugin.getLogger().severe("Error Code: PDCN-" + errorcode);
                ErrorLogger.logError("PDCN-" + errorcode, "PLAYERDATA_CREATE_WITH_NAME");
                break;
            case 3:
                plugin.getLogger().severe("A playerdata file could not be created using a player uuid! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                plugin.getLogger().severe("Error Code: PDCU-" + errorcode);
                ErrorLogger.logError("PDCU-" + errorcode, "PLAYERDATA_CREATE_WITH_UUID");
                break;
            case 4:
                plugin.getLogger().severe("A playerdata file could not be saved! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                plugin.getLogger().severe("Error Code: PDS-" + errorcode);
                ErrorLogger.logError("PDS-" + errorcode, "PLAYERDATA_SAVE");
                break;
            case 5:
                plugin.getLogger().severe("A report file could not be created! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                plugin.getLogger().severe("Error Code: RDC-" + errorcode);
                ErrorLogger.logError("RDC-" + errorcode, "REPORT_DATA_CREATE");
                break;
            case 6:
                plugin.getLogger().severe("A report file could not be saved! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                plugin.getLogger().severe("Error Code: RDS-" + errorcode);
                ErrorLogger.logError("RDS-" + errorcode, "REPORT_DATA_SAVE");
                break;
            case 7:
                plugin.getLogger().severe("An appeal file could not be created! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                plugin.getLogger().severe("Error Code: APDC-" + errorcode);
                ErrorLogger.logError("APDC-" + errorcode, "APPEAL_DATA_CREATE");
                break;
            case 8:
                plugin.getLogger().severe("An appeal file could not be saved! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                plugin.getLogger().severe("Error Code: APDS-" + errorcode);
                ErrorLogger.logError("APDS-" + errorcode, "APPEAL_DATA_SAVE");
                break;
            case 9:
                plugin.getLogger().severe("An appeal file could not be deleted! This is a potentially fatal bug. Restart the server and try again. If this behavior continues, report it at https://rudrecciah.dev/admincore/bugs immediately. ");
                plugin.getLogger().severe("Error Code: APDD-" + errorcode);
                ErrorLogger.logError("APDD-" + errorcode, "APPEAL_DATA_DELETE");
                break;
        }
    }
}
