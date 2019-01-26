package ru.altspace;

import java.util.logging.Logger;


public class Utils {

    private static AltEconomy altEconomy = new AltEconomy();
    private static Logger logger = Logger.getLogger("AltEconomy");
    private static Network network = new Network();
    private static Currency cur = new Currency();
    private static String defaultCur = null;

    public static Logger getLogger() {
        return logger;
    }

    public static void loadCurencies() {
        for(String name : altEconomy.getConfig().getStringList("currencies")) {
            if(!cur.isAdded(name)) {
                cur.add(name);
            }
        }
    }

    public static void addCurrency(final String name) {
        cur.add(name);
        network.addCurrency(name);
    }

    public static void setDefaultCur(String name) { defaultCur = name;  }
    public static String getDefaultCur() { return defaultCur; }
}
