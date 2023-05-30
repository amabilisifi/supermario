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
import project.*;
import project.Characters.Antonio;
import project.Characters.Lorenzo;
import project.Characters.Alexandro;
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
