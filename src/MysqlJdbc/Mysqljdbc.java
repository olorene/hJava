package MysqlJdbc;

import com.mysql.jdbc.MySQLConnection;

import java.sql.*;


public class Mysqljdbc
{
    public static void main(String[] args) {
        System.out.println("Start SQL Connections");

        MysqlJdbcPreparedStatement pstm = new MysqlJdbcPreparedStatement();
        pstm.myInsetPreparedStatement("Vlad3", "Zinchenko",
                Date.valueOf("1980-01-04"), "(809) 7282-1627", "ggarik83@gmail.com");
//        pstm.mySelectPreparedStatement();
//        firstConnectToBase();
    }

    public static void firstConnectToBase() {
        String sql = "SELECT first_name, last_name, email " +
                "FROM candidates";

        try (Connection conn = MySQLJDBCUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            //Print output message
            System.out.println(String.format("Connected to database %s" +
                    " successfully.", conn.getCatalog()));

            // loop through result set
            while (rs.next()) {
                System.out.println(rs.getString("first_name") + "\t" +
                                    rs.getString("last_name") + "\t" +
                                    rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
