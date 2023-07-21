package project.gameObjects.enemies;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.gameObjects.Direction;
import project.gameStuff.GameData;
import project.managers.CollisionManager;

public class Turtle extends Enemy {
    private boolean isAbleToBeThrown = true;
    private boolean beenCrazy = false;
    private boolean isAbleToMove = true;
    private boolean isCrazyRN = false;

    public Turtle(double startX, double startY) {
        super(startX, startY);
        setType("Turtle");
        this.setFitWidth(GameObjectsInfo.getInstance().getTurtleWidth());
        this.setImage(new Image(String.valueOf(getClass().getResource("/images/enemies/Turtle.png"))));
    }

    public void move() {
        // it should turn if it collides sth or when it'll turn around
        //speed is 0.5 block per seconds
        double dt = 20 / 100.0;
        if (getDirection() == Direction.Right && isAbleToMove) {
            setVx(getVx() - Math.abs(getaX() * dt / 3));
            setX(getX() + Math.abs(getVx()) * dt);
            setScaleX(-1);
        }
        if (getDirection() == Direction.Left && isAbleToMove) {
            setVx(getVx() - Math.abs(getaX() * dt / 3));
            setX(getX() - Math.abs(getVx()) * dt);
            setScaleX(1);
        }
        if (getVx() <= 0 && !this.isBeenCrazy()) {
            this.setBeenCrazy(true);
            isAbleToMove = false;
            Timeline chill = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
                setAbleToMove(true);
                isCrazyRN = false;
            }));
            chill.setCycleCount(1);
            chill.playFromStart();
            GameData.getInstance().addTimeLine(chill);
        }
        if (isBeenCrazy()) {
            if (getVx() < -4) setVx(-4);
            if (getVx() > 4) setVx(4);
        }
        CollisionManager.getInstance().collisionWithObjectsInGame(this);
    }

    public boolean isAbleToBeThrown() {
        return isAbleToBeThrown;
    }

    public void setAbleToBeThrown(boolean ableToBeThrown) {
        isAbleToBeThrown = ableToBeThrown;
    }

    public boolean isBeenCrazy() {
        return beenCrazy;
    }

    public void setBeenCrazy(boolean beenCrazy) {
        this.beenCrazy = beenCrazy;
    }

    public void setAbleToMove(boolean ableToMove) {
        isAbleToMove = ableToMove;
    }

    public boolean isCrazyRN() {
        return isCrazyRN;
    }

    public void setCrazyRN(boolean crazyRN) {
        isCrazyRN = crazyRN;
    }
}
