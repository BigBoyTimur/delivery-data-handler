import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class SignIn {
    private String name;
    private String pass;
    public SignIn(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public boolean checkCorrect(ArrayList<Client> clients) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        for (Client client : clients) {
            if (client.userName.equals(this.name) && client.passwordHash.equals(User.toHash(pass))) {
                return true;
            }
        }
        return false;
    }
}
