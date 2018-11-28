package ru.altspace;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        if(cmd.getName().equalsIgnoreCase("curlist") && args.length == 1){
            if(sender instanceof Player){
                Player pl = (Player) sender;
                pl.sendMessage("[AltEconomy]: Список валют:");
                for(String cur : cur.getCurList()){
                    pl.sendMessage("- " + cur);
                }
                return true;
            }
        }

        if(cmd.getName().equalsIgnoreCase("cur") && (args.length == 2 || args.length == 3)) {
            if(args.length == 2){
                if(cur.isAdded(args[1])) {
                    if(sender instanceof Player){
                        Player pl = (Player) sender;
                        if(cur.isAdded(args[1])) {
                            pl.sendMessage("Ваш баланс по данной валюте:" + network.getBalance(pl.getName(), args[1]));
                        } else sender.sendMessage("[AltEconomy]: Введенная вами валюта не существует!");
                    }
                }
            } else {
                if(Bukkit.getPlayer(args[2]) != null) {
                    if(sender instanceof Player){
                        Player pl = (Player) sender;
                        if(cur.isAdded(args[1])) {
                            pl.sendMessage("Баланс игрока по данной валюте:" + network.getBalance(args[2], args[1]));
                        } else sender.sendMessage("[AltEconomy]: Введенная вами валюта не существует!");
                    }
                } else sender.sendMessage("[AltEconomy]: Игрок не онлайн!");
            }
        }
        return false;
    }
}
