package project.managers;

import javafx.scene.image.ImageView;
import project.gameStuff.GameData;

public abstract class MovingEntity extends ImageView {
    private double Vx;
    private double Vy;
    private double aX;
    private boolean onBlock;

    public void fall() {
        double dt = 20 / 1000.0;
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
}
