package project.Characters;

import javafx.scene.image.Image;
import project.GameInfo;

public class Pedro extends Character {
    public Pedro() {
        this.setCharacterType("Pedro");
        setPrice((int) GameInfo.getInstance().getPedroPrice());
        this.setFitHeight(GameInfo.getInstance().getCharacterHeight());
        this.setFitWidth(GameInfo.getInstance().getCharacterWidth());
        this.setSpeedo(GameInfo.getInstance().getPedroSpeedo());
        this.setJumpVelocity(GameInfo.getInstance().getPedroJumpVelocity());

        this.setProfilePhoto(new Image(String.valueOf(getClass().getResource("/images/characters/blue/normal.PNG"))));
        this.setImg(new Image(String.valueOf(getClass().getResource("/images/characters/blue/normal.PNG"))));
        this.setImage1(new Image(String.valueOf(getClass().getResource("/images/characters/blue/1.PNG"))));
        this.setImage2(new Image(String.valueOf(getClass().getResource("/images/characters/blue/2.PNG"))));
        this.setImage3(new Image(String.valueOf(getClass().getResource("/images/characters/blue/3.PNG"))));
        this.setImage4(new Image(String.valueOf(getClass().getResource("/images/characters/blue/4.PNG"))));
        this.setImageSit(new Image(String.valueOf(getClass().getResource("/images/characters/blue/sit.PNG"))));

        this.setImage(getImg());
    }
}
