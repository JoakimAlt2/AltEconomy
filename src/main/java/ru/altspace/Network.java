package ru.altspace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class Network {

    private Connection con;
    private AltEconomy altEconomy = new AltEconomy();
    private Utils util = new Utils();
    private static HashMap<String, PreparedStatement> preparedStatements = new HashMap<>();

    private String url = altEconomy.getConfig().getString("url");
    private String user = altEconomy.getConfig().getString("user");
    private String password = altEconomy.getConfig().getString("password");


    public Connection getConnection() { return con; }

    public void createConnection() throws SQLException {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            util.getLogger().info("База данных подключена!");


        } catch (SQLException sqlEx) {

            sqlEx.printStackTrace();
            util.getLogger().info("База данных не подключена!\n" + "Возможные причины:\n" + "-Неправильно введены данные(url: " + url + "; user: " + user + "password: " + password + "\n-Проблемы с интернет соединением");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            if (con != null && !con.isClosed()) {
                return;
            }

            preparedStatements.put("addBalance", con.prepareStatement(

                    "UPDATE INTO `alteconomy` (player, type, value) VALUES (?,?,?)"));

            preparedStatements.put("getBalance", con.prepareStatement(

                    "SELECT `type` FROM `alteconomy` WHERE `player`=? AND `type`=?"));

            preparedStatements.put("addPlayer", con.prepareStatement(

                    "INSERT INTO `alteconomy` (player) VALUES (?);"));
        }
    }


}
