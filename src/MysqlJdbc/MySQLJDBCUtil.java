package MysqlJdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLJDBCUtil {
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try(FileInputStream f = new FileInputStream("C:\\\\java\\mysql\\db.properties")) {
            //DB parameters
            Properties pros = new Properties();
            pros.load(f);
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");

            //create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }
}
