package project.managers;

import javafx.fxml.Initializable;
import javafx.scene.Group;
import project.GameController;
import project.gameStuff.GameData;
import project.gameStuff.Level;
import project.gameStuff.Section;
import project.gameStuff.SectionDesigner;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LevelManager implements Initializable {
    private static LevelManager instance;
    private List<Level> allLevels = new ArrayList<>();
    private Level currentLevel;
    private int currentLevelNum;
    private Section currentSection = GameData.getInstance().getCurrentSection();

    public static LevelManager getInstance() {
        if (instance == null) {
            instance = new LevelManager();
        }
        return instance;
    }

    public void addLevel(Level level) {
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

    public void goToNextLevel() {
        Level next = null;
        if (currentLevelNum + 1 < allLevels.size())
            next = allLevels.get(++currentLevelNum);
        if (currentLevelNum + 1 >= allLevels.size()) {
            next = allLevels.get(0);
            currentLevelNum = 0;
        }
        GameData.getInstance().setCurrentLevel(next);
    }

    public void goToNextSection() {
        currentSection = GameData.getInstance().getCurrentSection();
        currentLevel = GameData.getInstance().getCurrentLevel();
        System.out.println("we were in level  " + currentLevelNum + " - section " + currentSection.getSectionNum() + "\n and because we have " + currentLevel.getSections().size() + " sections ");
        SectionDesigner.getInstance().clearSection();
        Section next = null;
        if (currentSection.getSectionNum() + 1 < currentLevel.getSections().size()) {
            currentSection.setSectionNum(currentSection.getSectionNum() + 1);
            next = currentLevel.getSections().get(currentSection.getSectionNum());
        } else {
            System.out.println("next level");
        }
        // next level
        System.out.println(next.getSectionNum() + " this is our next section ");
        GameData.getInstance().setCurrentSection(next);
        GameController.getInstance().updateController();
        SectionDesigner.getInstance().paint(next);
    }
}
