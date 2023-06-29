package project.gameObjects;

import javafx.scene.image.Image;
import project.GameObjectsInfo;
import project.gameObjects.enemies.Direction;
import project.managers.MovingEntity;

public class Sword extends MovingEntity {
    private double startX;
    private boolean isGoingLeft = false;


    public Sword(double characterX, double characterY,double characterHeight) {
        double width = 2* GameObjectsInfo.getInstance().getBlockWidth();
        double height = GameObjectsInfo.getInstance().getSwordHeight();
        Image img = new Image(String.valueOf(getClass().getResource("/images/sword.PNG")));
        this.setFitHeight(height);
        this.setX(characterX+ GameObjectsInfo.getInstance().getCharacterWidth());
        this.startX = characterX+ GameObjectsInfo.getInstance().getCharacterWidth();
        this.setY(characterY+characterHeight- GameObjectsInfo.getInstance().getBlockHeight());
        this.setFitWidth(width);
        this.setImage(img);
        setDirection(Direction.Right);
    }

    public double getStartX() {
        return startX;
    }

    public boolean isGoingLeft() {
        return isGoingLeft;
    }

    public void setGoingLeft(boolean goingLeft) {
        this.isGoingLeft = goingLeft;
    }
}
