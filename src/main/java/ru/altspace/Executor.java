package ru.altspace;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Executor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        final String prefix = "[AltEconomy]: ";

        //Хуйня редкостная, нахуй они нужны - не ясно
        Network network = new Network();

        if(cmd.getName().equalsIgnoreCase("pay") && (args.length == 4) || (args.length == 3)){
            if(Bukkit.getPlayer(args[1]) != null){
                if(Currency.isAdded(args[2])) {

                    //Не примитивный тип float? Што?
                    Float value = null;
                    try {
                        value = Float.valueOf(args[3]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(prefix + Language.getTUse() +" /pay [player] [currencyType] [value]");
                    }
                    if(value != null) {
                        network.addBalance(args[1], args[2], value);
                        return true;
                    }
                } else sender.sendMessage(prefix + Language.getTCurCheckExeption());
            } else sender.sendMessage(prefix + Language.getTPlayerIsNotOnline());
        }

        if(cmd.getName().equalsIgnoreCase("curlist") && args.length == 1){
            if(sender instanceof Player){
                Player pl = (Player) sender;
                pl.sendMessage(prefix + Language.getTCurList());
                for(String cur : Currency.getCurList()){
                    pl.sendMessage("- " + cur);
                }
                return true;
            }
        }

        if(cmd.getName().equalsIgnoreCase("curs") && (args.length == 2 || args.length == 3)) {
            if(args.length == 2){
                if(Currency.isAdded(args[1])) {
                    if(sender instanceof Player){
                        Player pl = (Player) sender;
                        if(Currency.isAdded(args[1])) {
                            pl.sendMessage(Language.getPlayerBalance(0) + network.getBalance(pl.getName(), args[1]));
                        } else sender.sendMessage(prefix + Language.getTCurCheckExeption());
                    }
                }
            } else {
                if(Bukkit.getPlayer(args[2]) != null) {
                    if(sender instanceof Player){
                        Player pl = (Player) sender;
                        if(Currency.isAdded(args[1])) {
                            pl.sendMessage(Language.getPlayerBalance(1) + network.getBalance(args[2], args[1]));
                        } else sender.sendMessage(prefix + Language.getTCurCheckExeption());
                    }
                } else sender.sendMessage(prefix + Language.getTPlayerIsNotOnline());
            }
        }
        if(cmd.getName().equalsIgnoreCase("setDefaultCur") && args.length == 2){
            if(Currency.isAdded(args[1])){
                Currency.setDefaultCur(args[1]);
                return true;
            } else if(sender instanceof Player){
                Player pl = (Player) sender;
                pl.sendMessage(prefix + Language.getTCurCheckExeption());

            }
        }
        if(cmd.getName().equalsIgnoreCase("addCur")) {
            if(args.length == 2 && args[1] != null && !Currency.isAdded(args[1])) {
                Currency.addCurrency(args[1]);
                return true;
            }
        }
        return false;
    }
}
