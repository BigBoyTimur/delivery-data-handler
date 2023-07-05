import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));

        Scene signInScene = new Scene(root);

        primaryStage.setTitle("App");
        primaryStage.setScene(signInScene);
        primaryStage.show();

//        User u = new User();
        Link link = Link.getInstance();

        SignInController.setUsers(link.getUsers().getUsers());
        SignUpController.setClients(link.getClients());
        System.out.println(User.toHash("admin"));
    }
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        launch(args);

    }
}
