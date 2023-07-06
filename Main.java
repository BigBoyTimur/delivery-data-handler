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
        Scene signInScene = (new SignInScene()).getSignInScene();


        primaryStage.setTitle("App");
        primaryStage.setScene(signInScene);
        primaryStage.show();

//        User u = new User();
        Link link = Link.getInstance();

        SignInController.setUsers(link.getUsers().getUsers());
        SignUpController.setClients(link.getClients());
        LinkController.setTables(link.getUsers(), link.getClients(), link.getPackages(), link.getPackagesCouriers());

    }
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        launch(args);

    }
}
