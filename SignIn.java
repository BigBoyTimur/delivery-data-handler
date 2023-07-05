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

    public boolean checkCorrect(ArrayList<User> users) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        for (User user : users) {
            if (user.userName.equals(this.name) && user.passwordHash.equals(User.toHash(pass))) {
                LinkController.setCurrentUser(user);
                return true;
            }
        }
        return false;
    }
}
