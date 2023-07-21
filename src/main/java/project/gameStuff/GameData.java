package project.gameStuff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.GameController;
import project.gameObjects.bossFight.BossEnemy;
import project.managers.CollisionManager;
import project.managers.SoundPlayer;

import java.util.ArrayList;
import java.util.List;

public class GameData {
    @JsonIgnore
    private static GameData instance = new GameData();
    @JsonIgnore
    private Scene scene ;
    @JsonIgnore
    private Group root = new Group();
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
    @JsonIgnore
    private SoundPlayer currentSoundPlayer;
    @JsonIgnore
    private int savedCheckPoint = 0;
    @JsonIgnore
    private int moneyAmount = 0;
    @JsonIgnore
    private SoundPlayer bgmPlayer;
    @JsonIgnore
    private List<Timeline> timelineList  = new ArrayList<>();
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
    public void pauseEverything(){
        System.out.println("pausing everything");
        for (Timeline timeline1:timelineList){
            timeline1.pause();
        }
    }
    public void playEverything(){
        System.out.println("playing everythingc");
        for (Timeline timeline1:timelineList){
            timeline1.play();
        }
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

    public SoundPlayer getCurrentSoundPlayer() {
        return currentSoundPlayer;
    }

    public void setCurrentSoundPlayer(SoundPlayer currentSoundPlayer) {
        this.currentSoundPlayer = currentSoundPlayer;
    }

    public void addTimeLine(Timeline timeline){
        timelineList.add(timeline);
    }

    public int getSavedCheckPoint() {
        return savedCheckPoint;
    }

    public void setSavedCheckPoint(int savedCheckPoint) {
        this.savedCheckPoint = savedCheckPoint;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public SoundPlayer getBgmPlayer() {
        return bgmPlayer;
    }

    public void setBgmPlayer(SoundPlayer bgmPlayer) {
        this.bgmPlayer = bgmPlayer;
    }
}
