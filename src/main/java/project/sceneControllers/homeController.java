package project.sceneControllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.User;
import project.UserData;

import java.io.IOException;

public class homeController {
    User currentUser = UserData.getInstance().getCurrentUser();

    public void goToHardnessScene(ActionEvent event) throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxmls/chooseHardness.fxml"));
        Parent root = homeLoader.load();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }

    public void goToProfileScene(MouseEvent event) throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxmls/profile.fxml"));
        Parent root = homeLoader.load();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }

    public void goToStart(ActionEvent event) throws IOException {
        UserData.getInstance().setCurrentUser(null);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/startPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }

    public void goToShop(ActionEvent event) throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxmls/Shop.fxml"));
        Parent root = homeLoader.load();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }

    public void dingDong(MouseEvent event) throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxmls/game.fxml"));
        Parent root = homeLoader.load();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }

    public void goToHistoryScene(ActionEvent event) throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("history.fxml"));
        Parent root = homeLoader.load();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }
}