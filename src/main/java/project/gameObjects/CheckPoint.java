package project.gameObjects;

import javafx.scene.image.Image;
import project.GameObjectsInfo;

public class CheckPoint extends EndPoint{
    private boolean colided = false;
    public CheckPoint(double startX, double startY) {
        super(startX,startY);
        setType("CheckPoint");
        setStartX(startX);
        setX(startX);
        setY(startY);
        setImage(new Image(String.valueOf(getClass().getResource("/images/checkPoint.PNG"))));
        setFitWidth(GameObjectsInfo.getInstance().getCheckPointWidth());
        setFitHeight(GameObjectsInfo.getInstance().getCheckPointHeight());
    }

    public boolean isColided() {
        return colided;
    }

    public void setColided(boolean colided) {
        this.colided = colided;
    }
}
