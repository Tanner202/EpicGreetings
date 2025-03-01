package com.tanner.epicGreetings.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length != 1) {
            commandSender.sendMessage(ChatColor.RED + "This command does not exist. Please use '/epicgreetings help' to list commands.");
            return false;
        }

        if (!args[0].equalsIgnoreCase("help")) {
            commandSender.sendMessage(ChatColor.RED + "This command does not exist. Please use '/epicgreetings help' to list commands.");
            return false;
        }

        if (commandSender.hasPermission("epicgreetings.use")) {
            commandSender.sendMessage(ChatColor.DARK_PURPLE + "[EpicGreetings Help]\n" + ChatColor.GRAY +
                    "- epicgreetings help - Shows this help menu.\n" +
                    "- setjoinmessage <message> - Sets the join message to specified message.\n" +
                    "- randomjoinmessage enable - Enables random join messages.\n" +
                    "- randomjoinmessage disable - Disables random join messages.\n" +
                    "- randomjoinmessage add <message> - Adds a message to the random join message list.\n" +
                    "- randomjoinmessage remove <index> - Removes a message from the random join message list at a certain index.\n" +
                    "- randomjoinmessage list - Lists out the current list of random join messages and their indexes.\n" +
                    "- joinsound enable - Enables join sound.\n" +
                    "- joinsound disable - Disables join sound.\n" +
                    "- joinsound set <sound> - Sets join sound to minecraft spigot sounds.");
        } else {
            commandSender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
        }
        return false;
    }
}
