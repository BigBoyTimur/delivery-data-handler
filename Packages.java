import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class Packages {
    ArrayList<Package> packages;
    Connection connection;
    public Packages() {
        packages = new ArrayList<Package>();
        connection = Link.getConnection();
    }

    public void addPackage(Package p) throws SQLException {
        packages.add(p);
        String sql = ("INSERT INTO package (id, weigh, type_of_delivery, sender_id, recipient_id, sender_center_id, recipient_center_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, p.getId());
        statement.setInt(2, p.getWeight());
        statement.setString(3, p.getTypeOfDelivery());
        statement.setInt(4, p.getSenderId());
        statement.setInt(5, p.getRecipientId());
        statement.setInt(6, p.getSenderCenterId());
        statement.setInt(7, p.getSenderCenterId());
        statement.executeUpdate();
    }


    public ArrayList<Package> getPackages() {
        return packages;
    }
}
