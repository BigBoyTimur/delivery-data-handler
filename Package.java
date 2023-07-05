import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Package {
    private int id, weight;
    private String typeOfDelivery;
    private int senderId;
    private int recipientId;
    private int senderCenterId;
    private int recipientCenterId;
    private Connection connection = Link.getConnection();

    public Package(int id, int weigh, String typeOfDelivery, int senderId, int recipientId, int senderCenterId) {
        this.id = id;
        this.weight = weigh;
        this.typeOfDelivery = typeOfDelivery;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.senderCenterId = senderCenterId;
    }
    public Package(int id, int weigh, String typeOfDelivery, int senderId, int recipientId, int senderCenterId, int recipientCenterId) {
        this.id = id;
        this.weight = weigh;
        this.typeOfDelivery = typeOfDelivery;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.senderCenterId = senderCenterId;
        this.recipientCenterId = recipientCenterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) throws SQLException {
        this.weight = weight;
        String sql = "UPDATE package SET weigh = ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, weight);
        statement.setInt(2, getId());

        statement.executeUpdate();
    }

    public String getTypeOfDelivery() {
        return typeOfDelivery;
    }

    public void setTypeOfDelivery(String typeOfDelivery) throws SQLException {
        this.typeOfDelivery = typeOfDelivery;
        String sql = "UPDATE package SET type_of_delivery = ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, typeOfDelivery);
        statement.setInt(2, getId());

        statement.executeUpdate();
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) throws SQLException {
        this.senderId = senderId;
        String sql = "UPDATE package SET sender_id = ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, senderId);
        statement.setInt(2, getId());

        statement.executeUpdate();
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) throws SQLException {
        this.recipientId = recipientId;
        String sql = "UPDATE package SET recipient_id = ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, recipientId);
        statement.setInt(2, getId());

        statement.executeUpdate();
    }

    public int getSenderCenterId() {
        return senderCenterId;
    }

    public void setSenderCenterId(int senderCenterId) throws SQLException {
        this.senderCenterId = senderCenterId;
        String sql = "UPDATE package SET sender_center_id = ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, senderCenterId);
        statement.setInt(2, getId());

        statement.executeUpdate();
    }

    public int getRecipientCenterId() {
        return recipientCenterId;
    }

    public void setRecipientCenterId(int recipientCenterId) throws SQLException {
        this.recipientCenterId = recipientCenterId;
        String sql = "UPDATE package SET recipient_center_id = ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, recipientCenterId);
        statement.setInt(2, getId());

        statement.executeUpdate();
    }
}
