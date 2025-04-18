package project.Characters;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Alexandro extends Character{
    private static int price ;

    private static double speedo;
    public Alexandro() {
        this.setCharacterType("Alexandro");
        setPrice(price);
        this.setFitHeight(getHeight());
        this.setFitWidth(getWidth());

        this.setProfilePhoto(new Image(String.valueOf(getClass().getResource("/images/characters/red/normal.PNG"))));
        this.setImg(new Image(String.valueOf(getClass().getResource("/images/characters/red/normal.PNG"))));
        this.setImage1(new Image(String.valueOf(getClass().getResource("/images/characters/red/1.PNG"))));
        this.setImage2(new Image(String.valueOf(getClass().getResource("/images/characters/red/2.PNG"))));
        this.setImage3(new Image(String.valueOf(getClass().getResource("/images/characters/red/3.PNG"))));
        this.setImage4(new Image(String.valueOf(getClass().getResource("/images/characters/red/4.PNG"))));
        this.setImageSit(new Image(String.valueOf(getClass().getResource("/images/characters/red/sit.PNG"))));

        this.setImage(getImg());
    }

    public int getPrice() {
        return price;
    }

    public static void setPrice(int price) {
        Alexandro.price = price;
    }


    public static double getSpeedo() {
        return speedo;
    }

    public static void setSpeedo(double speedo) {
        Alexandro.speedo = speedo;
    }
}
