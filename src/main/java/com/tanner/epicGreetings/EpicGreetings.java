package com.tanner.epicGreetings;

import com.tanner.epicGreetings.commands.RandomJoinMessageCommand;
import com.tanner.epicGreetings.commands.SetJoinMessageCommand;
import com.tanner.epicGreetings.commands.JoinSoundCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EpicGreetings extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new EpicGreetingsListener(this), this);
        getCommand("setjoinmessage").setExecutor(new SetJoinMessageCommand(this));
        getCommand("joinsound").setExecutor(new JoinSoundCommand(this));
        getCommand("randomjoinmessage").setExecutor(new RandomJoinMessageCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
