import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class LinkScene {
    Parent root;
    Scene linkScene;


    public LinkScene() throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxmlFiles/LinkScene.fxml"));
        linkScene = new Scene(root);
    }

    public Scene getLinkScene() {
        return linkScene;
    }
}
