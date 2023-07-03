package project.gameStuff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.GameController;
import project.UsersData;
import project.characters.Character;
import project.gameObjects.bossFight.BossEnemy;
import project.managers.CollisionManager;

public class GameData {
    @JsonIgnore
    private static GameData instance;
    @JsonIgnore
    private Scene scene;
    @JsonIgnore
    private Group root;
    @JsonIgnore
    private Level currentLevel;
    private Section currentSection;
    @JsonIgnore
    private GameController gameController;
    @JsonIgnore
    private BossEnemy bossEnemy;

    private int score;

    @JsonIgnore
    private Stage stage;
    @JsonIgnore
    private boolean isBossScene;
    private int levelNum;
    public GameData() {
    }

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public void increaseScore(int score){
        this.score += score;
        System.out.println(this.score);
    }

    public void decreaseScore(int score){
        this.score -= score;
    }
    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Section getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(Section currentSection) {
        this.currentSection = currentSection;
        GameData.getInstance().getGameController().setEndPoint(currentSection.getEndPoint());
        CollisionManager.getInstance().UpdateCollisionManagerList(currentSection);
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public GameController getGameController() {
        if(gameController == null)
            gameController = new GameController(scene,root);
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public BossEnemy getBossEnemy() {
        return bossEnemy;
    }

    public void setBossEnemy(BossEnemy bossEnemy) {
        this.bossEnemy = bossEnemy;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public double getHeight() {
        return stage.getHeight();
    }

    public double getWidth() {
        return stage.getWidth();
    }

    public boolean isBossScene() {
        return isBossScene;
    }

    public void setBossScene(boolean bossScene) {
        isBossScene = bossScene;
    }

    public int getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }
}
