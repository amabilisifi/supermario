package project.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.GameObjectsInfo;

public class Flag extends EndPoint {
    private double startX;
    public Flag(double startX,double startY) {
        super(startX,startY);
        setType("Flag");
        setX(startX);
        setY(startY);
        setImage(new Image(String.valueOf(getClass().getResource("/images/flag.PNG"))));
        setFitWidth(GameObjectsInfo.getInstance().getFlagWidth());
        setFitHeight(GameObjectsInfo.getInstance().getFlagHeight());
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }
}
