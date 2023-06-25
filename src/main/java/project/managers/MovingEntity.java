package project.managers;

import javafx.scene.image.ImageView;
import project.gameStuff.GameData;

public abstract class MovingEntity extends ImageView {
    private double Vx;
    private double Vy;
    private double aX;
    private boolean onBlock;

    public abstract void fall();

    public void move() {
        double dt = 20 / 1000.0;
        if (!onBlock)
            Vy += GameData.getInstance().getCurrentSection().getGravity() * dt;
        Vx += aX * dt;
        // moving
        setX(getX() + Vx * dt);
        setY(getY() + Vy * dt);
    }
}
