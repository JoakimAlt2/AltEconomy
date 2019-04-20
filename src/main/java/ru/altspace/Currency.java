package ru.altspace;

import java.util.ArrayList;
import java.util.List;

public class Currency {

    private static AltEconomy altEconomy = new AltEconomy();
    private static Network network = new Network();
    private static List<String> curencies = new ArrayList<String>();
    private static String nameOfDefaultCur = null;

    Currency(){

    }
/* временно не нужно
    Currency(final String name) {
        if(name.equals(nameOfDefaultCur)) { }
    }
*/
    public void add(final String name) {
        curencies.add(name);
    }
    public boolean isAdded(final String name) {
        return curencies.contains(name);
    }
    public List<String> getCurList() { return curencies; }
    public void loadCurencies() {
        for(String name : altEconomy.getConfig().getStringList("currencies")) {
            if(!isAdded(name)) {
                add(name);
            }
        }
        if(altEconomy.getConfig().getString("defaultcur") != null)
        { nameOfDefaultCur = altEconomy.getConfig().getString("defaultcur"); }
    }
    public void setDefaultCur(String name) { if (isAdded(name)) { altEconomy.getConfig().set("defaultcur", name); } }
    public void addCurrency(final String name) {
        add(name);
        network.addCurrency(name);
    }
}
