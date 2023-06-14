package project.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.managers.JsonManager;
import project.UsersData;
import project.managers.Page.PageType;
import project.managers.Page.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class historyController implements Initializable {
    @FXML
    Pane pane;
    JsonManager managerI;
    JsonManager managerII;
    JsonManager managerIII;
    String filePath = "src/main/resources/com/example/fx_guii/GameData/" + UsersData.getInstance().getCurrentUser().getName() + "/LoadData/load";
    // +num.jason
    int loadCount = UsersData.getInstance().getCurrentUser().getSavedLoadCount() - 1;
    @FXML
    Text text;

    public void goHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.HomePage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        text.setText("");
        if (loadCount >= 3) {
            managerIII = new JsonManager(filePath + loadCount + ".json");
            managerII = new JsonManager(filePath + (loadCount - 1) + ".json");
            managerI = new JsonManager(filePath + (loadCount - 2) + ".json");
        } else if (loadCount == 2) {
            managerII = new JsonManager(filePath + (loadCount) + ".json");
            managerI = new JsonManager(filePath + (loadCount - 1) + ".json");
        } else if (loadCount == 1) {
            managerI = new JsonManager(filePath + (loadCount) + ".json");
        }
    }

    public void loadI(ActionEvent event) throws IOException {
        text.setText("");
        if (managerI == null) {
            text.setText("there is no game saved in this file.");
        } else {
            //GameData gameI = managerI.readObject(GameData.class);
            //UserData.getInstance().setCurrentGameData(gameI);
//            level1part1 game = new level1part1();
//            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/fxmls/level1part1.fxml"));
//            Parent root = Loader.load();
//            Scene scene = new Scene(root, 800, 400);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            Pane pane1 = new Pane();
//            root.getChildrenUnmodifiable().add(pane1);
//            gameI.createScene(pane1);
//
//            stage.show();
        }
    }

    public void loadII() throws IOException {
        text.setText("");
        if (managerII == null) {
            text.setText("there is no game saved in this file.");
        } else {
            //GameData gameII = managerII.readObject(GameData.class);
            //UserData.getInstance().setCurrentGameData(gameII);
        }
    }

    public void loadIII() throws IOException {
        text.setText("");
        if (managerIII == null) {
            text.setText("there is no game saved in this file.");
        } else {
            //GameData gameIII = managerIII.readObject(GameData.class);
            //UserData.getInstance().setCurrentGameData(gameIII);
        }
    }
}
