import java.sql.SQLException;
import java.sql.Statement;

public class Client extends User{
    private int id;
    private String phone;
    private String address;


    public Client(int id, String userName, String passwordHash, String phone, String address) {
        this.id = id;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.address = address;
        this.macLevel = 1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "UPDATE client SET address = \"" + address + "\" WHERE id = " + getId() + ";";
        statement.executeUpdate(sql);
        this.address = address;
    }

    public void setPhone(String phone) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "UPDATE client SET phone = \"" + phone + "\" WHERE id = " + getId() + ";";
        statement.executeUpdate(sql);
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
