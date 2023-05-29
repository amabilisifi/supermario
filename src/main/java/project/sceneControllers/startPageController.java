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
import project.Characters.Antonio;
import project.Characters.Lorenzo;
import project.Characters.Alexandro;
import project.ConfigManager;
import project.JsonManager;
import project.User;
import project.UserData;
import project.gameObjects.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class startPageController implements Initializable {
    @FXML
    Pane pane;

    JsonManager manager = new JsonManager("src/main/resources/usera.json");
    public void goToLoginPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/loginPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 400);
        stage.setScene(scene);

        Text text = new Text(50, 270, "");
        text.setFont(new Font(20));
        pane.getChildren().add(text);

        stage.setResizable(false);
        stage.show();
    }
    public void goToSignupPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/signupPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 400);
        stage.setScene(scene);

        Text text = new Text(50, 270, "");
        text.setFont(new Font(20));
        pane.getChildren().add(text);

        stage.setResizable(false);
        stage.show();
    }
    public void exit() throws IOException{
        manager.writeArray(UserData.getInstance().getUsers());
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ConfigManager manager = new ConfigManager("src/main/java/project/gameObjects/objectsInfo.properties");
            Block.setHeight(manager.getInt("blockHeight"));
            Block.setWidth(manager.getInt("blockWidth"));
            Coin.setHeight(manager.getInt("coinHeight"));
            Coin.setWidth(manager.getInt("coinWidth"));
            Pipe.setWidth(manager.getInt("pipeWidth"));
            Pipe.setHeightShort(manager.getInt("shortPipeHeight"));
            Pipe.setHeightMedium(manager.getInt("mediumPipeHeight"));
            Pipe.setHeightLong(manager.getInt("longPipeHeight"));

            ConfigManager configManager = new ConfigManager("src/main/java/project/Characters/characterInfo.properties");
            Alexandro.setPrice(configManager.getInt("alexandroPrice"));
            Antonio.setPrice(configManager.getInt("antonioPrice"));
            Lorenzo.setPrice(configManager.getInt("patrickPrice"));
           // KrustyKrab.setPrice(configManager.getInt("krustyKrabPrice"));
            Alexandro.setWidth(configManager.getInt("characterWidth"));
            Antonio.setWidth(configManager.getInt("characterWidth"));
            Lorenzo.setWidth(configManager.getInt("characterWidth"));
           // KrustyKrab.setWidth(configManager.getInt("characterWidth"));
            Alexandro.setHeight(Block.getHeight()*2);
            Antonio.setHeight(Block.getHeight()*2);
            Lorenzo.setHeight(Block.getHeight()*2);
           // KrustyKrab.setHeight(Block.getHeight()*2);
            Alexandro.setSpeedo(configManager.getInt("alexandroSpeedo"));
            Lorenzo.setSpeedo(configManager.getInt("lorenzoSpeedo"));
            Antonio.setSpeedo(configManager.getInt("antonioSpeedo"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goArta(ActionEvent event) throws IOException {
        User u = User.userOf("arta");
        UserData.getInstance().setCurrentUser(u);
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxmls/homePage.fxml"));
        Parent root = homeLoader.load();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        String path = "src/main/resources/GameData/"+u.getName()+"/Inventory/purchasedCharacters.json";
        JsonManager manager = new JsonManager(path);
        u.setPurchasedCharacters(manager.readArray(JsonManager.characterTypeReference));
        u.getPurchasedCharacters().add(u.getFreeChar());

        stage.setResizable(false);
        stage.show();
    }
}
