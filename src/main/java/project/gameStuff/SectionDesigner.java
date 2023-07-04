package project.gameStuff;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.UsersData;
import project.characters.Character;
import project.gameObjects.*;
import project.gameObjects.bossFight.BossEnemy;
import project.gameObjects.enemies.Enemy;
import project.managers.CollisionManager;
import project.managers.JsonManager;
import project.managers.SoundPlayer;

import java.io.IOException;


public class SectionDesigner {
    private static SectionDesigner instance;
    private Group root;
    private Section section;
    private boolean isBossScene;
    private SoundPlayer soundPlayer;
    private SoundPlayer bossSoundPlayer;
    private Stage stage;

    public SectionDesigner(Group root, Section section) {
        this.root = root;
        this.section = section;
        soundPlayer = new SoundPlayer("src/main/resources/media/Barbie Girl.mp3");
        SoundPlayer.setMainSoundPlayer(soundPlayer);
        soundPlayer.play();
        soundPlayer.playOnRepeat();
    }

    public void paint(Section targetSection) {
        System.out.println(targetSection.isUserHaveCheckPointSaved());
        if(targetSection.isUserHaveCheckPointSaved()){
            try {
                System.out.println(targetSection.getSavedCheckPointPath());
            JsonManager manager = new JsonManager(targetSection.getSavedCheckPointPath());
                targetSection = manager.readObject(Section.class);
                System.out.println("df");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Section finalTargetSection = targetSection;
        Timeline timeSection = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            finalTargetSection.setTime(finalTargetSection.getTime() - 1);
            HUI.getInstance().setTime(finalTargetSection.getTime());
        }));
        timeSection.setCycleCount(Animation.INDEFINITE);
        timeSection.playFromStart();
        GameData.getInstance().setCurrentSection(targetSection);
        CollisionManager.getInstance().setCollisionWithEnd(false);
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        character.setX(10);
        GameData.getInstance().getGameController().setScrollLimit(false);
        for (Block block : targetSection.getBlockList()) {
            root.getChildren().add(block);
        }
        for (Enemy enemy : targetSection.getEnemyList()) {
            root.getChildren().add(enemy);
            if (enemy instanceof BossEnemy) {
                GameData.getInstance().setBossEnemy((BossEnemy) enemy);
            }
        }
        for (Pipe pipe : targetSection.getPipeList()) {
            root.getChildren().add(pipe);
        }
        for (Coin coin : targetSection.getCoinList()) {
            root.getChildren().add(coin);
        }
        for (CheckPoint checkPoint: targetSection.getCheckPointList()){
            root.getChildren().add(checkPoint);
        }
        root.getChildren().add(targetSection.getEndPoint());
        HUI.getInstance().paintHUI(root);
    }

    public void clearSection(Section section) {
        for (Block block : section.getBlockList()) {
            root.getChildren().remove(block);
        }
        for (Enemy enemy : section.getEnemyList()) {
            root.getChildren().remove(enemy);
        }
        for (Pipe pipe : section.getPipeList()) {
            root.getChildren().remove(pipe);
        }
        for (Coin coin : section.getCoinList()) {
            root.getChildren().remove(coin);
        }
        root.getChildren().remove(section.getEndPoint());
    }

    public static SectionDesigner getInstance() {
        if (instance == null)
            instance = new SectionDesigner(GameData.getInstance().getRoot(), GameData.getInstance().getCurrentSection());
        return instance;
    }

    public void moveMap(Double dx, Section section) {
        for (Pipe pipe : section.getPipeList()) {
            double x = pipe.getX();
            x -= dx;
            pipe.setX(x);
        }
        for (Block block : section.getBlockList()) {
            double x = block.getX();
            x -= dx;
            block.setX(x);
        }

        for (Coin coin : section.getCoinList()) {
            double x = coin.getX();
            x -= dx;
            coin.setX(x);
        }
        for (Item item : section.getItemList()) {
            double x = item.getX();
            x -= dx;
            item.setX(x);
        }
        for (Enemy enemy : section.getEnemyList()) {
            double x = enemy.getX();
            x -= dx;
            enemy.setX(x);
        }
        double x = section.getEndPoint().getX();
        x -= dx;
        section.getEndPoint().setX(x);
    }

    public void changeSceneToBossScene() {
        System.out.println("HI BOss");
        soundPlayer.stop();
        GameData.getInstance().setBossScene(true);

        bossSoundPlayer = new SoundPlayer("src/main/resources/media/EldenRingMainTheme.mp3");
        SoundPlayer.setMainSoundPlayer(bossSoundPlayer);
        bossSoundPlayer.play();
        bossSoundPlayer.playOnRepeat();

        section = GameData.getInstance().getCurrentSection();
        addBlockJail();
        UsersData.getInstance().getCurrentUser().getSelectedCharacter().setNearBossEnemy(true);
        ProgressBar hpBar = new ProgressBar(1);
        hpBar.setOpacity(0.6);
        hpBar.setStyle("-fx-accent: red");
        hpBar.setPrefWidth(500);
        hpBar.setPrefHeight(30);
        hpBar.setLayoutX(150);
        hpBar.setLayoutY(15);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            hpBar.setProgress(GameData.getInstance().getBossEnemy().getHP() / 20.0);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

        root.getChildren().add(hpBar);
    }

    public void addToRoot(ImageView object) {
        root.getChildren().add(object);
    }

    public void removeFromRoot(ImageView object) {
        root.getChildren().remove(object);
    }

    public void addBlockJail() {
        addBlockColumn(0, 11);
        addBlockColumn(GameData.getInstance().getWidth() - GameObjectsInfo.getInstance().getBlockWidth() - 8, 11);
    }

    public void addBlockColumn(double x, double num) {
        for (int i = 0; i < num; i++) {
            Block block = new Block(BlockType.Ground, x, GameData.getInstance().getHeight() - i * GameObjectsInfo.getInstance().getBlockHeight());
            section.getBlockList().add(block);
            root.getChildren().add(block);
        }
    }

    public boolean isBossScene() {
        return isBossScene;
    }

    public void setBossScene(boolean bossScene) {
        isBossScene = bossScene;
        if (bossScene) {
            changeSceneToBossScene();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }
}
