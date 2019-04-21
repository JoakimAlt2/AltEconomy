package ru.altspace;

import java.sql.*;
import java.util.HashMap;

public class Network {

    private Connection con;
    private static HashMap<String, PreparedStatement> preparedStatements = new HashMap<>();

    private String url = AltEconomy.INSTANCE.getConfig().getString("url");
    private String user = AltEconomy.INSTANCE.getConfig().getString("user");
    private String password = AltEconomy.INSTANCE.getConfig().getString("password");

    public void createConnection() throws SQLException {

        if (con != null && !con.isClosed()) {
            return;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url + "?useUnicode=true&characterEncoding=UTF-8", user, password);
            Utils.getLogger().info("Database is not connecneted!");

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            Utils.getLogger().info("Cant connect to SQL!\n" + "-check params(url: " + url + "; user: " + user + "password: " + password + "\n-Check internet connection");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        synchronized (this) {

            preparedStatements.put("addBalance", con.prepareStatement(
                    "UPDATE `alteconomy` SET `value`=? WHERE `player`=? AND `type`=?"));

            preparedStatements.put("getBalance", con.prepareStatement(
                    "SELECT `type` FROM `alteconomy` WHERE `player`=? AND `type`=?"));

            preparedStatements.put("addPlayer", con.prepareStatement(
                    "INSERT INTO `alteconomy` (player) VALUES (?);"));

            preparedStatements.put("addCurrency", con.prepareStatement(
                    "ALTER TABLE `alteconomy` ADD `type`=? FLOAT;"));
        }
    }

    public void addPlayer(String player) {
        try {

            PreparedStatement addPlayer = preparedStatements.get("addPlayer");
            addPlayer.setString(1, player);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addCurrency(String currencyType) {
        try {
            //Не доделано?
            PreparedStatement addCurrency = preparedStatements.get("addCurrency");
            addCurrency.setString(1, currencyType);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public float getBalance(final String player, String currencyType) {
        try {
            PreparedStatement getBalance = preparedStatements.get("getBalance");
            getBalance.setString(1, player);
            getBalance.setString(2, currencyType);
            ResultSet rs = getBalance.executeQuery();
            if (rs.next()) {
                return rs.getFloat(currencyType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void addBalance(String player, String currencyType, float value) {
        try {
            PreparedStatement addBalance = preparedStatements.get("addBalance");
            addBalance.setFloat(2, getBalance(player, currencyType) + value);
            addBalance.setString(2, player);
            addBalance.setString(3, currencyType);
            addBalance.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
