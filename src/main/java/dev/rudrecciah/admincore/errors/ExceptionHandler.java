package dev.rudrecciah.admincore.errors;

import static dev.rudrecciah.admincore.Main.plugin;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        plugin.getLogger().severe("An error occoured! This may be an error with Admincore or an error with something else. We're not sure, but it's most likely an error with Admincore. For debugging purposes, here's the details:");
        long time = System.currentTimeMillis();
        plugin.getLogger().severe("Error Code: U-" + time);
        plugin.getLogger().severe("If this is an error with Admincore and not another plugin or your implementation or usage of Admincore, please report this bug at https://rudrecciah.dev/admincore/bugs.");
        plugin.getLogger().severe("Throwable: " + e.getMessage());
        plugin.getLogger().severe("Thread: " + t.toString());
        plugin.getLogger().severe("Stack Trace:");
        e.printStackTrace();
    }

}
