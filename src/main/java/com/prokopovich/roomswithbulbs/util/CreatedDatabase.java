package com.prokopovich.roomswithbulbs.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class CreatedDatabase {

    private static final Logger LOGGER = LogManager.getLogger(CreatedDatabase.class);
    private static final String CREATE_ROOMS_WITH_BULBS_TABLE = "CREATE SCHEMA IF NOT EXISTS rooms_with_bulbs";
    private static final String CREATE_ROOM_TABLE = "CREATE TABLE IF NOT EXISTS rooms ("
            + "id INT NOT NULL AUTO_INCREMENT, "
            + "name VARCHAR(50) NOT NULL, "
            + "country VARCHAR(50) NOT NULL, "
            + "bulb_status VARCHAR(5) NOT NULL DEFAULT 'OFF',"
            + " PRIMARY KEY (id))";
    private static final String INSERT_TEST_DATA = "INSERT INTO rooms VALUES (1,'Room 1','Belarus','OFF')," +
            "(2,'Room 2','Finland','ON'),(3,'Room 3','Denmark','OFF')," +
            "(4,'Room 4','Monaco','OFF'),(5,'Room 5','Belarus','OFF')," +
            "(6,'Room 6','Monaco','ON'),(7,'Room 7','Belarus','ON')," +
            "(8,'Room 8','Brazil','OFF'),(9,'Room 9','Egypt','ON')," +
            "(10,'Room 10','Russia','OFF')";

    public static Connection getConnection() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/";
        String username = "admin";
        String password = "admin";
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static void createDatabase() {
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(CREATE_ROOMS_WITH_BULBS_TABLE);
            stmt.executeUpdate("USE rooms_with_bulbs");
            stmt.executeUpdate(CREATE_ROOM_TABLE);
            stmt.executeUpdate(INSERT_TEST_DATA);
            LOGGER.info("CREATE ROOMS WITH BULBS TABLE - table created");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
