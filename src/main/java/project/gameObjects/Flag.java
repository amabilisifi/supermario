package project.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.GameObjectsInfo;

public class Flag extends ImageView {
    public Flag(double startX,double startY) {
        setX(startX);
        setY(startY);
        setImage(new Image(String.valueOf(getClass().getResource("/images/flag.PNG"))));
        setFitWidth(GameObjectsInfo.getInstance().getFlagWidth());
        setFitHeight(GameObjectsInfo.getInstance().getFlagHeight());
    }
}
