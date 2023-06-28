package project.characters;

import javafx.scene.image.Image;
import project.GameObjectsInfo;

public class Antonio extends Character {

    public Antonio() {
        this.setCharacterType("Antonio");
        setPrice((int) GameObjectsInfo.getInstance().getAntonioPrice());
        this.setMode(CharacterModes.Mega);
        this.setFitWidth(GameObjectsInfo.getInstance().getCharacterWidth());
        this.setSpeedo(GameObjectsInfo.getInstance().getAntonioSpeedo());
        this.setJumpVelocity(GameObjectsInfo.getInstance().getAntonioJumpVelocity());

        this.setProfilePhoto(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/normal.PNG"))));
        this.setImg(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/normal.PNG"))));
        this.setImage1(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/1.PNG"))));
        this.setImage2(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/2.PNG"))));
        this.setImage3(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/3.PNG"))));
        this.setImage4(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/4.PNG"))));
        this.setImageSit(new Image(String.valueOf(getClass().getResource("/images/characters/darkGreen/sit.PNG"))));

        this.setImage(getImg());
    }

}
