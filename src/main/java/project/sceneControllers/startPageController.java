package project.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.gameStuff.GameData;
import project.managers.JsonManager;
import project.User;
import project.UsersData;
import project.managers.Page.PageType;
import project.managers.Page.SceneManager;
import project.managers.SoundPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class startPageController implements Initializable {
    @FXML
    Pane pane;

    JsonManager manager = new JsonManager("src/main/resources/usera.json");
    private SoundPlayer soundPlayer = new SoundPlayer("src/main/resources/media/Hans Zimmer-Batman Rises.mp3");


    public void goToLoginPage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.LoginPage);

        Text text = new Text(50, 270, "");
        text.setFont(new Font(20));
        pane.getChildren().add(text);
        soundPlayer.stop();

        stage.setResizable(false);
        stage.show();
    }

    public void goToSignupPage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.SignupPage);

        Text text = new Text(50, 270, "");
        text.setFont(new Font(20));
        pane.getChildren().add(text);
        soundPlayer.stop();

        stage.setResizable(false);
        stage.show();
    }

    public void exit() throws IOException {
        manager.writeArray(UsersData.getInstance().getUsers());
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SoundPlayer.setMainSoundPlayer(soundPlayer);
        soundPlayer.play();
        soundPlayer.playOnRepeat();
    }
}
