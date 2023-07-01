package project.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.UsersData;
import project.gameStuff.GameData;
import project.gameStuff.Level;
import project.gameStuff.Section;
import project.gameStuff.SectionDesigner;
import project.levels.level1;
import project.levels.temp;
import project.managers.Page.PageType;
import project.managers.Page.SceneManager;
import project.managers.SoundPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeController implements Initializable {
    private SoundPlayer soundPlayer;

    public void goToHardnessScene(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.HardnessPage);
        soundPlayer.stop();
    }

    public void goToProfileScene(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.ProfilePage);
    }

    public void goToStart(ActionEvent event) throws IOException {
        UsersData.getInstance().setCurrentUser(null);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.StartPage);
        soundPlayer.stop();
    }

    public void goToShop(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.ShopPage);
    }

    public void dingDong(MouseEvent event) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        GameData.getInstance().setRoot(root);
        GameData.getInstance().setScene(scene);

        Level temp = new temp();
        Section t = temp.getSections().get(0);
        GameData.getInstance().setCurrentLevel(temp);
        GameData.getInstance().setCurrentSection(t);
        soundPlayer.mute();
        soundPlayer.stop();


        SectionDesigner.getInstance().paint(t);

        stage.setResizable(false);
        stage.show();
    }

    public void goToHistoryScene(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.HistoryPage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        soundPlayer = new SoundPlayer("src/main/resources/media/mario.mp3");
        SoundPlayer.setMainSoundPlayer(soundPlayer);
        soundPlayer.play();
        soundPlayer.playOnRepeat();
    }
}