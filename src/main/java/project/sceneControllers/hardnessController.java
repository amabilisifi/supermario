package project.sceneControllers;

import project.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class hardnessController {
    public void easyGame(ActionEvent event) throws IOException {
    }
    public void normalGame(ActionEvent event) throws IOException {
    }
    public void hardGame(ActionEvent event) throws IOException {
    }
    public void goHome(ActionEvent event) throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxmls/homePage.fxml"));
        Parent root = homeLoader.load();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.show();
    }
}
