package project;

import project.gameStuff.GameData;
import project.gameStuff.Section;
import project.managers.JsonManager;

import java.io.File;
import java.io.IOException;

public class StorageController {
    private User user;
    private String folderPath;// "src/main/resources/GameData/"+username+"/LoadData"
    private int savedFileNum = 1;

    public StorageController(User user) {
        this.user = user;
        folderPath = "./src/main/resources/GameData/" + user.getName() + "/LoadData";
    }

    public void save(Section section) {
        try {
        String path = folderPath + "/load" + savedFileNum + ".json";
        File file = new File(path);
        savedFileNum++;
            file.createNewFile();
        JsonManager manager = new JsonManager(path);
        Section gameData = new Section( section.getTime(),section.getBlockList(),section.getPipeList(),section.getCoinList(),section.getItemList(),section.getEnemyList(),section.getCheckPointList(),section.getEndPoint());
        manager.writeObject(gameData);} catch (IOException e) {
        throw new RuntimeException(e);
    }
    }
}
