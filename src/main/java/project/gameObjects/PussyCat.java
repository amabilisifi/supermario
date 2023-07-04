package project.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.GameObjectsInfo;
import project.gameObjects.enemies.Enemy;

public class PussyCat extends EndPoint {
    private double startX;
    public PussyCat(double startX,double startY) {
        super(startX,startY);
        setType("PussyCat");
        setX(startX);
        setY(startY);
        setImage(new Image(String.valueOf(getClass().getResource("/images/cat.PNG"))));
        setFitWidth(GameObjectsInfo.getInstance().getPussyCatWidth());
        setFitHeight(GameObjectsInfo.getInstance().getPussyCatHeight());
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }
}
