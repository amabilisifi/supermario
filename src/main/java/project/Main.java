package project;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import project.collisionAriyan.GraphicalObjects;

import java.awt.image.BufferedImage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        Image img = new Image(String.valueOf(getClass().getResource("/images/characters/orange/normal.PNG")), 340, 500, false, false);
        ImageView imageview = new ImageView(img);
        imageview.setX(80);

        BufferedImage image = SwingFXUtils.fromFXImage(img, null);
        ImageView imageView2 = new ImageView();
        GraphicalObjects bowser = new GraphicalObjects(image);

        root.getChildren().add(imageview);
        stage.setScene(scene);
        stage.show();
    }
}
