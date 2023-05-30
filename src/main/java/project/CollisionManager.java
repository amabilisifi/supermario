package project;

import javafx.geometry.Bounds;
import project.Characters.Character;
import project.gameObjects.Block;

public class CollisionManager {
    private Character character = UserData.getInstance().getCurrentUser().getSelectedCharacter();
    private static CollisionManager instance;

    public static CollisionManager getInstance(){
        if(instance == null){
            instance = new CollisionManager();
        }
        return instance;
    }
}
