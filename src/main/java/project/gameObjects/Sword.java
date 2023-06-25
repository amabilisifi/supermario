package project.gameObjects;

import javafx.scene.image.Image;
import project.GameObjectsInfo;
import project.managers.MovingEntity;

public class Sword extends MovingEntity {
    private double startX;
    private boolean turnBack = false;


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
        this.move();
    }

    public double getStartX() {
        return startX;
    }

    public boolean isTurnBack() {
        return turnBack;
    }

    public void setTurnBack(boolean turnBack) {
        this.turnBack = turnBack;
    }

    @Override
    public void fall() {

    }
}
