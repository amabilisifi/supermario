package project.managers;

import project.characters.Character;
import project.UsersData;

// this is collision manger for character
public class CollisionManager {
    private static CollisionManager instance;
    private Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();

    public static CollisionManager getInstance() {
        if (instance == null) {
            instance = new CollisionManager();
        }
        return instance;
    }
}
