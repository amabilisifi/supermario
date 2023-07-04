package project;

import project.gameStuff.Level;
import project.gameStuff.Section;
import project.managers.JsonManager;

import java.io.File;
import java.io.IOException;

public class StorageController {
    private static StorageController instance;


    public StorageController() {
    }

    public void save(Level level, Section section) {
        try {
            String folderPath = UsersData.getInstance().getCurrentUser().getFilePath();
            section.setUserHaveCheckPointSaved(true);
            String path = folderPath + "/load" + level.getLevelNum() + "-" + section.getSectionNum() + ".json";
            section.setSavedCheckPointPath(path);
            File file = new File(path);
            file.createNewFile();
            JsonManager manager = new JsonManager(path);
            manager.writeObject(section);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static StorageController getInstance() {
        if (instance == null)
            instance = new StorageController();
        return instance;
    }
}
