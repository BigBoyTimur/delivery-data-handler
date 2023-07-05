import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jdk.jfr.Frequency;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LinkController implements Initializable{
    @FXML
    MenuBar menuBar;
    @FXML
    Menu user;
    @FXML
    Menu data;
    @FXML
    TableView table;
    @FXML
    TextField recipientField;
    @FXML
    TextField weightField;
    @FXML
    TextField typeOfDelField;
    @FXML
    TextField sendDelCentField;
    @FXML
    Button sendBtn;
    @FXML
    Label sendTitle;
    @FXML
    Label fieldText1;
    @FXML
    Label fieldText2;
    @FXML
    Label fieldText3;
    @FXML
    Label fieldText4;
    @FXML
    Label warningLabel;
    @FXML
    Label editItemTitle;
    @FXML
    TextField editItemField;
    @FXML
    Button editItemButton;
    //FOR INSERT PackageCourier
    @FXML
    Label packageIdLabel;
    @FXML
    Label courierIdLabel;
    @FXML
    TextField packageIdField;
    @FXML
    TextField courierIdField;
    @FXML
    Button insertBtn;
    // Для удаление заказа у курьера
    @FXML
    Label deleteLabel;
    @FXML
    TextField deleteField;
    @FXML
    Button deleteBtn;
    @FXML
    Label deleteSuccess;

    private static User currentUser;
    private Parent root;
    private Scene scene;
    private Stage stage;
    private String currentTable;

    private static Clients clients;
    private static Users users;
    private static Packages packages;
    private static PackagesCouriers packagesCouriers;

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

            data.getItems().add(new MenuItem("Отправить"));
        }
        else if(currentUser.getMacLevel() == 3) {
            user.getItems().add(new MenuItem("Имя"));
            user.getItems().add(new MenuItem("Пароль"));


            data.getItems().add(new MenuItem("packages"));
            data.getItems().add(new MenuItem("package_courier"));
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } else if (currentUser.getMacLevel() == 2) {
            user.getItems().add(new MenuItem("Имя"));
            user.getItems().add(new MenuItem("Пароль"));
            user.getItems().add(new MenuItem("Телефон"));

            data.getItems().add(new MenuItem("Мои заказы"));
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
            sendBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        sendPackage(actionEvent);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            editItemButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        openEditView(actionEvent);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            insertBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        insertToPC(actionEvent);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        deleteItemPC(actionEvent);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }


        for (MenuItem menuItem: data.getItems()) {
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (currentUser.getMacLevel() == 3){
                        showDataMac3(actionEvent, menuItem);
                    } else if (currentUser.getMacLevel() == 1) {
                        showDataMac1(actionEvent, menuItem);
                    } else if (currentUser.getMacLevel() == 2) {
                        showDataMac2(actionEvent);
                    }
                }
            });
        }
