package project.managers;

import javafx.fxml.Initializable;
import javafx.scene.Group;
import project.GameController;
import project.gameObjects.Pipe;
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
    private  List<Level> allLevels = new ArrayList<>();
    private Level currentLevel;
    private int currentLevelNum;
    private Section currentSection = GameData.getInstance().getCurrentSection();
    private int currentSectionNum;

    public static LevelManager getInstance() {
        if (instance == null) {
            instance = new LevelManager();
        }
        return instance;
    }

    public void addLevel(Level level) {
        allLevels.add(level);
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
//            next = allLevels.get(0);
//            currentLevelNum = 0;
            System.exit(0);
        }
        GameData.getInstance().setCurrentLevel(next);
    }

    public void goToNextSection() {
        System.out.println(allLevels.size());
        System.out.println("next section");
        currentSection = GameData.getInstance().getCurrentSection();
        currentLevel = GameData.getInstance().getCurrentLevel();
        SectionDesigner.getInstance().clearSection(currentSection);
        Section next = null;
        System.out.println(currentSection.getSectionNum());
        if (currentSection.getSectionNum() + 1 < currentLevel.getSections().size()) {
            currentSection.setSectionNum(currentSection.getSectionNum() + 1);
            currentSectionNum ++;
            next = currentLevel.getSections().get(currentSection.getSectionNum());
            System.out.println(next.getSectionNum()+"   .");
        } else {
            // next level
            System.out.println("next level");
            goToNextLevel();
        }
        GameData.getInstance().setCurrentSection(next);
        SectionDesigner.getInstance().paint(next);
    }

    public void goToSecretSection(Pipe pipe){
        Section targetSection = pipe.getSection();
        currentSection = GameData.getInstance().getCurrentSection();
        SectionDesigner.getInstance().clearSection(currentSection);
        pipe.setSecretPipe(false);

        GameData.getInstance().setCurrentSection(targetSection);
        SectionDesigner.getInstance().paint(targetSection);
    }
    public void turningBackFromSecretLevel(){
        currentLevel = GameData.getInstance().getCurrentLevel();
        Section targetSection = currentLevel.getSections().get(currentSectionNum);

        currentSection = GameData.getInstance().getCurrentSection();
        SectionDesigner.getInstance().clearSection(currentSection);

        GameData.getInstance().setCurrentSection(targetSection);
        SectionDesigner.getInstance().paint(targetSection);
    }
}
