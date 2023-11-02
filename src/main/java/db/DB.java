package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private final String URL = "jdbc:mariadb://localhost:3306/webapp2";
    private final String USERNAME = "student";
    private final String PASSWORD = "123";
    private Connection connection;

    public DB() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
