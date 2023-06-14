package project.characters;

import javafx.scene.image.Image;
import project.GameObjectsInfo;

public class Mateo extends Character {
    public Mateo() {
        this.setCharacterType("Mateo");
        setPrice((int) GameObjectsInfo.getInstance().getMateoPrice());
        this.setFitHeight(GameObjectsInfo.getInstance().getCharacterHeight());
        this.setFitWidth(GameObjectsInfo.getInstance().getCharacterWidth());
        this.setSpeedo(GameObjectsInfo.getInstance().getMateoSpeedo());
        this.setJumpVelocity(GameObjectsInfo.getInstance().getMateoJumpVelocity());

        this.setProfilePhoto(new Image(String.valueOf(getClass().getResource("/images/characters/skyBlue/normal.PNG"))));
        this.setImg(new Image(String.valueOf(getClass().getResource("/images/characters/skyBlue/normal.PNG"))));
        this.setImage1(new Image(String.valueOf(getClass().getResource("/images/characters/skyBlue/1.PNG"))));
        this.setImage2(new Image(String.valueOf(getClass().getResource("/images/characters/skyBlue/2.PNG"))));
        this.setImage3(new Image(String.valueOf(getClass().getResource("/images/characters/skyBlue/3.PNG"))));
        this.setImage4(new Image(String.valueOf(getClass().getResource("/images/characters/skyBlue/4.PNG"))));
        this.setImageSit(new Image(String.valueOf(getClass().getResource("/images/characters/skyBlue/sit.PNG"))));

        this.setImage(getImg());
    }
}
