package project.gameStuff;

import javafx.scene.Group;
import javafx.scene.Scene;
import project.GameController;
import project.gameObjects.bossFight.BossEnemy;
import project.managers.CollisionManager;

public class GameData {
    private static GameData instance;
    private Scene scene;
    private Group root;
    private Level currentLevel;
    private Section currentSection;
    private GameController gameController;
    private BossEnemy bossEnemy;

    private int score;
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
}
