package project;

import javafx.application.Application;
import javafx.stage.Stage;
import project.gameStuff.GameData;
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
                if(GameData.getInstance().getScore()>=UsersData.getInstance().getCurrentUser().getHighScore()){
                    UsersData.getInstance().getCurrentUser().setHighScore(GameData.getInstance().getScore());
                }
                manager.writeArray(UsersData.getInstance().getUsers());
                System.exit(0);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        GameData.getInstance().setStage(stage);
        SceneManager.getInstance().goToScene(stage, PageType.StartPage);

        try {
            UsersData.getInstance().setUsers(manager.readArray(JsonManager.userTypeReference));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
