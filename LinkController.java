import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LinkController implements Initializable{
    @FXML
    MenuBar menuBar;
    @FXML
    Menu user;
    @FXML
    Menu data;

    private static User currentUser;
    private Parent root;
    private Scene scene;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (currentUser.getMacLevel() == 1) {
//            MenuItem userName = new MenuItem("Имя");
//            MenuItem password = new MenuItem("Пароль");
//            MenuItem phone = new MenuItem("Телефон");
//            MenuItem address = new MenuItem("Адрес");
            user.getItems().add(new MenuItem("Имя"));
            user.getItems().add(new MenuItem("Пароль"));
            user.getItems().add(new MenuItem("Телефон"));
            user.getItems().add(new MenuItem("Адрес"));
        }
        else if(currentUser.getMacLevel() == 3) {
            user.getItems().add(new MenuItem("Имя"));
            user.getItems().add(new MenuItem("Пароль"));
        }
        for (MenuItem menuItem: user.getItems()) {
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        changeUserField(actionEvent, menuItem);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
//        user.getItems().get(0).setText("SSS");
    }
    public static void setCurrentUser(User currentUser) {
        LinkController.currentUser = currentUser;
    }

    public void changeUserField(ActionEvent e, MenuItem menuItem) throws IOException {
        UserController.setField(menuItem.getText());
        UserController.setCurrentUser(currentUser);
        root = FXMLLoader.load(getClass().getResource("User.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
