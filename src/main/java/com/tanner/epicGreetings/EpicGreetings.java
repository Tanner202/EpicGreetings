package com.tanner.epicGreetings;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EpicGreetings extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new EpicGreetingsListener(this), this);
        getCommand("setjoinmessage").setExecutor(new SetJoinMessageCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
