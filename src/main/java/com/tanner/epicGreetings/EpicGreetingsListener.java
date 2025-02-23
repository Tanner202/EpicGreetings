package com.tanner.epicGreetings;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EpicGreetingsListener implements Listener {

    private FileConfiguration config;

    public EpicGreetingsListener(EpicGreetings epicGreetings) {
        config = epicGreetings.getConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        String joinMessage = Utility.convertConfigMessage(config.getString("join-message"), player);
        e.setJoinMessage(joinMessage);

        player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    }
}
