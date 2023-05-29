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
import project.Characters.Alexandro;
import project.JsonManager;
import project.User;
import project.UserData;

import java.io.IOException;

public class signupController {
    @FXML
    Pane pane;
    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    Text text = new Text(50, 270, "");

    public void signupSubmit(ActionEvent event) throws IOException {
        String enteredUsername = usernameField.getText();
        String enteredPassword = passwordField.getText();
        if (enteredUsername.length() < 3) {
            pane.getChildren().remove(text);
            text.setText("user name must have at least 3 characters");
            pane.getChildren().add(text);
            usernameField.clear();
            passwordField.clear();
        } else {
            if (User.userExist(enteredUsername)) {
                pane.getChildren().remove(text);
                text.setText("username already exist");
                pane.getChildren().add(text);
                usernameField.clear();
                passwordField.clear();
            } else {
                User u = new User(enteredUsername, enteredPassword);
                UserData.getInstance().setCurrentUser(u);
                FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxmls/homePage.fxml"));
                Parent root = homeLoader.load();
                Scene scene = new Scene(root, 800, 400);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);

                String path = "src/main/resources/GameData/"+u.getName()+"/Inventory/purchasedCharacters.json";
                JsonManager manager = new JsonManager(path);
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
