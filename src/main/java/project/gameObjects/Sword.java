package project.gameObjects;

import javafx.scene.image.Image;
import project.GameObjectsInfo;
import project.gameObjects.enemies.Direction;
import project.MovingEntity;

public class Sword extends MovingEntity {
    private double startX;
    private boolean isTurning = false;
    private boolean isObjectDeleted = false;


    public Sword(double characterX, double characterY, double characterHeight,Direction direction) {
        double width = 2 * GameObjectsInfo.getInstance().getBlockWidth();
        double height = GameObjectsInfo.getInstance().getSwordHeight();
        Image img = new Image(String.valueOf(getClass().getResource("/images/sword.PNG")));
        this.setFitHeight(height);
        if(direction == Direction.Right) {
            this.setX(characterX + GameObjectsInfo.getInstance().getCharacterWidth());
            this.startX = characterX + GameObjectsInfo.getInstance().getCharacterWidth();
            setScaleX(1);
        }
        if (direction == Direction.Left) {
            this.setX(characterX - this.getFitWidth());
            this.startX = characterX- this.getFitWidth();
            setScaleX(-1);
        }
        this.setY(characterY + characterHeight - GameObjectsInfo.getInstance().getBlockHeight());
        this.setFitWidth(width);
        this.setImage(img);
        setDirection(Direction.Right);
    }

    public double getStartX() {
        return startX;
    }

    public boolean isTurning() {
        return isTurning;
    }

    public void setTurning(boolean turning) {
        this.isTurning = turning;
    }

    public boolean isObjectDeleted() {
        return isObjectDeleted;
    }

    public void setObjectDeleted(boolean objectDeleted) {
        isObjectDeleted = objectDeleted;
    }
}
