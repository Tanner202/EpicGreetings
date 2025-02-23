package com.tanner.epicGreetings;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetJoinMessageCommand implements CommandExecutor {

    private EpicGreetings epicGreetings;

    private FileConfiguration config;

    public SetJoinMessageCommand(EpicGreetings epicGreetings) {
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

        StringBuilder newJoinMessage = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            newJoinMessage.append(args[i]);

            if (i != args.length - 1) {
                newJoinMessage.append(" ");
            }
        }
        config.set("join-message", newJoinMessage.toString());
        epicGreetings.saveConfig();
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            commandSender.sendMessage(ChatColor.GREEN + "You have set the join message to: " + ChatColor.RESET + Utility.convertConfigMessage(newJoinMessage.toString(), player));
        } else {
            commandSender.sendMessage(ChatColor.GREEN + "You have set the join message to: " + ChatColor.RESET + Utility.convertConfigMessage(newJoinMessage.toString()));
        }
        return false;
    }
}
