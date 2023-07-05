import java.sql.*;
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
//        String sql = ("INSERT INTO client (id, name, phone, address, mac_level, password_hash) VALUES ('" +
//                client.getId() + "', '" + client.getUserName() + "', '" + client.getPhone() + "', '" + client.getAddress() + "', '1', '" +
//                client.getPasswordHash() + "');");
        String sql = ("INSERT INTO client (id, name, phone, address, mac_level, password_hash) VALUES (?, ?, ?, ?, ?, ?)");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, client.getId());
        statement.setString(2, client.getUserName());
        statement.setString(3, client.getPhone());
        statement.setString(4, client.getAddress());
        statement.setInt(5, client.getMacLevel());
        statement.setString(6, client.getPasswordHash());

//        System.out.println(sql);

        statement.executeUpdate();

    }
}
