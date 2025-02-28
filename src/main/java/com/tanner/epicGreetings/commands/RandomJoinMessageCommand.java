package com.tanner.epicGreetings.commands;

import com.tanner.epicGreetings.EpicGreetings;
import com.tanner.epicGreetings.Utility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class RandomJoinMessageCommand implements CommandExecutor {

    private EpicGreetings epicGreetings;

    private FileConfiguration config;

    public RandomJoinMessageCommand(EpicGreetings epicGreetings) {
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

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("enable")) {
                config.set("random-join-messages.enabled", true);
                commandSender.sendMessage(ChatColor.GREEN + "Random join message enabled");
            } else if (args[0].equalsIgnoreCase("disable")) {
                config.set("random-join-messages.enabled", false);
                commandSender.sendMessage(ChatColor.GREEN + "Random join message " + ChatColor.RED + "disabled");
            } else if (args[0].equalsIgnoreCase("list")) {
                List<String> listRandomMessages = config.getStringList("random-join-messages.messages");
                StringBuilder listMessage = new StringBuilder("The current messages are:");
                for (int i = 0; i < listRandomMessages.size(); i++) {
                    listMessage.append("\nIndex ").append(i).append(": ").append(listRandomMessages.get(i));
                }
                commandSender.sendMessage(ChatColor.GRAY + listMessage.toString());
            } else {
                commandSender.sendMessage(ChatColor.RED + "Please use the correct usage:\n" +
                        "- /randomjoinmessage enable\n" +
                        "- /randomjoinmessage disable\n" +
                        "- /randomjoinmessage add <message>\n" +
                        "- /randomjoinmessage remove <index>\n" +
                        "- /randomjoinmessage list");
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
            int indexToRemove;
            try {
                indexToRemove = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                commandSender.sendMessage(ChatColor.RED + "This is not a valid integer. Please use the correct format: /randomjoinmessage remove <index>");
                return false;
            }

            if (epicGreetings.getConfig().getStringList("random-join-messages.messages").isEmpty()) {
                commandSender.sendMessage(ChatColor.RED + "You cannot remove a message because the random messages list is empty.");
                return false;
            }

            List<String> existingRandomMessages = config.getStringList("random-join-messages.messages");

            if (indexToRemove < existingRandomMessages.size() && indexToRemove >= 0) {
                commandSender.sendMessage(ChatColor.GREEN + "You " + ChatColor.RED + "removed: " + ChatColor.RESET + Utility.convertConfigMessage(existingRandomMessages.get(indexToRemove)) + ChatColor.GREEN + " from the random message list.");
                existingRandomMessages.remove(indexToRemove);
                config.set("random-join-messages.messages", existingRandomMessages);
            } else {
                commandSender.sendMessage(ChatColor.RED + "This index is outside of the range of the list. Please choose a number between 0-" + (existingRandomMessages.size() - 1));
            }
        } else if (args.length >= 2 && args[0].equalsIgnoreCase("add")) {
            StringBuilder newRandomMessage = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                newRandomMessage.append(args[i]);

                if (i != args.length - 1) {
                    newRandomMessage.append(" ");
                }
            }
            List<String> randomMessages = config.getStringList("random-join-messages.messages");
            randomMessages.add(newRandomMessage.toString());
            config.set("random-join-messages.messages", randomMessages);
            commandSender.sendMessage(ChatColor.GREEN + "You added: " + ChatColor.RESET + Utility.convertConfigMessage(newRandomMessage.toString()) + ChatColor.GREEN + " to the random message list");
        } else {
            commandSender.sendMessage(ChatColor.RED + "Please use the correct usage:\n" +
                    "- /randomjoinmessage enable\n" +
                    "- /randomjoinmessage disable\n" +
                    "- /randomjoinmessage add <message>\n" +
                    "- /randomjoinmessage remove <index>\n" +
                    "- /randomjoinmessage list");
        }
        epicGreetings.saveConfig();

        return false;
    }
}
