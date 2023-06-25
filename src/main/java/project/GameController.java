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
import project.gameObjects.*;
import project.gameStuff.GameData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController implements Runnable {
    private static GameController instance;
    private final Group root;
    private final Scene scene;
    private final User currentUser = UsersData.getInstance().getCurrentUser();
    private final Character character = currentUser.getSelectedCharacter();
    private final double gravity = GameData.getInstance().getCurrentSection().getGravity();
    private boolean upPressed = false;
    private boolean startScrolling = false;
    private boolean onBlock = true;

    private final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), e -> {
        character.move();
        if (startScrolling && character.isMoving()) {
            moveMap(character.getSpeed() * 20 / 1000.0);
        }
    }));
    private final Timeline timelinePrime = new Timeline(new KeyFrame(Duration.millis(200), e -> character.setFrame()));
    private final  Timeline timelineSwordMove = new Timeline(new KeyFrame(Duration.millis(100), e -> swordMove()));

    private final List<Block> blockList = GameData.getInstance().getCurrentSection().getBlockList();
    private final List<Coin> coinList = GameData.getInstance().getCurrentSection().getCoinList();
    private final List<Pipe> pipeList = GameData.getInstance().getCurrentSection().getPipeList();
    private final List<Item> itemList = new ArrayList<>();

    private Sword sword = null;
    private boolean isSoundMenuClosed = true;

    public GameController(Scene scene, Group rt) {
        this.root = rt;
        this.scene = scene;
        run();
    }

    @Override
    public void run() {
        // it used to be in constructor before implementing runnable
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
                case D -> {
                    character.setScaleX(1);
                    character.setSpeed(Math.abs(character.getSpeedo()));
                    character.setScaleY(1);
                    character.setMoving(true);
                }
                case A -> {
                    character.setScaleX(-1);
                    character.setSpeed(Math.abs(character.getSpeedo()) * -1);
                    character.setScaleY(1);
                    character.setAbleToMove(true);
                }
                case S -> {
                    character.setImage(character.getImageSit());
                    character.setScaleY(0.8);
                }
                case W -> {
                    if (character.isAbleToJumpAgain()) {
                        upPressed = true;
                        character.setJumping(true);
                        character.setVy(character.getJumpVelocity());
                        character.setAbleToJumpAgain(false);
                    }
                }
                case ESCAPE -> {
                    try {
                        isSoundMenuClosed = false;
                        timeline.pause();
                        timelinePrime.pause();
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/soundMenu.fxml"));
                        Parent root = loader.load();
                        Scene sc = new Scene(root, 396, 292);
                        stage.setScene(sc);
                        stage.setResizable(false);
                        stage.setOnCloseRequest(e -> {
                            isSoundMenuClosed = true;
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
                        Sword sword = new Sword(character.getX(), character.getY(), character.getFitHeight());
                        this.sword = sword;
                        root.getChildren().add(sword);
                        character.setSwordCooledDown(false);
                    }
                }
            }
        });
        scene.setOnKeyReleased(KeyEvent -> {
            switch (KeyEvent.getCode()) {
                case D, A -> {
                    character.setMoving(false);
                    character.setSpeed(0);
                    character.setImage(character.getImg());
                }
                case S -> {
                    //character.setImage(character.getImg());
                    character.setFrame();
                    character.setScaleY(1);
                    character.setImage(character.getImg());
                }
                case W -> {
                    upPressed = false;
                    character.setJumping(false);
                }
            }
        });
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (startScrolling && character.isMoving()) {
                    moveMap(character.getSpeed() * 20 / 1000.0);
                }
            }
        };
        animationTimer.start();
        character.yProperty().addListener((observableValue, oldVal, newVal) -> {
            // check jump limit
            if (character.getVy() <= 0) {
                character.setAbleToJumpAgain(true);
            }
            /* height limit
            if (newVal.doubleValue() < 0) {
                character.setVy(2);
            }*/
        });
        character.xProperty().addListener((observableValue, oldVal, newVal) -> {
            // width limit
            if (newVal.doubleValue() <= 0 || newVal.doubleValue() + character.getFitWidth() > scene.getWidth()) {
                character.setSpeed(0);
            }
            //scrolling
            if (newVal.doubleValue() >= 450) {
                startScrolling = true;
                character.setAbleToMove(false);
            }
        });
    }
    public void swordMove() {
        // speed is 2 block per second so its 0.2 block per 100 millis
        double blockWidth = GameObjectsInfo.getInstance().getBlockWidth();
        // moving
        if (sword != null) {
            if (sword.getX() >= sword.getStartX()) {
                if (sword.getX() - sword.getStartX() >= blockWidth * 4) {
                    sword.setTurnBack(true);
                }
                if (!sword.isTurnBack()) sword.setX(sword.getX() + 0.2 * blockWidth);
                if (sword.isTurnBack()) sword.setX(sword.getX() - 0.2 * blockWidth);
            } else {
                root.getChildren().remove(sword);
                timelineSwordMove.stop();
                sword = null;
                Timeline chill = new Timeline(new KeyFrame(Duration.seconds(3), e -> character.setSwordCooledDown(true)));
                chill.setCycleCount(1);
                chill.playFromStart();
            }
            // collision
        }
    }


    public void moveMap(Double dx) {
        for (Pipe pipe : pipeList) {
            double x = pipe.getX();
            x -= dx;
            pipe.setX(x);
        }
        for (Block block : blockList) {
            double x = block.getX();
            x -= dx;
            block.setX(x);
        }

        for (Coin coin : coinList) {
            double x = coin.getX();
            x -= dx;
            coin.setX(x);
        }
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isOnBlock() {
        return onBlock;
    }

    public void setOnBlock(boolean onBlock) {
        this.onBlock = onBlock;
    }
}
