package ru.altspace;

import java.util.logging.Logger;


public class Utils {

    private static AltEconomy altEconomy = new AltEconomy();
    private static Logger logger = Logger.getLogger("AltEconomy");
    private static Network network = new Network();
    private static Currency cur = new Currency();

    public static Logger getLogger() {
        return logger;
    }


}
