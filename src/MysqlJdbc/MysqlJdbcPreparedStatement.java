package MysqlJdbc;

import java.io.PrintWriter;
import java.sql.*;

public class MysqlJdbcPreparedStatement {
    public void myInsetPreparedStatement(String firstName, String lastName,
                                         Date dob, String phone, String email){

        String insertSql = "INSERT INTO candidates(first_name, last_name, dob, phone, email) "
                + "VALUES(?, ?, ?, ?, ?)";

        try {
            Connection conn = MySQLJDBCUtil.getConnection();

            PreparedStatement pstm = conn.prepareStatement(insertSql);
            pstm.setString(1, firstName);
            pstm.setString(2, lastName);
            pstm.setDate(3, dob);
            pstm.setString(4, phone);
            pstm.setString(5, email);

            int rowAffected = pstm.executeUpdate();

            System.out.println(String.format("Row affected %d",  rowAffected));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void mySelectPreparedStatement() {
        try {
            Connection conn = MySQLJDBCUtil.getConnection();
            String sqlUpsate = "UPDATE candidates SET last_name = ? WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(sqlUpsate);

            String lastName = "France";
            int id = 100;
            pstm.setString(1, lastName);
            pstm.setInt(2, id);
            int rowAffected  = pstm.executeUpdate();
            System.out.println(String.format("Row affected %d", rowAffected));

            lastName = "Loye";
            id = 101;
            pstm.setString(1, lastName);
            pstm.setInt(2, id);
            rowAffected = pstm.executeUpdate();
            System.out.println(String.format("Row affected %d", rowAffected));

            pstm.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
