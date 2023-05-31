package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.otherStuff.SoundPlayer;

import java.io.IOException;

public class StartPage extends Application {
    JsonManager manager = new JsonManager("src/main/resources/usera.json");

    @Override
    public void start(Stage stage) throws Exception {
        stage.setOnCloseRequest(e -> {
            try {
                manager.writeArray(UserData.getInstance().getUsers());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/startPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 400);
        stage.setScene(scene);

        try {
            UserData.getInstance().setUsers(manager.readArray(JsonManager.userTypeReference));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.show();

        SoundPlayer soundPlayer = new SoundPlayer("./src/main/resources/media/Magentium - Among Us Theme.mp3");
        //soundPlayer.play();
    }
}
