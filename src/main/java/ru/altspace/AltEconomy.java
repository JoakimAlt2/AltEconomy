package ru.altspace;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class AltEconomy extends JavaPlugin {

    Utils util = new Utils();
    Network network = new Network();
    private static AltEconomy inst;

    public AltEconomy(){
        final AltEconomy plugin = inst = this;
    }

    @Override
    public void onEnable() {
        loadConfig();
        try {
            network.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        util.loadCurencies();
        util.getLogger().info("Plugin is enabled!");
        System.out.println("Ky");

    }

    @Override
    public void onDisable() {
        util.getLogger().info("Plugin is disabled");
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();

        System.out.println("Loading config...");
        util.getLogger().info("Loading config...");
    }

    public static void main(String args[]){}
}
