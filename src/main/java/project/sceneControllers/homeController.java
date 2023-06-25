package project.sceneControllers;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.*;
import project.gameStuff.*;
import project.levels.temp;
import project.managers.Page.PageType;
import project.managers.Page.SceneManager;

import java.io.IOException;

public class homeController {
    User currentUser = UsersData.getInstance().getCurrentUser();

    public void goToHardnessScene(ActionEvent event)  {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.HardnessPage);
    }

    public void goToProfileScene(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.ProfilePage);
    }

    public void goToStart(ActionEvent event) throws IOException {
        UsersData.getInstance().setCurrentUser(null);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.StartPage);
    }

    public void goToShop(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.ShopPage);
    }

    public void dingDong(MouseEvent event) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        Level temp = new temp();
        Section t =  temp.getSections().get(0);
        SectionDesigner sectionDesigner = new SectionDesigner(root,t);
        GameData.getInstance().setRoot(root);
        GameData.getInstance().setCurrentLevel(temp);
        GameData.getInstance().setCurrentSection(t);

        GameController controller = new GameController(scene,root);
        GameData.getInstance().setGameController(controller);


        sectionDesigner.paint();

        stage.setResizable(false);
        stage.show();
    }

    public void goToHistoryScene(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.HistoryPage);
    }
}