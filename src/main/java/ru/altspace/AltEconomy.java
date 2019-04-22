package ru.altspace;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class AltEconomy extends JavaPlugin {

    public static AltEconomy INSTANCE;

    @Override
    public void onEnable() {
        loadConfig();
        INSTANCE = this;
        Network network = new Network();

        Bukkit.getScheduler().runTaskAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                try {
                    network.createConnection();
                } catch (SQLException e) {
                    getLogger().severe("ERROR! Cant load SQL, check config!");
                    getLogger().severe("PLUGIN DISABLED");
                }
            }
        });

        Currency.loadCurencies();
        Utils.getLogger().info("Plugin is enabled!");

    }

    @Override
    public void onDisable() {
        Utils.getLogger().info("Plugin is disabled");
    }

    private void loadConfig() {
        this.saveDefaultConfig();
        this.getConfig().options().copyHeader();
        Utils.getLogger().info("Loading config...");
    }
}
