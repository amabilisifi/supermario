package project.gameObjects.enemies;

import javafx.scene.image.ImageView;

public class Spiny extends Enemy {
    public Spiny(double startX,double startY) {
        super(startX,startY);
        setVx(3);
    }
}
