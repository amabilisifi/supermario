package project.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.UsersData;
import project.gameStuff.GameData;
import project.gameStuff.Section;
import project.gameStuff.SectionDesigner;
import project.managers.JsonManager;
import project.managers.Page.PageType;
import project.managers.Page.SceneManager;
import project.managers.SoundPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeController implements Initializable {
    private SoundPlayer soundPlayer;

    public void goToHardnessScene() {
        JsonManager manager = new JsonManager("src/main/resources/test.Json");
        try {
            Section section = manager.readObject(Section.class);
            SectionDesigner sectionDesigner = new SectionDesigner(new Group(),section);
            sectionDesigner.paint(section);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToProfileScene(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.ProfilePage);
    }

    public void goToStart(ActionEvent event) {
        UsersData.getInstance().setCurrentUser(null);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.StartPage);
        soundPlayer.stop();
    }

    public void goToShop(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.ShopPage);
    }
    public void goToHistoryScene(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.HistoryPage);
    }
    public void goToWorldList(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.WorldPage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        soundPlayer = new SoundPlayer("src/main/resources/media/the batman-ben afflec.mp3");
        SoundPlayer.setMainSoundPlayer(soundPlayer);
        soundPlayer.play();
        soundPlayer.playOnRepeat();
        GameData.getInstance().setCurrentSoundPlayer(soundPlayer);
    }
}