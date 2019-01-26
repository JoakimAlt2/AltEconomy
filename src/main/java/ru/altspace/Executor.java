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
    private static Utils ut = new Utils();
    private static Language ln = new Language();


    @Override
    public boolean onCommand(final CommandSender sender,final Command cmd,final String label,final String[] args) {

        if(cmd.getName().equalsIgnoreCase("pay") && args.length == 4){
            if(Bukkit.getPlayer(args[1]) != null){
                if(cur.isAdded(args[2])) {
                    Float value = null;
                    try {
                        value = Float.valueOf(args[3]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage("[AltEconomy]: " + ln.getTUse() +" /pay [player] [currencyType] [value]");
                    }
                    if(value != null) {
                        network.addBalance(args[1], args[2], value);
                        return true;
                    }
                } else sender.sendMessage("[AltEconomy]: " + ln.getTCurCheckExeption());
            } else sender.sendMessage("[AltEconomy]: " + ln.getTPlayerIsNotOnline());
        }

        if(cmd.getName().equalsIgnoreCase("curlist") && args.length == 1){
            if(sender instanceof Player){
                Player pl = (Player) sender;
                pl.sendMessage("[AltEconomy]: " + ln.getTCurList());
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
                            pl.sendMessage(ln.getPlayerBalance(0) + network.getBalance(pl.getName(), args[1]));
                        } else sender.sendMessage("[AltEconomy]: " + ln.getTCurCheckExeption());
                    }
                }
            } else {
                if(Bukkit.getPlayer(args[2]) != null) {
                    if(sender instanceof Player){
                        Player pl = (Player) sender;
                        if(cur.isAdded(args[1])) {
                            pl.sendMessage(ln.getPlayerBalance(1) + network.getBalance(args[2], args[1]));
                        } else sender.sendMessage("[AltEconomy]: " + ln.getTCurCheckExeption());
                    }
                } else sender.sendMessage("[AltEconomy]: " + ln.getTPlayerIsNotOnline());
            }
        }
        if(cmd.getName().equalsIgnoreCase("setDefaultCur") && args.length == 2){
            if(cur.isAdded(args[1])){
                ut.setDefaultCur(args[1]);
                return true;
            } else if(sender instanceof Player){
                Player pl = (Player) sender;
                pl.sendMessage("[AltEconomy]: " + ln.getTCurCheckExeption());

            }
        }
        return false;
    }
}
