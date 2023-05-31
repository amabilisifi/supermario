package project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.Characters.Character;
import project.gameObjects.Block;
import project.gameObjects.BlockType;
import project.gameObjects.Item;
import project.gameObjects.ItemType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    AnchorPane pane;
    private final User currentUser = UserData.getInstance().getCurrentUser();
    private final Character character = currentUser.getSelectedCharacter();

    private final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), e -> move()));
    private final double startX = 10;
    private final double startY = 100;
    private final double gravity = 1.5;

    private List<Block> blockList = new ArrayList<>();

    private boolean upPressed = false;

    private List<Item> itemList  = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        character.setCurrentX(startX);
        character.setCurrentY(startY);
        pane.getChildren().add(character);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
        Timeline timelinePrime = new Timeline(new KeyFrame(Duration.millis(150), e -> character.setFrame()));
        timelinePrime.setCycleCount(Animation.INDEFINITE);
        timelinePrime.playFromStart();

        addBlockTable(BlockType.Ground, 4, 3, 60);
        Block block = new Block(BlockType.ContainManyCoins,100,170);
        pane.getChildren().add(block);
        blockList.add(block);

        pane.setOnKeyPressed(KeyEvent -> {
            switch (KeyEvent.getCode()) {
                case D -> {
                    character.setScaleX(1);
                    character.setSpeed(Math.abs(character.getSpeedo()));
                    character.setScaleY(1);
                }
                case A -> {
                    character.setScaleX(-1);
                    character.setSpeed(Math.abs(character.getSpeedo()) * -1);
                    character.setScaleY(1);
                }
                case S -> {
                    character.setImage(character.getImageSit());
                    character.setScaleY(0.8);
                }
                case W -> {
                    if (character.isAbleToJumpAgain()) {
                        upPressed = true;
                        character.setVy(character.getJumpVelocity());
                        character.setAbleToJumpAgain(false);
                    }
                }
            }
        });
        pane.setOnKeyReleased(KeyEvent -> {
            switch (KeyEvent.getCode()) {
                case D, A -> {
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
                }
            }
        });
        character.yProperty().addListener((observableValue, oldVal, newVal) -> {
            // check jump limit
            if (character.getVy() >= 0) {
                character.setAbleToJumpAgain(true);
            }
            // height limit
            if (newVal.doubleValue() < 0) {
                character.setVy(2);
            }
        });
        character.xProperty().addListener((observableValue, oldVal, newVal) -> {
            // width limit
            if (newVal.doubleValue() <= 0 || newVal.doubleValue() + character.getFitWidth() > pane.getWidth()) {
                character.setSpeed(0);
            }
        });
    }
    public void move() {
        //fall
        double dt = 20 / 1000.0;
        character.setVy(character.getVy() + (gravity * dt));
        // moving
        character.setCurrentX(character.getCurrentX() + character.getSpeed() * dt);
        character.setCurrentY(character.getCurrentY() + character.getVy() * dt);
        // collision blocks
        collisionWithBlocks();
    }
    public void collisionWithBlocks() {
        Bounds marioBounds = character.getBoundsInParent();
        for (Block block : blockList) {
            Bounds blockBounds = block.getBoundsInParent();
            if (blockBounds.intersects(marioBounds)) {
                double dy = character.getCurrentY() - block.getCurrentY();
                double dx = character.getCurrentX() - block.getCurrentX();

                if (dy < 0) {
//                    character.setCurrentY(block.getCurrentY() - character.getFitHeight());
                    character.setOnBlock(true);
                    if(!upPressed)
                        character.setVy(0);
                    if(block.getBlockType()==BlockType.Slime){
                        character.setVy(character.getJumpVelocity()*1.5);
                    }
                } else if (dy >= 0) {
                    character.setVy(0);
                    if(block.getBlockType()==BlockType.Simple){
                        pane.getChildren().remove(block);
                        blockList.remove(block);
                    }if(block.getBlockType()==BlockType.Bonus && block.getItemLeft()>=0){
                        Item item = new Item(block);
                        itemList.add(item);
                        pane.getChildren().add(item);
                    }
                    if((block.getBlockType()== BlockType.ContainCoin || block.getBlockType()==BlockType.ContainManyCoins) && block.getItemLeft()>=0){
                        Item item = new Item(ItemType.Coin,block);
                        itemList.add(item);
                        block.setItemLeft(block.getItemLeft()-1);
                        pane.getChildren().add(item);
                    }
                } else if (dx < 0) {
//                    character.setCurrentX(block.getCurrentX() - character.getFitWidth() - 1.2);
                    character.setSpeed(0);
                } else if (dx >= 0) {
//                    character.setCurrentX(block.getCurrentX() + block.getFitWidth() + 1);
                    character.setSpeed(0);
                }
                break;
            } else {
                character.setOnBlock(false);
            }
        }
    }
    public void addBlockTable(BlockType type, int row, int column, double startX) {
        for (int i = 1; i <= column; i++) {
            for (int j = 0; j < row; j++) {
                double x = startX + j * GameInfo.getInstance().getBlockWidth();
                double y = 400 - i * GameInfo.getInstance().getBlockHeight();
                Block b = new Block(type, x, y);
                blockList.add(b);
                pane.getChildren().add(b);
            }
        }
    }
    public void goHome(ActionEvent event) throws IOException {
        timeline.stop();
        String path = "src/main/resources/GameData/" + UserData.getInstance().getCurrentUser().getName() + "/Inventory/purchasedCharacters.json";
        JsonManager manager = new JsonManager(path);
        manager.writeArray(UserData.getInstance().getCurrentUser().getPurchasedCharacters());
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxmls/homePage.fxml"));
        Parent root = homeLoader.load();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.show();
    }
}
