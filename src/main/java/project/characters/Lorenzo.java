package project.characters;

import javafx.scene.image.Image;
import project.GameObjectsInfo;

public class Lorenzo extends Character {
    public Lorenzo() {
        this.setCharacterType("Lorenzo");
        setPrice((int) GameObjectsInfo.getInstance().getLorenzoPrice());
        this.setFitHeight(GameObjectsInfo.getInstance().getCharacterHeight());
        this.setFitWidth(GameObjectsInfo.getInstance().getCharacterWidth());
        this.setSpeedo(GameObjectsInfo.getInstance().getLorenzoSpeedo());
        this.setJumpVelocity(GameObjectsInfo.getInstance().getLorenzoJumpVelocity());

        this.setProfilePhoto(new Image(String.valueOf(getClass().getResource("/images/characters/orange/normal.PNG"))));
        this.setImg(new Image(String.valueOf(getClass().getResource("/images/characters/orange/normal.PNG"))));
        this.setImage1(new Image(String.valueOf(getClass().getResource("/images/characters/orange/1.PNG"))));
        this.setImage2(new Image(String.valueOf(getClass().getResource("/images/characters/orange/2.PNG"))));
        this.setImage3(new Image(String.valueOf(getClass().getResource("/images/characters/orange/3.PNG"))));
        this.setImage4(new Image(String.valueOf(getClass().getResource("/images/characters/orange/4.PNG"))));
        this.setImageSit(new Image(String.valueOf(getClass().getResource("/images/characters/orange/sit.PNG"))));

        this.setImage(getImg());
    }
}
