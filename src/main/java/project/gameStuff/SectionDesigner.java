package project.gameStuff;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.UsersData;
import project.gameObjects.*;
import project.gameObjects.bossFight.BossEnemy;
import project.gameObjects.enemies.Enemy;
import project.managers.SoundPlayer;

import java.net.URL;
import java.util.ResourceBundle;


public class SectionDesigner{
    private static SectionDesigner instance;
    private Group root;
    private Section section;
    private boolean isBossScene;
    private SoundPlayer soundPlayer;
    private SoundPlayer bossSoundPlayer;

    public SectionDesigner(Group root, Section section) {
        this.root = root;
        this.section = section;
        soundPlayer = new SoundPlayer("src/main/resources/media/Barbie Girl.mp3");
        SoundPlayer.setMainSoundPlayer(soundPlayer);
        soundPlayer.play();
        soundPlayer.playOnRepeat();
    }

    public void paint(Section section) {
        GameData.getInstance().setCurrentSection(section);
        for (Block block : section.getBlockList()) {
            root.getChildren().add(block);
        }
        for (Enemy enemy : section.getEnemyList()) {
            root.getChildren().add(enemy);
            if (enemy instanceof BossEnemy) {
                GameData.getInstance().setBossEnemy((BossEnemy) enemy);
            }
        }
        for (Pipe pipe : section.getPipeList()) {
            root.getChildren().add(pipe);
        }
        for (Coin coin : section.getCoinList()) {
            root.getChildren().add(coin);
        }
        root.getChildren().add(section.getEndPoint());
    }

    public void clearSection() {
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
        System.out.println("done");
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
        addBlockColumn(800 - GameObjectsInfo.getInstance().getBlockWidth() - 8, 11);
    }

    public void addBlockColumn(double x, double num) {
        for (int i = 0; i < num; i++) {
            Block block = new Block(BlockType.Ground, x, 400 - i * GameObjectsInfo.getInstance().getBlockHeight());
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
}
