package project.managers.Page;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.gameStuff.GameData;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;

public class SceneManager {
    private static SceneManager instance;
    private HashMap<PageType, String> fxmlPaths = new HashMap<>();

    public SceneManager() {
        fxmlPaths.put(PageType.StartPage, "/fxmls/startPage.fxml");
        fxmlPaths.put(PageType.HomePage, "/fxmls/homePage.fxml");
        fxmlPaths.put(PageType.SignupPage, "/fxmls/signupPage.fxml");
        fxmlPaths.put(PageType.LoginPage, "/fxmls/loginPage.fxml");
        fxmlPaths.put(PageType.HardnessPage, "/fxmls/chooseHardness.fxml");
        fxmlPaths.put(PageType.ProfilePage, "/fxmls/profile.fxml");
        fxmlPaths.put(PageType.ShopPage, "/fxmls/Shop.fxml");
        fxmlPaths.put(PageType.HistoryPage, "history.fxml");
        fxmlPaths.put(PageType.WorldPage, "/fxmls/worlds.fxml");
        fxmlPaths.put(PageType.GameOver,"/fxmls/gameover.fxml");
    }

    public static SceneManager getInstance() {
        if (instance == null)
            instance = new SceneManager();
        return instance;
    }

    public void goToScene(Stage stage, PageType type) {
        try {
            String path = fxmlPaths.get(type);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 400);
            GameData.getInstance().setScene(scene);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goToScene(Stage stage, Group root) {
        Scene scene = new Scene(root, 800, 400);
        GameData.getInstance().setScene(scene);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
