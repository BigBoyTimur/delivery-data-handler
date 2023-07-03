import java.sql.*;
public class Main {
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver" );
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2311_delivery" ,
                    "std_2311_delivery" , "sosa1234" );
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM client" ;
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                int id = result.getInt("id" );
                String name = result.getString("name" );
                System.out.print("id = " + id);
                System.out.print(" , name = \"" + name + "\"" );
            }
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}