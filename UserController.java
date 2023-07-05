import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jdk.jfr.Frequency;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    static String field; //изменяемое поле
    private static User currentUser;

    @FXML
    Label title;
    @FXML
    TextField textField;
    @FXML
    Button btn;
    @FXML
    Label done;
    @FXML
    Label value;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setText(field);
        switch (field) {
            case "Имя": value.setText(currentUser.getUserName());
                break;
            case "Телефон": value.setText((((Client) currentUser).getPhone()));
                break;
            case "Адрес": value.setText((((Client) currentUser).getAddress()));
                break;
            default: value.setText("Невозможно для пароля");
        }
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    updateField();
                } catch (UnsupportedEncodingException | NoSuchAlgorithmException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public static void setField(String field) {
        UserController.field = field;
    }

    public void updateField() throws UnsupportedEncodingException, NoSuchAlgorithmException, SQLException {
        switch (field) {
            case "Имя": currentUser.setUserName(textField.getText());
                value.setText(currentUser.getUserName());
                break;
            case "Пароль": currentUser.setPasswordHash(User.toHash(textField.getText()));
                value.setText("Невозможно для пароля");
                break;
            case "Телефон": ((Client) currentUser).setPhone(textField.getText());
                value.setText((((Client) currentUser).getPhone()));
                break;
            case "Адрес": ((Client) currentUser).setAddress(textField.getText());
                value.setText((((Client) currentUser).getAddress()));
                break;
        }
        done.setVisible(true);
    }

    public static void setCurrentUser(User currentUser) {
        UserController.currentUser = currentUser;
    }
}
