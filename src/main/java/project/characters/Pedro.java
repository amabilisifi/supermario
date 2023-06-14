package project.characters;

import javafx.scene.image.Image;
import project.GameObjectsInfo;

public class Pedro extends Character {
    public Pedro() {
        this.setCharacterType("Pedro");
        setPrice((int) GameObjectsInfo.getInstance().getPedroPrice());
        this.setFitHeight(GameObjectsInfo.getInstance().getCharacterHeight());
        this.setFitWidth(GameObjectsInfo.getInstance().getCharacterWidth());
        this.setSpeedo(GameObjectsInfo.getInstance().getPedroSpeedo());
        this.setJumpVelocity(GameObjectsInfo.getInstance().getPedroJumpVelocity());

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
