import java.sql.SQLException;
import java.sql.Statement;

public class Courier extends User{
    private String phone;
    public Courier(int id, String userName, String passwordHash, String phone) {
        this.id = id;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.macLevel = 2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "UPDATE courier SET phone = \"" + phone + "\" WHERE id = " + getId() + ";";
        statement.executeUpdate(sql);
        this.phone = phone;
    }
}
