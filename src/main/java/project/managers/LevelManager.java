package project.managers;

import javafx.fxml.Initializable;
import javafx.scene.Group;
import project.GameController;
import project.User;
import project.UsersData;
import project.gameObjects.Pipe;
import project.gameStuff.*;
import project.levels.level2;
import project.levels.temp;

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
//        Level next = null;
//        if (currentLevelNum + 1 <= allLevels.size()) {
//            currentLevelNum+=1;
//            next = allLevels.get(currentLevelNum);
//        }
//        if (currentLevelNum + 1 > allLevels.size()) {
//            System.exit(0);
//        }
//        GameData.getInstance().setCurrentLevel(next);
//        if(next!=null) {
//            HUI.getInstance().setWorld(next.getLevelNum(), 0 + 1);
//            GameData.getInstance().setCurrentSection(next.getSections().get(0));
//            SectionDesigner.getInstance().paint(next.getSections().get(0));
//        }
        currentSection = GameData.getInstance().getCurrentSection();
        currentLevel = GameData.getInstance().getCurrentLevel();
        calculateScore(currentSection);
        HUI.getInstance().setScore(GameData.getInstance().getScore());
        SectionDesigner.getInstance().clearSection(currentSection);

        Level level = new level2();
        Section t = level.getSections().get(0);
        GameData.getInstance().setCurrentLevel(level);
        GameData.getInstance().setCurrentSection(t);
        SectionDesigner.getInstance().paint(t);

        GameData.getInstance().setLevelNum(2);

        SectionDesigner.getInstance().addToRoot(UsersData.getInstance().getCurrentUser().getSelectedCharacter());
    }

    public void goToNextSection() {
        currentSection = GameData.getInstance().getCurrentSection();
        currentLevel = GameData.getInstance().getCurrentLevel();
        calculateScore(currentSection);
        HUI.getInstance().setScore(GameData.getInstance().getScore());
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
            SectionDesigner.getInstance().removeFromRoot(UsersData.getInstance().getCurrentUser().getSelectedCharacter());
            goToNextLevel();
        }
        if(next!=null) {
            HUI.getInstance().setWorld(currentLevel.getLevelNum(), next.getSectionNum() + 1);
            GameData.getInstance().setCurrentSection(next);
            SectionDesigner.getInstance().paint(next);
            HUI.getInstance().setTime(next.getTime());
        }
    }
    public void calculateScore(Section section){
        int timeLeft = section.getTime();
        int hearts = UsersData.getInstance().getCurrentUser().getSelectedCharacter().getHearts();

        int score = timeLeft+20*hearts;
        GameData.getInstance().increaseScore(score);
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
