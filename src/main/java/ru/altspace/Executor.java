package ru.altspace;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Executor implements CommandExecutor {

    private static AltEconomy altEconomy = new AltEconomy();
    private static Network network = new Network();
    private static Currency cur = new Currency();


    @Override
    public boolean onCommand(final CommandSender sender,final Command cmd,final String label,final String[] args) {

        if(cmd.getName().equalsIgnoreCase("pay") && args.length == 4){
            if(Bukkit.getPlayer(args[1]) != null){
                if(cur.isAdded(args[2])) {
                    Float value = null;
                    try {
                        value = Float.valueOf(args[3]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage("[AltEconomy]: Используйте /pay [player] [currencyType] [value]");
                    }
                    if(value != null) {
                        network.addBalance(args[1], args[2], value);
                        return true;
                    }
                } else sender.sendMessage("[AltEconomy]: Введенная вами валюта не существует!");
            } else sender.sendMessage("[AltEconomy]: Игрок не онлайн!");
        }
        return false;
    }
}
