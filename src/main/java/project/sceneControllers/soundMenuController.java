package project.sceneControllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.JsonManager;
import project.UserData;
import project.otherStuff.SoundPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class soundMenuController implements Initializable {
    @FXML
    Slider volumeSlider;
    @FXML
    CheckBox muteCheckBox;
    private Timeline timeline  ;
    public void home(ActionEvent event){
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
    public void continueGame(){
        System.out.println("close to continue game");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline = new Timeline(new KeyFrame(Duration.millis(200),e->{
            double value =  volumeSlider.getValue();
            SoundPlayer.getMainSoundPlayer().setVolume(value);

            if(muteCheckBox.isSelected())
                SoundPlayer.getMainSoundPlayer().mute();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }
}
