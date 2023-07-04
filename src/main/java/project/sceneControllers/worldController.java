package project.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.gameStuff.GameData;
import project.gameStuff.Level;
import project.gameStuff.Section;
import project.gameStuff.SectionDesigner;
import project.levels.level1;
import project.levels.level2;
import project.managers.Page.PageType;
import project.managers.Page.SceneManager;
import project.managers.SoundPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class worldController implements Initializable {
    public void goHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.HomePage);
    }
    public void level1part1(ActionEvent event) throws IOException {
//        SectionDesigner.getInstance().sketch(1,1);
        goToLevel1part(0,event);
    }
    public void level1part2(ActionEvent event) throws IOException {
//        SectionDesigner.getInstance().sketch(1,2);
        goToLevel1part(1,event);
    }
    public void level1part3(ActionEvent event) throws IOException {
//        SectionDesigner.getInstance().sketch(1,3);
        goToLevel1part(2,event);
    }
    public void level1part4(ActionEvent event) throws IOException {
//        SectionDesigner.getInstance().sketch(1,2);
        goToLevel1part(3,event);
    }
    public void level1part5(ActionEvent event) throws IOException {
//        SectionDesigner.getInstance().sketch(1,3);
        goToLevel1part(4,event);
    }
    public void level2part1(ActionEvent event) throws IOException {
//        SectionDesigner.getInstance().sketch(1,1);
        goToLevel2part(0,event);
    }
    public void level2part2(ActionEvent event) throws IOException {
//        SectionDesigner.getInstance().sketch(1,2);
        goToLevel2part(1,event);
    }
    public void level2part3(ActionEvent event) throws IOException {
//        SectionDesigner.getInstance().sketch(1,3);
        goToLevel2part(2,event);
    }
    public void level2part4(ActionEvent event) throws IOException {
//        SectionDesigner.getInstance().sketch(1,2);
        goToLevel2part(3,event);
    }
    public void level2part5(ActionEvent event) throws IOException {
//        SectionDesigner.getInstance().sketch(1,3);
        goToLevel2part(4,event);
    }
    public void goToLevel1part(int num , ActionEvent event){
        GameData.getInstance().getCurrentSoundPlayer().stop();
        Group root = new Group();
        SectionDesigner.getInstance().setRoot(root);
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        GameData.getInstance().setRoot(root);
        GameData.getInstance().setScene(scene);


        Level temp = new level1();
        Section t = temp.getSections().get(num);
        GameData.getInstance().setCurrentLevel(temp);
        GameData.getInstance().setCurrentSection(t);


        SectionDesigner.getInstance().paint(t);

        stage.setResizable(false);
        stage.show();
    }
    public void goToLevel2part(int num , ActionEvent event){
        GameData.getInstance().getCurrentSoundPlayer().stop();
        Group root = new Group();
        SectionDesigner.getInstance().setRoot(root);
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        GameData.getInstance().setRoot(root);
        GameData.getInstance().setScene(scene);


        Level temp = new level2();
        Section t = temp.getSections().get(num);
        GameData.getInstance().setCurrentLevel(temp);
        GameData.getInstance().setCurrentSection(t);


        SectionDesigner.getInstance().paint(t);

        stage.setResizable(false);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
