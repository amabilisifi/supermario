package project.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Flag extends ImageView {
    private Image img = new Image(String.valueOf(getClass().getResource("images/flag.PNG")));

    private double width;
    private double height;
    private double startX;
    private double startY;
    private double currentX = startX;
    private double currentY = startY;

    public Flag(double X, double Y) {
        this.setImage(img);
        this.setX(X);
        this.setY(Y);
        this.setFitWidth(width);
        this.setFitHeight(height);
        this.startX = X;
        this.startY = Y;
    }
    /** getter setter **/

    public Image getImg() {
        return img;
    }

    public void setImg(Image image) {
        this.img = image;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
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

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getCurrentX() {
        return currentX;
    }

    public void setCurrentX(double currentX) {
        this.currentX = currentX;
    }

    public double getCurrentY() {
        return currentY;
    }

    public void setCurrentY(double currentY) {
        this.currentY = currentY;
    }
}
