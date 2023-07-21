package project.gameObjects.enemies;

import javafx.scene.image.Image;
import project.GameObjectsInfo;


public class Mushroom extends Enemy {
    public Mushroom(double startX, double startY) {
        super(startX, startY);
        setType("Mushroom");
        this.setFitWidth(GameObjectsInfo.getInstance().getMushroomWidth());
        this.setImage(new Image(String.valueOf(getClass().getResource("/images/enemies/Goompa.png"))));
    }
}
