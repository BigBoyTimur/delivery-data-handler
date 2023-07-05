import java.sql.PreparedStatement;
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
        String sql = "UPDATE courier SET phone = ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, phone);
        statement.setInt(2, getId());
        statement.executeUpdate();
        this.phone = phone;
    }
}
