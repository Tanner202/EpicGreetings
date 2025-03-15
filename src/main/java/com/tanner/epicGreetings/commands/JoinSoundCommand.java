package com.tanner.epicGreetings.commands;

import com.tanner.epicGreetings.EpicGreetings;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class JoinSoundCommand implements CommandExecutor {

    private EpicGreetings epicGreetings;

    private FileConfiguration config;

    public JoinSoundCommand(EpicGreetings epicGreetings) {
        config = epicGreetings.getConfig();
        this.epicGreetings = epicGreetings;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player | commandSender instanceof ConsoleCommandSender)) { return false; }

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (!player.hasPermission("epicgreetings.use")) {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                return false;
            }
        }

        if (args.length > 0) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("enable")) {
                    config.set("join-sound.enabled", true);
                    commandSender.sendMessage(ChatColor.GREEN + "Join sound enabled");
                } else if (args[0].equalsIgnoreCase("disable")) {
                    config.set("join-sound.enabled", false);
                    commandSender.sendMessage(ChatColor.GREEN + "Join sound " + ChatColor.RED + "disabled");
                } else {
                    commandSender.sendMessage(ChatColor.RED + "Please use the correct usage:\n" +
                            "- /joinsound enable\n" +
                            "- /joinsound disable\n" +
                            "- /joinsound set <sound_name>");
                }
            } else if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
                String soundName = args[1];
                try {
                    Sound.valueOf(soundName);
                    config.set("join-sound.sound", soundName);
                    epicGreetings.saveConfig();
                    commandSender.sendMessage(ChatColor.GREEN + "You have set the join sound to: '" + soundName + "'");
                } catch (IllegalArgumentException exception) {
                    commandSender.sendMessage(ChatColor.RED + "This is not a valid sound name. Please choose a valid sound name. \nA list of sounds can be found on: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html\n- Example: ENTITY_PLAYER_LEVELUP");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "Please use the correct usage:\n" +
                        "- /joinsound enable\n" +
                        "- /joinsound disable\n" +
                        "- /joinsound set <sound_name>");
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "Please use the correct usage:\n" +
                    "- /joinsound enable\n" +
                    "- /joinsound disable\n" +
                    "- /joinsound set <sound_name>");
        }

        return false;
    }
}
