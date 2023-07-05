import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Clients {
    ArrayList<Client> clients;
    Connection connection;
    public Clients(Connection connection) {
        this.connection = connection;
        this.clients = new ArrayList<Client>();
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) throws SQLException {
        clients.add(client);
        Statement statement = connection.createStatement();
        String sql = ("INSERT INTO client (id, name, phone, address, mac_level, password_hash) VALUES ('" +
                client.getId() + "', '" + client.getUserName() + "', '" + client.getPhone() + "', '" + client.getAddress() + "', '1', '" +
                client.getPasswordHash() + "');");

//        System.out.println(sql);

        statement.executeUpdate(sql);

    }
}