//        user.getItems().get(0).setText("SSS");
    }

    private void deleteItemPC(ActionEvent actionEvent) throws SQLException {
        packagesCouriers.delete(Integer.parseInt(deleteField.getText()));
    }

    private void showDataMac2(ActionEvent actionEvent) {
        deleteLabel.setVisible(true);
        deleteField.setVisible(true);
        deleteBtn.setVisible(true);

        if (table.getColumns().size() > 0) {
            table.getColumns().clear();

            table.getItems().clear();
        }

        table.setVisible(true);
        table.getColumns().add(new TableColumn<>("package_id"));
        ((TableColumn) table.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<>("packageId"));
        table.getColumns().add(new TableColumn<>("courier_id"));
        ((TableColumn) table.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<>("CourierId"));
        for(PackageCourier packageCourier: packagesCouriers.getPackagesCouriers()) {
            if (currentUser.getId() == packageCourier.getCourierId()) {
                table.getItems().add(packageCourier);
                deleteSuccess.setVisible(true);
            }
        }

        for(Object column: table.getColumns()) {
            ((TableColumn) column).setPrefWidth(1000 / table.getColumns().size());
        }
    }

    private void insertToPC(ActionEvent actionEvent) throws SQLException {
        int packageId = Integer.parseInt(packageIdField.getText());
        int courierId = Integer.parseInt(courierIdField.getText());

        packagesCouriers.addPackageCourier(new PackageCourier(packageId, courierId));
    }

    private void sendPackage(ActionEvent actionEvent) throws SQLException {
        int id;
        if (packages.getPackages().size() > 0) id = packages.getPackages().get(packages.getPackages().size() - 1).getId() + 1;
        else id = 1;
        int weight = (int) Integer.parseInt(weightField.getText());
        String typeOfDel = typeOfDelField.getText();
        int senderId = currentUser.getId();
        int senderCenterId = Integer.parseInt(sendDelCentField.getText());
        int recipientId = 0;
        for (User user : users.getUsers()) {

            if (user.getUserName().equals(recipientField.getText())){
                recipientId = user.getId();
                Package p = new Package(id, weight, typeOfDel, senderId, recipientId, senderCenterId);
                packages.addPackage(p);
                warningLabel.setText("Успешно");
                warningLabel.setTextFill(Color.GREEN);
                warningLabel.setVisible(true);
                return;
            }
        }
        warningLabel.setText("Что-то не так");
        warningLabel.setTextFill(Color.RED);
        warningLabel.setVisible(true);

//        int recipientId = Integer.parseInt(recipientField.getText());
    }

    private void showDataMac1(ActionEvent actionEvent, MenuItem menuItem) {
        sendTitle.setVisible(true);
        recipientField.setVisible(true);
        weightField.setVisible(true);
        typeOfDelField.setVisible(true);
        sendDelCentField.setVisible(true);
        sendBtn.setVisible(true);
        fieldText1.setVisible(true);
        fieldText2.setVisible(true);
        fieldText3.setVisible(true);
        fieldText4.setVisible(true);

    }

    private void showDataMac3(ActionEvent actionEvent, MenuItem menuItem) {
        table.setVisible(true);
        editItemTitle.setVisible(false);
        editItemField.setVisible(false);
        editItemButton.setVisible(false);

        packageIdLabel.setVisible(false);
        courierIdLabel.setVisible(false);
        packageIdField.setVisible(false);
        courierIdField.setVisible(false);
        insertBtn.setVisible(false);

        if (table.getColumns().size() > 0) {
            table.getColumns().clear();

            table.getItems().clear();
        }
        switch (menuItem.getText()) {
            case "package_courier":
                packageIdLabel.setVisible(true);
                courierIdLabel.setVisible(true);
                packageIdField.setVisible(true);
                courierIdField.setVisible(true);
                insertBtn.setVisible(true);

                table.getColumns().add(new TableColumn<>("package_id"));
                ((TableColumn) table.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<>("packageId"));
                table.getColumns().add(new TableColumn<>("courier_id"));
                ((TableColumn) table.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<>("CourierId"));
                for(PackageCourier packageCourier: packagesCouriers.getPackagesCouriers()) {
                    table.getItems().add(packageCourier);
                }
                break;
            case "packages":
                editItemTitle.setVisible(true);
                editItemField.setVisible(true);
                editItemButton.setVisible(true);
                this.currentTable = "packages";
                table.getColumns().add(new TableColumn<>("id"));
                ((TableColumn) table.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<>("id"));
                table.getColumns().add(new TableColumn<>("weigh"));
                ((TableColumn) table.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<>("weight"));
                table.getColumns().add(new TableColumn<>("type_of_delivery"));
                ((TableColumn) table.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory<>("typeOfDelivery"));
                table.getColumns().add(new TableColumn<>("sender_id"));
                ((TableColumn) table.getColumns().get(3)).setCellValueFactory(new PropertyValueFactory<>("senderId"));
                table.getColumns().add(new TableColumn<>("recipient_id"));
                ((TableColumn) table.getColumns().get(4)).setCellValueFactory(new PropertyValueFactory<>("recipientId"));
                table.getColumns().add(new TableColumn<>("sender_center_id"));
                ((TableColumn) table.getColumns().get(5)).setCellValueFactory(new PropertyValueFactory<>("senderCenterId"));
                table.getColumns().add(new TableColumn<>("recipient_center_id"));
                ((TableColumn) table.getColumns().get(6)).setCellValueFactory(new PropertyValueFactory<>("recipientCenterId"));
                for(Package p: packages.getPackages()) {
                    table.getItems().add(p);
                }
                break;
        }

        for(Object column: table.getColumns()) {
            ((TableColumn) column).setPrefWidth(1000 / table.getColumns().size());
        }
    }

    public void openEditView(ActionEvent e) throws IOException {
        if (currentTable.equals("packages")) {
            PackageController.setPackages(packages);
            PackageController.setCurrentId(Integer.parseInt(editItemField.getText()));
            root = FXMLLoader.load(getClass().getResource("Package.fxml"));
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
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

    public static void setTables(Users users, Clients clients, Packages packages, PackagesCouriers packagesCouriers) {
        LinkController.users = users;
        LinkController.clients = clients;
        LinkController.packages = packages;
        LinkController.packagesCouriers = packagesCouriers;
    }


}
