import java.sql.*;
// Устанавливает соединение и создает представления всех таблиц
public class Link {
    private Clients clients; // используется для регистрации
    private Users users; //используется для входа и содержит данные о клиентах, курьерах, администраторах
    private Packages packages;
    private PackagesCouriers packagesCouriers;
    private static Connection connection;
    private static final Link INSTANCE = new Link();
    private Link() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver" );
            connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2311_delivery" ,
                    "std_2311_delivery" , "sosa1234" );


            this.clients = new Clients(connection);
            this.users = new Users();
            this.packages = new Packages();
            this.packagesCouriers = new PackagesCouriers();


            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM client");


            while (result.next()) {
                int id = result.getInt("id" );
                String name = result.getString("name" );
                String phone = result.getString("phone" );
                String address = result.getString("address");
                String passwordHash = result.getString("password_hash");
                Client c = new Client(id, name, passwordHash, phone, address) ;
                clients.getClients().add(c);
                users.getUsers().add(c);
            }

            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM admin");
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name" );
                String passwordHash = result.getString("password_hash");
                users.getUsers().add(new Admin(id, name, passwordHash));
            }

            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM courier");
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name" );
                String phone = result.getString("phone" );
                String passwordHash = result.getString("password_hash");
                users.getUsers().add(new Courier(id, name, passwordHash, phone));
            }

            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM package");
            while (result.next()) {
                int id = result.getInt("id");
                int weight = result.getInt("weigh");
                String typeOfDelivery = result.getString("type_of_delivery" );
                int senderId = result.getInt("sender_id");
                int recipientId = result.getInt("recipient_id");
                int senderCenterId = result.getInt("sender_center_id");
                int recipientCenterId = result.getInt("recipient_center_id");

                packages.getPackages().add(new Package(id, weight, typeOfDelivery, senderId, recipientId, senderCenterId, recipientCenterId));
            }

            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM package_courier");
            while (result.next()) {
                int packageId = result.getInt("package_id");
                int courierId = result.getInt("courier_id");

                packagesCouriers.getPackagesCouriers().add(new PackageCourier(packageId, courierId));
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

//            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static Link getInstance() {
        return INSTANCE;
    }

    public Clients getClients() {
        return clients;
    }

    public Users getUsers() {
        return users;
    }

    public static Connection getConnection() {
        return connection;
    }

    public Packages getPackages() {
        return packages;
    }

    public PackagesCouriers getPackagesCouriers() {
        return packagesCouriers;
    }
}