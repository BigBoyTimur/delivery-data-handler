import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PackageController implements Initializable {
    @FXML
    TextField weightField;
    @FXML
    TextField typeOfDeliveryField;
    @FXML
    TextField senderIdField;
    @FXML
    TextField recipientIdField;
    @FXML
    TextField senderCenterIdField;
    @FXML
    TextField recipientCenterIdField;
    @FXML
    Button btn;
    @FXML
    Label succesLabel;

    private static Packages packages;
    private static int currentId;

    private Package currentPackage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Package p : packages.getPackages()) {
            if (p.getId() == currentId) {
                currentPackage = p;
                break;
            }

        }
        weightField.setText(Integer.toString(currentPackage.getWeight()));
        typeOfDeliveryField.setText(currentPackage.getTypeOfDelivery());
        senderIdField.setText(Integer.toString(currentPackage.getSenderId()));
        recipientIdField.setText(Integer.toString(currentPackage.getRecipientId()));
        senderCenterIdField.setText(Integer.toString(currentPackage.getSenderCenterId()));
        recipientCenterIdField.setText(Integer.toString(currentPackage.getRecipientCenterId()));

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    updatePackage(actionEvent);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void updatePackage(ActionEvent actionEvent) throws SQLException {
        currentPackage.setWeight(Integer.parseInt(weightField.getText()));
        currentPackage.setTypeOfDelivery(typeOfDeliveryField.getText());
        currentPackage.setSenderId(Integer.parseInt(senderIdField.getText()));
        currentPackage.setRecipientId(Integer.parseInt(recipientIdField.getText()));
        currentPackage.setSenderCenterId(Integer.parseInt(senderCenterIdField.getText()));
        currentPackage.setRecipientCenterId(Integer.parseInt(recipientCenterIdField.getText()));
        succesLabel.setVisible(true);
    }

    public static void setPackages(Packages packages) {
        PackageController.packages = packages;
    }

    public static void setCurrentId(int currentId) {
        PackageController.currentId = currentId;
    }
}
