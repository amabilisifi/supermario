package project.Characters;

import javafx.scene.image.Image;
import project.GameInfo;

public class Antonio extends Character {
    private static int price;

    private static double speedo;

    public Antonio() {
        this.setCharacterType("Antonio");
        setPrice((int) GameInfo.getInstance().getAntonioPrice());
        this.setFitHeight(GameInfo.getInstance().getCharacterHeight());
        this.setFitWidth(GameInfo.getInstance().getCharacterWidth());

        this.setProfilePhoto(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/normal.PNG"))));
        this.setImg(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/normal.PNG"))));
        this.setImage1(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/1.PNG"))));
        this.setImage2(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/2.PNG"))));
        this.setImage3(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/3.PNG"))));
        this.setImage4(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/4.PNG"))));
        this.setImageSit(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/sit.PNG"))));

        this.setImage(getImg());
    }

    public void setPrice(int price) {
        Antonio.price = price;
    }


    public static double getSpeedo() {
        return speedo;
    }

    public static void setSpeedo(double speedo) {
        Antonio.speedo = speedo;
    }

}
