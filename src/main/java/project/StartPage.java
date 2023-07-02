package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.gameStuff.GameData;
import project.gameStuff.SectionDesigner;
import project.managers.JsonManager;
import project.managers.Page.PageType;
import project.managers.Page.SceneManager;

import java.io.IOException;

public class StartPage extends Application {
    JsonManager manager = new JsonManager("src/main/resources/usera.json");

    @Override
    public void start(Stage stage) throws Exception {
        stage.setOnCloseRequest(e -> {
            try {
                manager.writeArray(UsersData.getInstance().getUsers());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
//        SectionDesigner.getInstance().setStage(stage);
        GameData.getInstance().setStage(stage);
        SceneManager.getInstance().goToScene(stage, PageType.StartPage);

        try {
            UsersData.getInstance().setUsers(manager.readArray(JsonManager.userTypeReference));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
