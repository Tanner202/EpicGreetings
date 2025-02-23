package com.tanner.epicGreetings;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utility {

    public static String convertConfigMessage(String joinMessage) {
        joinMessage = ChatColor.translateAlternateColorCodes('&', joinMessage);
        return joinMessage;
    }

    public static String convertConfigMessage(String joinMessage, Player player) {
        joinMessage = joinMessage.replace("{player}", player.getDisplayName());
        joinMessage = ChatColor.translateAlternateColorCodes('&', joinMessage);
        return joinMessage;
    }
}
