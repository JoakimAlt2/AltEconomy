package ru.altspace;

import org.bukkit.plugin.java.JavaPlugin;

public class AltEconomy extends JavaPlugin {

    Utils util = new Utils();
    Network network = new Network();

    @Override
    public void onEnable() {
        loadConfig();
        network.createConnection();
        util.getLogger().info("Плагин включен!!");
    }

    @Override
    public void onDisable() {
        util.getLogger().info("Плагин выключен!");
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();

        System.out.println("Loading config...");
        util.getLogger().info("Загрузка конфига...");
    }
}
