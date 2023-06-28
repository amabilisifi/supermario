package project.characters;

import javafx.scene.image.Image;
import project.GameObjectsInfo;

public class Alexandro extends Character {
    public Alexandro() {
        this.setCharacterType("Alexandro");
        setPrice((int) GameObjectsInfo.getInstance().getAlexandroPrice());
        this.setMode(CharacterModes.Mega);
        this.setFitWidth(GameObjectsInfo.getInstance().getCharacterWidth());
        this.setSpeedo(GameObjectsInfo.getInstance().getAlexandroSpeedo());
        this.setJumpVelocity(GameObjectsInfo.getInstance().getAlexandroJumpVelocity());

        this.setProfilePhoto(new Image(String.valueOf(getClass().getResource("/images/characters/red/normal.PNG"))));
        this.setImg(new Image(String.valueOf(getClass().getResource("/images/characters/red/normal.PNG"))));
        this.setImage1(new Image(String.valueOf(getClass().getResource("/images/characters/red/1.PNG"))));
        this.setImage2(new Image(String.valueOf(getClass().getResource("/images/characters/red/2.PNG"))));
        this.setImage3(new Image(String.valueOf(getClass().getResource("/images/characters/red/3.PNG"))));
        this.setImage4(new Image(String.valueOf(getClass().getResource("/images/characters/red/4.PNG"))));
        this.setImageSit(new Image(String.valueOf(getClass().getResource("/images/characters/red/sit.PNG"))));

        this.setImage(getImg());
    }
}
