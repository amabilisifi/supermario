package project.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.GameInfo;

public class Sword extends ImageView {
    private double width;
    private double height;
    private double startX;
    private boolean turnBack = false;


    public Sword(double characterX, double characterY,double characterHeight) {
        this.width = 2* GameInfo.getInstance().getBlockWidth();
        this.height = GameInfo.getInstance().getSwordHeight();
        Image img = new Image(String.valueOf(getClass().getResource("/images/sword.PNG")));
        this.setFitHeight(height);
        this.setX(characterX+GameInfo.getInstance().getCharacterWidth());
        this.startX = characterX+GameInfo.getInstance().getCharacterWidth();
        this.setY(characterY+characterHeight-GameInfo.getInstance().getBlockHeight());
        this.setFitWidth(width);
        this.setImage(img);
    }


    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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
}
