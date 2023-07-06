import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SignInScene {
    Parent root;
    Scene signInScene;


    public SignInScene() throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxmlFiles/SignIn.fxml"));
        signInScene = new Scene(root);
    }

    public Scene getSignInScene() {
        return signInScene;
    }
}
