package project.characters;

import javafx.scene.image.Image;
import project.GameObjectsInfo;

public class Diego extends Character {
    public Diego() {
        this.setCharacterType("Diego");
        setPrice((int) GameObjectsInfo.getInstance().getDiegoPrice());
        this.setFitHeight(GameObjectsInfo.getInstance().getCharacterHeight());
        this.setFitWidth(GameObjectsInfo.getInstance().getCharacterWidth());
        this.setSpeedo(GameObjectsInfo.getInstance().getDiegoSpeedo());
        this.setJumpVelocity(GameObjectsInfo.getInstance().getDiegoJumpVelocity());

        this.setProfilePhoto(new Image(String.valueOf(getClass().getResource("/images/characters/yellow/normal.PNG"))));
        this.setImg(new Image(String.valueOf(getClass().getResource("/images/characters/yellow/normal.PNG"))));
        this.setImage1(new Image(String.valueOf(getClass().getResource("/images/characters/yellow/1.PNG"))));
        this.setImage2(new Image(String.valueOf(getClass().getResource("/images/characters/yellow/2.PNG"))));
        this.setImage3(new Image(String.valueOf(getClass().getResource("/images/characters/yellow/3.PNG"))));
        this.setImage4(new Image(String.valueOf(getClass().getResource("/images/characters/yellow/4.PNG"))));
        this.setImageSit(new Image(String.valueOf(getClass().getResource("/images/characters/yellow/sit.PNG"))));

        this.setImage(getImg());
    }
}
