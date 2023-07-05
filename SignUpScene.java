import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SignUpScene {
    Parent root;
    Scene signUpScene;


    public SignUpScene() throws IOException {
        root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        signUpScene = new Scene(root);
    }

    public Scene getSignUpScene() {
        return signUpScene;
    }
}
