package project.Characters;

import javafx.scene.image.Image;

public class Lorenzo extends Character{
    private static int price ;
    private static double speedo;
    public Lorenzo() {
        this.setCharacterType("Lorenzo");
        setPrice(price);
        this.setFitHeight(getHeight());
        this.setFitWidth(getWidth());

        this.setProfilePhoto(new Image(String.valueOf(getClass().getResource("/images/characters/orange/normal.PNG"))));
        this.setImg(new Image(String.valueOf(getClass().getResource("/images/characters/orange/normal.PNG"))));
        this.setImage1(new Image(String.valueOf(getClass().getResource("/images/characters/orange/1.PNG"))));
        this.setImage2(new Image(String.valueOf(getClass().getResource("/images/characters/orange/2.PNG"))));
        this.setImage3(new Image(String.valueOf(getClass().getResource("/images/characters/orange/3.PNG"))));
        this.setImage4(new Image(String.valueOf(getClass().getResource("/images/characters/orange/4.PNG"))));
        this.setImageSit(new Image(String.valueOf(getClass().getResource("/images/characters/orange/sit.PNG"))));

        this.setImage(getImg());
    }
    public int getPrice() {
        return price;
    }

    public static void setPrice(int price) {
        Lorenzo.price = price;
    }


    public static double getSpeedo() {
        return speedo;
    }

    public static void setSpeedo(double speedo) {
        Lorenzo.speedo = speedo;
    }
}
