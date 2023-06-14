package project.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.managers.Page.PageType;
import project.managers.Page.SceneManager;

import java.io.IOException;

public class hardnessController {
    public void easyGame(ActionEvent event) throws IOException {
    }

    public void normalGame(ActionEvent event) throws IOException {
    }

    public void hardGame(ActionEvent event) throws IOException {
    }

    public void goHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.HomePage);
    }
}
