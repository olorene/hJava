import java.sql.*;
import java.util.Calendar;
import java.util.Locale;

public class HelloWorld {
    private int a = 10;

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/menagerie";
        String user = "vzinchenko";
        String password = "babylon-5";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM pet";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Name\t Owner\t Species\t sex\t birth\t death");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String owner = resultSet.getString("owner");
                String species = resultSet.getString("species");
                String sex = resultSet.getString("sex");
                String birth = resultSet.getString("birth");
                String death = resultSet.getString("death");
                System.out.println(name + "\t" + owner + "\t" + species + "\t" + sex + "\t" + birth + "\t" + death + "\t");

            }
            String aName = "Mozart";
            String aOwner = "Alex";
            String aSpecies = "dog";
            String aSex = "m";
            String aBirth = "2018-10-12";
            String queryInsert = "INSERT TNTO pet VALUES(" + aName
                    + aOwner +
                    aSpecies +
                    aSex +
                    aBirth + ")";

//                statement.executeUpdate("DROP TABLE first_table");
//                statement.executeUpdate("CREATE TABLE first (id MEDIUMINT NOT NULL auto_incriment, name CHAR (30) NOT NULL, primary_key(id))");
//                statement.executeUpdate("INSERT  INTO first(name) VALUE ('Inferno')");


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
