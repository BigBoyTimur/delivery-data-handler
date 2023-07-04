import java.sql.*;
// Устанавливает соединение и создает представления всех таблиц
public class Link {
    private Clients clients;
    public Link() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver" );
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2311_delivery" ,
                    "std_2311_delivery" , "sosa1234" );

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM client");
            this.clients = new Clients();

            while (result.next()) {
                int id = result.getInt("id" );
                String name = result.getString("name" );
                String phone = result.getString("phone" );
                String address = result.getString("address");
                String passwordHash = result.getString("password_hash");
                clients.getClients().add(new Client(id, name, passwordHash, phone, address));
            }
//            System.out.println(clients.getClients().size());




//            Statement statement = connection.createStatement();
//            String query = "SELECT * FROM client" ;
//            ResultSet result = statement.executeQuery(query);
//
//            while(result.next()){
//                int id = result.getInt("id" );
//                String name = result.getString("name" );
//                System.out.print("id = " + id);
//                System.out.print(" , name = \"" + name + "\"" );
//            }

            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public Clients getClients() {
        return clients;
    }
}