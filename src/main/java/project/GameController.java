package project;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.characters.Character;
import project.gameObjects.EndPoint;
import project.gameObjects.Laser;
import project.gameObjects.Sword;
import project.gameObjects.bossFight.BossEnemy;
import project.gameObjects.enemies.Direction;
import project.gameStuff.GameData;
import project.gameStuff.HUI;
import project.gameStuff.SectionDesigner;
import project.managers.CollisionManager;
import project.managers.JsonManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class GameController implements Runnable {
    private final Group root;
    private final Scene scene;
    private final User currentUser = UsersData.getInstance().getCurrentUser();
    private final Character character = currentUser.getSelectedCharacter();
    private boolean startScrolling = false;
    private boolean scrollLimit = false;

    private final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), e -> {
        if (!character.isGrabbed()) {
            character.move();
        }
        if (startScrolling && character.isMoving() && !scrollLimit && !character.isNearBossEnemy()) {
            SectionDesigner.getInstance().moveMap(character.getVx() * 17 / 1000.0, GameData.getInstance().getCurrentSection());
        }
    }));
    private final Timeline timelinePrime = new Timeline(new KeyFrame(Duration.millis(200), e -> character.setFrame()));
    private final Timeline timelineSwordMove = new Timeline(new KeyFrame(Duration.millis(10), e -> swordMove()));
    private EndPoint endPoint = GameData.getInstance().getCurrentSection().getEndPoint();

    private Sword sword = null;

    private int leftPressedNum;
    private int rightPressedNum;

    public GameController(Scene scene, Group rt) {
        this.root = rt;
        this.scene = scene;
        run();
    }

    @Override
    public void run() {
        // it used to be in constructor before implementing runnable in last project
        double startX = 10;
        double startY = 100;
        character.setX(startX);
        character.setY(startY);
        root.getChildren().add(character);


        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

        timelinePrime.setCycleCount(Animation.INDEFINITE);
        timelinePrime.playFromStart();

        scene.setOnKeyPressed(KeyEvent -> {
            switch (KeyEvent.getCode()) {
                case F -> {
                    character.setScaleX(1);
                    character.setDirection(Direction.Right);
                    character.setVx(Math.abs(2 * character.getSpeedo()));
                    character.setScaleY(1);
                    character.setMoving(true);
                }
                case D -> {
                    if (character.isDizzy()) {
                        // A stuff
                        character.setScaleX(-1);
                        character.setDirection(Direction.Left);
                        character.setVx(Math.abs(character.getSpeedo()) * -1);
                        character.setScaleY(1);
                        character.setAbleToMove(true);
                        leftPressedNum++;
                    } else {
                        character.setScaleX(1);
                        character.setDirection(Direction.Right);
                        character.setVx(Math.abs(character.getSpeedo()));
                        character.setScaleY(1);
                        character.setMoving(true);
                        rightPressedNum++;
                    }
                }
                case A -> {
                    if (character.isDizzy()) {
                        // D stuff
                        character.setScaleX(1);
                        character.setDirection(Direction.Right);
                        character.setVx(Math.abs(character.getSpeedo()));
                        character.setScaleY(1);
                        character.setMoving(true);
                        rightPressedNum++;
                    } else {
                        character.setScaleX(-1);
                        character.setDirection(Direction.Left);
                        character.setVx(Math.abs(character.getSpeedo()) * -1);
                        character.setScaleY(1);
                        character.setAbleToMove(true);
                        leftPressedNum++;
                    }
                }
                case S -> {
                    if (character.isDizzy()) {
                        // W stuff
                        if (character.isAbleToJumpAgain()) {
                            character.setUpPressed(true);
                            character.setJumping(true);
                            character.setVy(character.getJumpVelocity());
                            character.setAbleToJumpAgain(false);
                        }
                    } else {
                        character.setImage(character.getImageSit());
                        character.setScaleY(0.8);
                    }
                }
                case W -> {
                    if (character.isDizzy()) {
                        // S stuff
                        character.setImage(character.getImageSit());
                        character.setScaleY(0.8);
                    } else {
                        if (character.isAbleToJumpAgain()) {
                            character.setUpPressed(true);
                            character.setJumping(true);
                            character.setVy(character.getJumpVelocity());
                            character.setAbleToJumpAgain(false);
                        }
                    }
                }
                case ESCAPE -> {
                    try {
                        timeline.pause();
                        timelinePrime.pause();
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/soundMenu.fxml"));
                        Parent root = loader.load();
                        Scene sc = new Scene(root, 396, 292);
                        stage.setScene(sc);
                        stage.setResizable(false);
                        stage.setOnCloseRequest(e -> {
                            timeline.play();
                            timelinePrime.play();
                        });
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case L -> {
                    if (character.isSwordCooledDown() && currentUser.getCoin() > 3) {
                        currentUser.setCoin(currentUser.getCoin() - 3);
                        Sword sword = new Sword(character.getX(), character.getY(), character.getFitHeight(), character.getDirection());
                        this.sword = sword;
                        root.getChildren().add(sword);
                        character.setSwordCooledDown(false);
                        timelineSwordMove.setCycleCount(Animation.INDEFINITE);
                        timelineSwordMove.playFromStart();
                    }
                }
                case SPACE -> {
                    // if (character.isOnBlock()) {
                    Laser laser = new Laser();
                    root.getChildren().add(laser);
                    if (SectionDesigner.getInstance().isBossScene()) {
                        BossEnemy bossEnemy = GameData.getInstance().getBossEnemy();
                        double distance = Math.abs(character.getX() - bossEnemy.getX());
                        double blockSize = GameObjectsInfo.getInstance().getBlockWidth();
                        double pD = distance / (blockSize);
                        int random = (int) (8 * Math.random());
                        if (pD >= random)
                            bossEnemy.jump(false);
                    }
                }
                case K ->{
                    File file  = new File("src/main/resources/test.Json");
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    JsonManager manager = new JsonManager("src/main/resources/test.Json");
                    try {
                        manager.writeObject(character);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case M ->{
                    JsonManager manager = new JsonManager("src/main/resources/test.Json");
                    try {
                        Character character1 = manager.readObject(Character.class);
                        System.out.println(character1.getX());
                        root.getChildren().add(character1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        scene.setOnKeyReleased(KeyEvent -> {
            switch (KeyEvent.getCode()) {
                case D, A, F -> {
                    character.setMoving(false);
                    character.setVx(0);
                    character.setImage(character.getImg());
                }
                case S -> {
                    if (character.isDizzy()) {
                        // W stuff
                        character.setUpPressed(false);
                        character.setJumping(false);
                    } else {
                        character.setFrame();
                        character.setScaleY(1);
                        character.setImage(character.getImg());
                    }
                }
                case W -> {
                    if (character.isDizzy()) {
                        // S stuff
                        character.setFrame();
                        character.setScaleY(1);
                        character.setImage(character.getImg());
                    } else {
                        character.setUpPressed(false);
                        character.setJumping(false);
                    }
                }
            }
        });
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
//                System.out.println(endPoint.getX() - character.getX());
                if (endPoint.getX() - character.getX() <= 72) {
                    scrollLimit = true;
                    startScrolling = false;
                    character.setAbleToMove(true);
                }
            }
        };
        animationTimer.start();
        character.yProperty().addListener((observableValue, oldVal, newVal) -> {
            // check jump limit
            if (character.getVy() <= 0) {
                character.setAbleToJumpAgain(true);
            }
            if(newVal.doubleValue()>=380){
                character.setHearts(character.getHearts()-1);
                HUI.getInstance().setHearts(character.getHearts());
                GameData.getInstance().decreaseScore(30);
                CollisionManager.getInstance().reset();
            }
            /* height limit
            if (newVal.doubleValue() < 0) {
                character.setVy(2);
            }*/
        });
        character.xProperty().addListener((observableValue, oldVal, newVal) -> {
            // width limit
            if (newVal.doubleValue() <= 0 || newVal.doubleValue() + character.getFitWidth() > scene.getWidth()) {
                character.setVx(0);
            }
            //scrolling
            if (newVal.doubleValue() >= 450 && !scrollLimit && !character.isNearBossEnemy()) {
                startScrolling = true;
                character.setAbleToMove(false);
            } else {
                startScrolling = false;
                character.setAbleToMove(true);
            }
        });
    }

    public void swordMove() {
        // speed is 2 block per second so its 0.02 block per 10 millis
        double blockWidth = GameObjectsInfo.getInstance().getBlockWidth();
        // moving
        if (sword != null) {
            if ((sword.getX() >= sword.getStartX() && sword.getScaleX() == 1) ||
                    (sword.getX() <= character.getX() && sword.getScaleX() == -1)) {
                if (Math.abs(sword.getX() - sword.getStartX()) >= blockWidth * 4) {
                    sword.setTurning(true);
                }
                if (sword.getScaleX() == 1) {
                    if (!sword.isTurning()) sword.setX(sword.getX() + 0.02 * blockWidth);
                    if (sword.isTurning()) sword.setX(sword.getX() - 0.02 * blockWidth);
                }
                if (sword.getScaleX() == -1) {
                    if (!sword.isTurning()) sword.setX(sword.getX() - 0.02 * blockWidth);
                    if (sword.isTurning()) sword.setX(sword.getX() + 0.02 * blockWidth);
                }
            } else {
                root.getChildren().remove(sword);
                timelineSwordMove.stop();
                sword = null;
                Timeline chill = new Timeline(new KeyFrame(Duration.seconds(3), e -> character.setSwordCooledDown(true)));
                chill.setCycleCount(1);
                chill.playFromStart();
            }
            // collision
            if (sword != null)
                CollisionManager.getInstance().collisionWeapon(sword);
        }
    }

    public boolean check10LeftRight() {
        if (leftPressedNum > 5 && rightPressedNum > 5) {
            System.out.println("freeee");
            return true;
        }
        return false;
    }

    public void resetLeftRight() {
        leftPressedNum = 0;
        rightPressedNum = 0;
    }

    // getter setter

    public void setEndPoint(EndPoint endPoint) {
        this.endPoint = endPoint;
    }

    public void setScrollLimit(boolean scrollLimit) {
        this.scrollLimit = scrollLimit;
    }
}
