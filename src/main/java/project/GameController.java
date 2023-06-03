package project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.Characters.Character;
import project.gameObjects.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Scene scene;
    private Group root;
    private final User currentUser = UserData.getInstance().getCurrentUser();
    private final Character character = currentUser.getSelectedCharacter();
    private final double startX = 10;
    private final double startY = 100;
    private final double gravity = 1.5;
    private List<Block> blockList = new ArrayList<>();
    private boolean upPressed = false;
    private List<Item> itemList = new ArrayList<>();
    private final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), e -> move()));
    private List<Coin> coinList = new ArrayList<>();
    private List<Pipe> pipeList = new ArrayList<>();

    public GameController(Scene scene, Group rt) {
        this.scene = scene;
        this.root = rt;
        character.setCurrentX(startX);
        character.setCurrentY(startY);
        root.getChildren().add(character);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
        Timeline timelinePrime = new Timeline(new KeyFrame(Duration.millis(200), e -> character.setFrame()));
        timelinePrime.setCycleCount(Animation.INDEFINITE);
        timelinePrime.playFromStart();

        addBlockTable(BlockType.Ground, 6, 3, 0);
        addBlockTable(BlockType.Ground, 10, 4, 216);
        Block block = new Block(BlockType.Bonus, 100, 160);
        root.getChildren().add(block);
        blockList.add(block);

        Coin coin = new Coin(95, 270);
        Coin coin1 = new Coin(135, 270);
        coinList.add(coin1);
        coinList.add(coin);
        root.getChildren().add(coin);
        root.getChildren().add(coin1);

        Pipe pipe = new Pipe(PipeType.Medium, 400, 178);
        pipeList.add(pipe);
        root.getChildren().add(pipe);

        GameInfo.getInstance().setBlockList(blockList);
        GameInfo.getInstance().setItemList(itemList);
        System.out.println(this.scene);

        this.scene.setOnKeyPressed(KeyEvent -> {
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
                case ESCAPE -> {
                    try {
                        timeline.pause();
                        timelinePrime.pause();
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/soundMenu.fxml"));
                        Parent root = loader.load();
                        Scene sc = new Scene(root, 440, 352);
                        stage.setScene(sc);
                        stage.setResizable(false);
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        this.scene.setOnKeyReleased(KeyEvent -> {
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
            if (newVal.doubleValue() <= 0 || newVal.doubleValue() + character.getFitWidth() > scene.getWidth()) {
                character.setSpeed(0);
            }
        });
    }
    public void hbh(){
        System.out.println("vgjvjgv jgu");
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
        collisionWithItems();
        collisionWithCoin();
        collisionWithPipe();
    }

    public void collisionWithBlocks() {
        Bounds marioBounds = character.getBoundsInParent();
        for (Block block : blockList) {
            Bounds blockBounds = block.getBoundsInParent();
            if (blockBounds.intersects(marioBounds)) {
                double dy = character.getCurrentY() - block.getCurrentY();
//                double dx = character.getCurrentX() - block.getCurrentX();

                if (dy < 0) {
//                    character.setCurrentY(block.getCurrentY() - character.getFitHeight());
                    character.setOnBlock(true);
                    if (!upPressed)
                        character.setVy(0);
                    if (block.getBlockType() == BlockType.Slime) {
                        character.setVy(character.getJumpVelocity() * 1.5);
                    }
                } else if (dy >= 0) {
                    character.setVy(0);
                    if (block.getBlockType() == BlockType.Simple) {
                        root.getChildren().remove(block);
                        blockList.remove(block);
                    }
                    if (block.getBlockType() == BlockType.Bonus && block.getItemLeft() >= 0 && block.isAbleToGiveAnotherItem()) {
                        block.setAbleToGiveAnotherItem(false);
                        Item item = new Item(block);
                        itemList.add(item);
                        root.getChildren().add(item);
                        //block.setItemLeft(block.getItemLeft()-1);
                    }
                    if ((block.getBlockType() == BlockType.ContainCoin || block.getBlockType() == BlockType.ContainManyCoins) && block.getItemLeft() > 0 && block.isAbleToGiveAnotherItem()) {
                        block.setAbleToGiveAnotherItem(false);
                        Item item = new Item(ItemType.Coin, block);
                        itemList.add(item);
                        block.setItemLeft(block.getItemLeft() - 1);
                        root.getChildren().add(item);
                    }
                }

//                 else if (dx < 0) {
////                    character.setCurrentX(block.getCurrentX() - character.getFitWidth() - 1.2);
//                    character.setSpeed(0);
//                } else if (dx >= 0) {
////                    character.setCurrentX(block.getCurrentX() + block.getFitWidth() + 1);
//                    character.setSpeed(0);
//                }
                break;
            } else {
                character.setOnBlock(false);
            }
        }
        double dt = 20.0 / 1000;
        double deltaX = character.getSpeed() * dt;
        double yRunner = character.getY();
        double deltaY = character.getVy() * dt;
        for (int i = 0; i < blockList.size(); i++) {
            Block block = blockList.get(i);
            //right of mario
            double rightRunner = character.getX() + character.getFitWidth() + deltaX;
            if (rightRunner > block.getX() && rightRunner < block.getX() + block.getFitWidth() &&
                    yRunner <= block.getY() && yRunner + character.getFitHeight() >= block.getY() + block.getFitHeight()) {
                character.setSpeed(0);
            }
            //left Of mario
            double leftRunner = character.getX() + deltaX;
            if (leftRunner > block.getX() && leftRunner < block.getX() + block.getFitWidth() &&
                    yRunner <= block.getY() && yRunner + character.getFitHeight() >= block.getY() + block.getFitHeight()) {
                character.setSpeed(0);
            }
//            //down Of mario
//            double downRunner = character.getY() + character.getFitHeight();
//            if (character.getX() + character.getFitWidth() >= block.getX() && character.getX() < block.getX() + block.getFitWidth() && downRunner >= block.getY() && downRunner <= block.getY() + block.getFitHeight()) {
//                character.setOnBlock(true);
//                if(!upPressed)
//                    character.setVy(0);
//                if(block.getBlockType()==BlockType.Slime){
//                    character.setVy(character.getJumpVelocity()*1.5);
//                }
//            }else {
//                character.setOnBlock(false);
//            }
//            //top of mario
//            double topRunner = character.getY() + deltaY;
//            if (character.getX() + character.getFitWidth() > block.getX() && character.getX() + character.getFitWidth() < block.getX() + block.getFitWidth() && topRunner > block.getY() && topRunner < block.getY() + block.getFitHeight()) {
//                character.setVy(0);
//                    if(block.getBlockType()==BlockType.Simple){
//                        pane.getChildren().remove(block);
//                        blockList.remove(block);
//                    }if(block.getBlockType()==BlockType.Bonus && block.getItemLeft()>=0 && block.isAbleToGiveAnotherItem()){
//                        block.setAbleToGiveAnotherItem(false);
//                        Item item = new Item(block);
//                        itemList.add(item);
//                        pane.getChildren().add(item);
//                        //block.setItemLeft(block.getItemLeft()-1);
//                    }
//                    if((block.getBlockType()== BlockType.ContainCoin || block.getBlockType()==BlockType.ContainManyCoins) && block.getItemLeft()>=0 && block.isAbleToGiveAnotherItem()){
//                        block.setAbleToGiveAnotherItem(false);
//                        Item item = new Item(ItemType.Coin,block);
//                        itemList.add(item);
//                        block.setItemLeft(block.getItemLeft()-1);
//                        pane.getChildren().add(item);
//                    }
//            }
        }
    }

    public void collisionWithItems() {
        Item collisionItem = null;
        for (Item item : itemList) {
            if (character.intersects(item.getBoundsInParent())) {
                item.setObtained(true);
                collisionItem = item;
                item.getBlock().setAbleToGiveAnotherItem(true);
                switch (item.getItemType()) {
                    case Coin -> {
                        currentUser.setCoin(currentUser.getCoin() + 1);
                        System.out.println(currentUser.getCoin());
                    }
                }
                if (item.getBlock().getBlockType() == BlockType.ContainCoin) {
                    item.getBlock().setBlockType(BlockType.Simple);
                }
                if (item.getBlock().getBlockType() == BlockType.ContainManyCoins && item.getBlock().getItemLeft() <= 0) {
                    item.getBlock().setBlockType(BlockType.Empty);
                    item.getBlock().setImage(new Image(String.valueOf(getClass().getResource("/images/Blocks/empty.PNG"))));
                }
                root.getChildren().remove(item);
            }
        }
        itemList.remove(collisionItem);
    }

    public void collisionWithCoin() {
        Coin collisionCoin = null;
        for (Coin coin : coinList) {
            if (character.intersects(coin.getBoundsInParent())) {
                collisionCoin = coin;
                currentUser.setCoin(currentUser.getCoin() + 1);
                System.out.println(currentUser.getCoin());
            }
        }
        coinList.remove(collisionCoin);
        root.getChildren().remove(collisionCoin);
    }

    public void collisionWithPipe() {
        Bounds marioBounds = character.getBoundsInParent();
        for (Pipe pipe : pipeList) {
            Bounds blockBounds = pipe.getBoundsInParent();
            if (blockBounds.intersects(marioBounds)) {
                double dy = character.getCurrentY() - pipe.getCurrentY();

                if (dy < 0) {
                    character.setOnBlock(true);
                    if (!upPressed)
                        character.setVy(0);
                }
                break;
            } else {
                character.setOnBlock(false);
            }
        }
        double dt = 20.0 / 1000;
        double deltaX = character.getSpeed() * dt;
        double yRunner = character.getY();
        for (Pipe pipe : pipeList) {
            //right of mario
            double rightRunner = character.getX() + character.getFitWidth() + deltaX;
            if (rightRunner > pipe.getX() && rightRunner < pipe.getX() + pipe.getFitWidth() &&
                    yRunner + character.getFitHeight() <= pipe.getY() + pipe.getFitHeight() && yRunner + character.getFitHeight() > pipe.getY() + 5) {
                character.setSpeed(0);
            }
            //left Of mario
            double leftRunner = character.getX() + deltaX;
            if (leftRunner > pipe.getX() && leftRunner < pipe.getX() + pipe.getFitWidth() &&
                    yRunner + character.getFitHeight() <= pipe.getY() + pipe.getFitHeight() && yRunner + character.getFitHeight() > pipe.getY() + 5) {
                character.setSpeed(0);
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
                root.getChildren().add(b);
            }
        }
    }
}
