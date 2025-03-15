package com.tanner.epicGreetings;

import com.tanner.epicGreetings.commands.HelpCommand;
import com.tanner.epicGreetings.commands.RandomJoinMessageCommand;
import com.tanner.epicGreetings.commands.SetJoinMessageCommand;
import com.tanner.epicGreetings.commands.JoinSoundCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class EpicGreetings extends JavaPlugin {

    @Override
    public void onEnable() {
        File configFile = new File(getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            getLogger().warning("Config not found! Creating default...");
            saveDefaultConfig();
        } else {
            try {
                getConfig().load(configFile);
            } catch (Exception e) {
                backupConfig();
            }
        }

        Bukkit.getPluginManager().registerEvents(new EpicGreetingsListener(this), this);
        getCommand("epicgreetings").setExecutor(new HelpCommand());
        getCommand("setjoinmessage").setExecutor(new SetJoinMessageCommand(this));
        getCommand("joinsound").setExecutor(new JoinSoundCommand(this));
        getCommand("randomjoinmessage").setExecutor(new RandomJoinMessageCommand(this));
    }

    public void backupConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        File backupFile = new File(getDataFolder(), "config_backup.yml");

        if (configFile.exists()) {
            if (backupFile.exists()) {
                backupFile.delete();
            }
            configFile.renameTo(backupFile);
            getLogger().warning("Backed up old config as config_backup.yml");
        }

        saveDefaultConfig();
    }


    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }
}
