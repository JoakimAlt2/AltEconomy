package ru.altspace;

import java.util.ArrayList;
import java.util.List;

public class Currency {

    private static AltEconomy altEconomy = new AltEconomy();
    private static Network network = new Network();
    private static List<String> curencies = new ArrayList<String>();

    Currency(){

    }

    Currency(final String name) {

    }

    public static void add(final String name) {
        curencies.add(name);
    }
    public static boolean isAdded(final String name) {
        if(curencies.contains(name)){
            return true;
        } else return false;
    }
}
