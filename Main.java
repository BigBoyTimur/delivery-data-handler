import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));

        Scene signInScene = new Scene(root);

        primaryStage.setTitle("App");
        primaryStage.setScene(signInScene);
        primaryStage.show();

//        User u = new User();
        Link link = new Link();

        SignInController.setClients(link.getClients().getClients());

    }
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        launch(args);

    }
}
