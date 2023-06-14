package project.managers;

import javafx.fxml.Initializable;
import project.GameData;
import project.Level;
import project.Section;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LevelManager implements Initializable {
    private static LevelManager instance;
    private  List<Level> allLevels = new ArrayList<>();
    private Level currentLevel;
    private int currentLevelNum;
    private Section currentSection;

    public static LevelManager getInstance(){
        if(instance== null){
            instance = new LevelManager();
        }
        return instance;
    }
    public void addLevel(Level level){
        allLevels.add(level);
    }

    public List<Level> getAllLevels() {
        return allLevels;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.currentLevel = GameData.getInstance().getCurrentLevel();
        this.currentLevelNum = currentLevel.getLevelNum();
    }

    public void goToNextLevel(){
        Level next = null;
        if(currentLevelNum+1<allLevels.size())
            next = allLevels.get(++currentLevelNum);
        if(currentLevelNum+1>=allLevels.size()) {
            next = allLevels.get(0);
            currentLevelNum = 0;
        }
        GameData.getInstance().setCurrentLevel(next);
    }
}
