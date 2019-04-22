package ru.altspace;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Currency {

    private static Network network = new Network();
    private static List<String> curencies = new ArrayList<String>();
    private static String nameOfDefaultCur;

/* временно не нужно
    Currency(final String name) {
        if(name.equals(nameOfDefaultCur)) { }
    }
*/
    private static void add(String name) {
        curencies.add(name);
    }

    public static boolean isAdded( String name) {
        return curencies.contains(name);
    }
    public static List<String> getCurList() { return curencies; }

    public static void loadCurencies() {
        FileConfiguration cfg = AltEconomy.INSTANCE.getConfig();
        for(String name : cfg.getStringList("currencies")) {
            if(!isAdded(name)) {
                add(name);
            }
        }
        if(cfg.getString("defaultcur") != null)
        { nameOfDefaultCur = cfg.getString("defaultcur"); }
    }

    public static void setDefaultCur(String name) { if (isAdded(name)) { AltEconomy.INSTANCE.getConfig().set("defaultcur", name); } }

    public static void addCurrency(String name) {
        Currency.add(name);
        network.addCurrency(name);
    }
}
