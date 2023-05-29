package project.gameObjects;

import javafx.scene.image.ImageView;

public abstract class Enemy extends ImageView {
    private static double width;
    private static double height;
    private double startX;
    private double startY;
    private double currentX;
    private double currentY;

    public Enemy(double x, double y) {
        this.startX = x;
        this.startY = y;
    }

    public Enemy() {
    }
}
