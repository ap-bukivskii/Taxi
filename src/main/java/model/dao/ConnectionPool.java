package model.dao;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {
    private BasicDataSource basicDataSource = new BasicDataSource();

    private static class ConnectionPoolHolder {
        public static final ConnectionPool HOLDER_INSTANCE = new ConnectionPool();
    }
    private ConnectionPool() {
        //try {
          //  Class.forName("com.mysql.jdbc.Driver");
            ResourceBundle resourceBundle = ResourceBundle.getBundle("db_connection");
            basicDataSource.setUrl(resourceBundle.getString("URL"));
            basicDataSource.setUsername(resourceBundle.getString("USER"));
            basicDataSource.setPassword(resourceBundle.getString("PASS"));
            basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            basicDataSource.setMinIdle(5);
            basicDataSource.setMaxOpenPreparedStatements(100);
            basicDataSource.setMaxActive(250);
        //} catch (Exception e){}

    }

    public static ConnectionPool getInstance(){
        return ConnectionPoolHolder.HOLDER_INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }
}
