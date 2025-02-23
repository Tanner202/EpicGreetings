package com.tanner.epicGreetings;

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

        String joinMessage;
        if (config.getBoolean("random-join-messages.enabled"))
        {
            List<String> messages = config.getStringList("random-join-messages.messages");
            int randomIndex = new Random().nextInt(messages.size());
            joinMessage = messages.get(randomIndex);
        } else {
            joinMessage = config.getString("join-message");
        }
        e.setJoinMessage(Utility.convertConfigMessage(joinMessage, player));

        player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    }
}
