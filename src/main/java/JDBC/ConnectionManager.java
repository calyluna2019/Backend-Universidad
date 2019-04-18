package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private Connection connection = null;

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed() || connection.isValid(10000)) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Universidad", "root", "carlosluna");
        }
        return connection;
    }
}
