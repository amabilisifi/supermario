package project.gameObjects.markers;

import javafx.scene.image.Image;
import project.GameObjectsInfo;

public class PussyCat extends EndPoint {
    public PussyCat(double startX,double startY) {
        super(startX,startY);
        setType("PussyCat");
        setX(startX);
        setY(startY);
        setImage(new Image(String.valueOf(getClass().getResource("/images/cat.PNG"))));
        setFitWidth(GameObjectsInfo.getInstance().getPussyCatWidth());
        setFitHeight(GameObjectsInfo.getInstance().getPussyCatHeight());
    }
}
