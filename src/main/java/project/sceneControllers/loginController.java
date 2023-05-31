package project.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.JsonManager;
import project.User;
import project.UserData;

import java.io.IOException;

public class loginController {
    @FXML
    Pane pane;
    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    Text text = new Text(50, 270, "");

    public void loginSubmit(ActionEvent event) throws IOException {
        String enteredUsername = usernameField.getText();
        String enteredPassword = passwordField.getText();

        if (!User.userExist(enteredUsername)) {
            pane.getChildren().remove(text);
            text.setText("there is no account with this user name");
            pane.getChildren().add(text);
            usernameField.clear();
            passwordField.clear();
        } else {
            if (!User.checkPassword(enteredUsername, enteredPassword)) {
                pane.getChildren().remove(text);
                text.setText("wrong password");
                pane.getChildren().add(text);
                passwordField.clear();
            } else {
                User u = User.userOf(enteredUsername);
                UserData.getInstance().setCurrentUser(u);
                FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxmls/homePage.fxml"));
                Parent root = homeLoader.load();
                Scene scene = new Scene(root, 800, 400);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);

                String path = "src/main/resources/GameData/" + u.getName() + "/Inventory/purchasedCharacters.json";
                JsonManager manager = new JsonManager(path);
                u.setPurchasedCharacters(manager.readArray(JsonManager.characterTypeReference));
                u.getPurchasedCharacters().add(u.getFreeChar());

                stage.setResizable(false);
                stage.show();
            }
        }
    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxmls/startPage.fxml"));
        Parent root = homeLoader.load();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }
}
