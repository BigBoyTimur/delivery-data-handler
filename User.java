import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    protected int id;
    protected String userName;
    protected String passwordHash;
    protected int macLevel;
    protected Connection connection = Link.getConnection();

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) throws SQLException {
        String sql = null;
        PreparedStatement statement = null;
        if (getMacLevel() == 1) {
//            sql = "UPDATE client SET password_hash = \"" + passwordHash + "\" WHERE id = " + getId() + ";";
            sql = "UPDATE client SET password_hash = ? WHERE id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, passwordHash);
            statement.setInt(2, getId());
        }
        else if(getMacLevel() == 3) {
//            sql = "UPDATE admin SET password_hash = \"" + passwordHash + "\" WHERE id = " + getId() + ";";
            sql = "UPDATE admin SET password_hash = ? WHERE id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, passwordHash);
            statement.setInt(2, getId());
        } else if (getMacLevel() == 2) {
//            sql = "UPDATE courier SET password_hash = \"" + passwordHash + "\" WHERE id = " + getId() + ";";
            sql = "UPDATE courier SET password_hash = ? WHERE id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, passwordHash);
            statement.setInt(2, getId());
        }
        statement.executeUpdate();

        this.passwordHash = passwordHash;

    }

    public void setUserName(String userName) throws SQLException {
        String sql = null;
        PreparedStatement statement = null;
        if (getMacLevel() == 1) {
//            sql = "UPDATE client SET name = \"" + userName + "\" WHERE id = " + getId() + ";";
            sql = "UPDATE client SET name = ? WHERE id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setInt(2, getId());
        }
        else if(getMacLevel() == 3) {
//            sql = "UPDATE admin SET name = \"" + userName + "\" WHERE id = " + getId() + ";";
            sql = "UPDATE admin SET name = ? WHERE id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setInt(2, getId());
        } else if (getMacLevel() == 2) {
//            sql = "UPDATE courier SET name = \"" + userName + "\" WHERE id = " + getId() + ";";
            sql = "UPDATE courier SET name = ? WHERE id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setInt(2, getId());
        }
        statement.executeUpdate();
        this.userName = userName;
    }

    public static String toHash(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuilder hash = new StringBuilder();
        byte[] bytesOfMessage = s.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] theMD5digest = md.digest(bytesOfMessage);
        for(byte b : theMD5digest) hash.append(b);
        return hash.toString();
    }

    public int getMacLevel() {
        return macLevel;
    }

    public int getId() {
        return id;
    }
}
