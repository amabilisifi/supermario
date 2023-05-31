package project;

import project.Characters.Character;

public class CollisionManager {
    private static CollisionManager instance;
    private Character character = UserData.getInstance().getCurrentUser().getSelectedCharacter();

    public static CollisionManager getInstance() {
        if (instance == null) {
            instance = new CollisionManager();
        }
        return instance;
    }
}
