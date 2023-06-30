package project.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.GameObjectsInfo;

public class PussyCat extends ImageView {
    public PussyCat(double startX,double startY) {
        setX(startX);
        setY(startY);
        setImage(new Image(String.valueOf(getClass().getResource("/images/cat.PNG"))));
        setFitWidth(GameObjectsInfo.getInstance().getPussyCatWidth());
        setFitHeight(GameObjectsInfo.getInstance().getPussyCatHeight());
    }
}
