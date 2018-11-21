package ru.altspace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Network {

    private Connection con;
    private AltEconomy altEconomy = new AltEconomy();
    private Utils util = new Utils();

    private String url = altEconomy.getConfig().getString("url");
    private String user = altEconomy.getConfig().getString("user");
    private String password = altEconomy.getConfig().getString("password");


    public Connection getConnection() { return con; }

    public void createConnection() {

        try {

            con = DriverManager.getConnection(url, user, password);
            util.getLogger().info("База данных подключена!");


        } catch (SQLException sqlEx) {

            sqlEx.printStackTrace();
            util.getLogger().info("База данных не подключена!\n" + "Возможные причины:\n" + "-Неправильно введены данные(url: " + url + "; user: " + user + "password: " + password + "\n-Проблемы с интернет соединением");

        }

    }


}
