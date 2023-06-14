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
import project.UsersData;
import project.managers.Page.PageType;
import project.managers.Page.SceneManager;

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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.HomePage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameText.setText(UsersData.getInstance().getCurrentUser().getName());
        highestScoreText.setText(String.valueOf(UsersData.getInstance().getCurrentUser().getHighScore()));
        coinText.setText(String.valueOf(UsersData.getInstance().getCurrentUser().getCoin()));
    }
}
