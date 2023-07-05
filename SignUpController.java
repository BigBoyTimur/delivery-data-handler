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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button regBtn;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField passField;
    @FXML
    private Label nameWarningLabel;
    static private Clients clients;
    private Parent root;
    private Scene scene;
    private Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        enterBtn.add
        regBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    reg(actionEvent);
                } catch (NoSuchAlgorithmException | IOException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public static void setClients(Clients clients) {
        SignUpController.clients = clients;
    }

    public void reg(ActionEvent e) throws IOException, NoSuchAlgorithmException, SQLException {
        SignUp up = new SignUp(usernameField.getText(), phoneField.getText(), addressField.getText(), User.toHash(passField.getText()));
        if (up.checkUserName(clients)) {
            up.addClient(clients);
            root = FXMLLoader.load(getClass().getResource("LinkScene.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            nameWarningLabel.setVisible(true);
        }
    }
}
