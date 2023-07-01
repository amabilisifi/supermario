package project;

import javafx.scene.image.ImageView;
import project.gameObjects.enemies.Direction;
import project.gameStuff.GameData;

public abstract class MovingEntity extends ImageView {
    private double Vx;
    private double Vy;
    private double aX;
    private boolean onBlock;
    private Direction direction;

    public void fall() {
        double dt = 200 / 1000.0;
        if (!onBlock)
            Vy += GameData.getInstance().getCurrentSection().getGravity() * dt;
        setY(getY() + Vy * dt);
    }

    public void move() {
        double dt = 20 / 1000.0;
        Vx += aX * dt;
        // moving
        setX(getX() + Vx * dt);
    }

    public double getVx() {
        return Vx;
    }

    public void setVx(double vx) {
        Vx = vx;
    }

    public double getVy() {
        return Vy;
    }

    public void setVy(double vy) {
        Vy = vy;
    }

    public double getaX() {
        return aX;
    }

    public void setaX(double aX) {
        this.aX = aX;
    }

    public boolean isOnBlock() {
        return onBlock;
    }

    public void setOnBlock(boolean onBlock) {
        this.onBlock = onBlock;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
