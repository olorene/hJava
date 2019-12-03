package Jsoap;

import java.sql.*;

public class JsoapJdbc {
    public void connectToDb() throws ClassNotFoundException, SQLException {
//        String url = "jdbc:mysql://localhost:3306/study";
        String url = "jdbc:mysql://remotemysql.com:3306/2o3Hu4iJ7l";
        String user = "2o3Hu4iJ7l";
        String password = "SdmE9Ae7mO";
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
//            statement.executeUpdate("drop table first_table");
            statement.executeUpdate("create table first (id MEDIUMINT not null auto_incriment, " +
                    "name char (30) not null, primary_key(id))");
            statement.executeUpdate("insert  into first(name) value ('Inferno')");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
