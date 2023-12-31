import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button enterBtn;
    @FXML
    private Button signUpBtn;
    @FXML
    private Label incorrectLabel;

    private Parent root;
    private Scene scene;
    private Stage stage;

    static private ArrayList<User> users;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        enterBtn.add
        enterBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    enter(actionEvent);
                } catch (NoSuchAlgorithmException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    switchToSignUp(actionEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void setUsers(ArrayList<User> users) {
        SignInController.users = users;
    }

    public void enter(ActionEvent e) throws IOException, NoSuchAlgorithmException {
        SignIn in = new SignIn(usernameField.getText(), passwordField.getText());
        if (in.checkCorrect(users)) {
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = (new LinkScene()).getLinkScene();
            stage.setScene(scene);
            stage.show();
        }
        else {
            incorrectLabel.setVisible(true);
        }
    }

    private void switchToSignUp(ActionEvent e) throws IOException {
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = (new SignUpScene()).getSignUpScene();
        stage.setScene(scene);
        stage.show();
    }
}
