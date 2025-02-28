package com.tanner.epicGreetings;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.Random;

public class EpicGreetingsListener implements Listener {

    private FileConfiguration config;

    public EpicGreetingsListener(EpicGreetings epicGreetings) {
        config = epicGreetings.getConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        String joinMessage = getJoinMessage();
        e.setJoinMessage(Utility.convertConfigMessage(joinMessage, player));

        if (config.getBoolean("join-sound.enabled")) {
            playJoinSound(player);
        }
    }

    private void playJoinSound(Player player) {
        String soundName = config.getString("join-sound.sound", "ENTITY_PLAYER_LEVELUP");

        try {
            Sound sound = Sound.valueOf(soundName);
            player.playSound(player.getLocation(), sound, 1, 1);
        } catch (IllegalArgumentException exception) {
            player.sendMessage(ChatColor.RED + "Invalid sound in config! Using default.");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        }
    }

    private String getJoinMessage() {
        String joinMessage;
        if (config.getBoolean("random-join-messages.enabled"))
        {
            List<String> messages = config.getStringList("random-join-messages.messages");
            int randomIndex = new Random().nextInt(messages.size());
            joinMessage = messages.get(randomIndex);
        } else {
            joinMessage = config.getString("join-message");
        }
        return joinMessage;
    }
}
