package project.characters;

import javafx.scene.image.Image;
import project.GameObjectsInfo;

public class Pablo extends Character {
    public Pablo() {
        this.setCharacterType("Pablo");
        setPrice((int) GameObjectsInfo.getInstance().getPabloPrice());
        this.setMode(CharacterModes.Mega);
        this.setFitWidth(GameObjectsInfo.getInstance().getCharacterWidth());
        this.setSpeedo(GameObjectsInfo.getInstance().getPabloSpeedo());
        this.setJumpVelocity(GameObjectsInfo.getInstance().getPabloJumpVelocity());

        this.setProfilePhoto(new Image(String.valueOf(getClass().getResource("/images/characters/purple/normal.PNG"))));
        this.setImg(new Image(String.valueOf(getClass().getResource("/images/characters/purple/normal.PNG"))));
        this.setImage1(new Image(String.valueOf(getClass().getResource("/images/characters/purple/1.PNG"))));
        this.setImage2(new Image(String.valueOf(getClass().getResource("/images/characters/purple/2.PNG"))));
        this.setImage3(new Image(String.valueOf(getClass().getResource("/images/characters/purple/3.PNG"))));
        this.setImage4(new Image(String.valueOf(getClass().getResource("/images/characters/purple/4.PNG"))));
        this.setImageSit(new Image(String.valueOf(getClass().getResource("/images/characters/purple/sit.PNG"))));

        this.setImage(getImg());
    }
}
