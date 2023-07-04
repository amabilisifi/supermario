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
            System.out.println(folderPath);
            String path = folderPath + "/load" + level.getLevelNum() + "-" + section.getSectionNum() + ".json";
            File file = new File(path);
            file.createNewFile();
            JsonManager manager = new JsonManager(path);
//            Section gameData = new Section(section.getTime(), section.getBlockList(), section.getPipeList(), section.getCoinList(), section.getItemList(), section.getEnemyList(), section.getCheckPointList(), section.getEndPoint());
//            manager.writeObject(gameData);
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
