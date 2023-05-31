package project.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.UserData;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class profileController implements Initializable {
    @FXML
    Text usernameText;
    @FXML
    Text highestScoreText;
    @FXML
    Text coinText;

    public void goHome(ActionEvent event) throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxmls/homePage.fxml"));
        Parent root = homeLoader.load();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameText.setText(UserData.getInstance().getCurrentUser().getName());
        highestScoreText.setText(String.valueOf(UserData.getInstance().getCurrentUser().getHighScore()));
        coinText.setText(String.valueOf(UserData.getInstance().getCurrentUser().getCoin()));
    }
}
