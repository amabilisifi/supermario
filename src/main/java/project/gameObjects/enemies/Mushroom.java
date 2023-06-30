package project.gameObjects.enemies;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import project.GameObjectsInfo;

import java.net.URL;
import java.util.ResourceBundle;

public class Mushroom extends Enemy {
    public Mushroom(double startX, double startY) {
        super(startX, startY);
        this.setFitWidth(GameObjectsInfo.getInstance().getMushroomWidth());
        this.setImage(new Image(String.valueOf(getClass().getResource("/images/enemies/Goompa.png"))));
    }
}
