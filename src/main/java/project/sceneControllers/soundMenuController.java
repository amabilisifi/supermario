package project.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import project.JsonManager;
import project.UserData;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class soundMenuController {
    @FXML
    Slider soundSlider;
    @FXML
    RadioButton muteRadioButton;
    public static void home(MouseEvent event){
        String path = "src/main/resources/GameData/" + UserData.getInstance().getCurrentUser().getName() + "/Inventory/purchasedCharacters.json";
        JsonManager manager = new JsonManager(path);
        try {
            manager.writeArray(UserData.getInstance().getCurrentUser().getPurchasedCharacters());
            FXMLLoader homeLoader = new FXMLLoader(soundMenuController.class.getResource("/fxmls/homePage.fxml"));
            Parent root = homeLoader.load();
            Scene scene = new Scene(root, 800, 400);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
