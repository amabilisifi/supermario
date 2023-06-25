package project.managers;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.image.Image;
import project.GameController;
import project.User;
import project.UsersData;
import project.characters.Character;
import project.gameObjects.*;
import project.gameStuff.GameData;
import project.gameStuff.Section;

import java.util.List;

public class CollisionManager {
    private static CollisionManager instance;
    private final User currentUser = UsersData.getInstance().getCurrentUser();
    private final Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
    private final Section section = GameData.getInstance().getCurrentSection();
    private final List<Block> blockList = section.getBlockList();
    private final List<Pipe> pipeList = section.getPipeList();
    private final List<Coin> coinList = section.getCoinList();
    private final List<Item> itemList = section.getItemList();
    private final Group root = GameData.getInstance().getRoot();
    private final GameController gameController = GameData.getInstance().getGameController();
    private boolean upPressed = gameController.isUpPressed();
    public void collisionCharacter(){
        collisionWithBlocksChar();
        collisionWithItemsChar();
        collisionWithCoinChar();
        collisionWithPipeChar();
    }
    public void collisionWithBlocksChar() {
        Bounds marioBounds = character.getBoundsInParent();
        boolean flag = false;
        for (Block block : blockList) {
            Bounds blockBounds = block.getBoundsInParent();
            if (blockBounds.intersects(marioBounds)) {
                double dy = character.getY() - block.getCurrentY();
//                double dx = character.getCurrentX() - block.getCurrentX();

                if (dy <= 0 && !character.isJumping()) {
                    character.setY(block.getCurrentY() - character.getFitHeight());
                    character.setOnBlock(true);
                    flag = true;
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
                        GameData.getInstance().getCurrentSection().addItem(item);
                        root.getChildren().add(item);
                        //block.setItemLeft(block.getItemLeft()-1);
                    }
                    if ((block.getBlockType() == BlockType.ContainCoin || block.getBlockType() == BlockType.ContainManyCoins) && block.getItemLeft() > 0 && block.isAbleToGiveAnotherItem()) {
                        block.setAbleToGiveAnotherItem(false);
                        Item item = new Item(ItemType.Coin, block);
                        GameData.getInstance().getCurrentSection().addItem(item);
                        block.setItemLeft(block.getItemLeft() - 1);
                        root.getChildren().add(item);
                    }
                }

                 /* else if (dx < 0) {
                    character.setSpeed(0);
//                    character.setCurrentX(block.getCurrentX() - character.getFitWidth() - 1.2);
                } else if (dx >= 0) {
//                    character.setCurrentX(block.getCurrentX() + block.getFitWidth() + 1);
                    character.setSpeed(0);
                }*/
                break;
            }
        }
        if (!flag) {
            character.setOnBlock(false);
        }
        double dt = 20.0 / 1000;
        double deltaX = character.getSpeed() * dt;
        double yRunner = character.getY();
        //double deltaY = character.getVy() * dt;
        for (Block block : blockList) {
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
            /*down Of mario
            double downRunner = character.getY() + character.getFitHeight();
            if (character.getX() + character.getFitWidth() >= block.getX() && character.getX() < block.getX() + block.getFitWidth() && downRunner >= block.getY() && downRunner <= block.getY() + block.getFitHeight()) {
                character.setOnBlock(true);
                if(!upPressed)
                    character.setVy(0);
                if(block.getBlockType()==BlockType.Slime){
                    character.setVy(character.getJumpVelocity()*1.5);
                }
            }else {
                character.setOnBlock(false);
            }
            //top of mario
            double topRunner = character.getY() + deltaY;
            if (character.getX() + character.getFitWidth() > block.getX() && character.getX() + character.getFitWidth() < block.getX() + block.getFitWidth() && topRunner > block.getY() && topRunner < block.getY() + block.getFitHeight()) {
                character.setVy(0);
                    if(block.getBlockType()==BlockType.Simple){
                        pane.getChildren().remove(block);
                        blockList.remove(block);
                    }if(block.getBlockType()==BlockType.Bonus && block.getItemLeft()>=0 && block.isAbleToGiveAnotherItem()){
                        block.setAbleToGiveAnotherItem(false);
                        Item item = new Item(block);
                        itemList.add(item);
                        pane.getChildren().add(item);
                        //block.setItemLeft(block.getItemLeft()-1);
                    }
                    if((block.getBlockType()== BlockType.ContainCoin || block.getBlockType()==BlockType.ContainManyCoins) && block.getItemLeft()>=0 && block.isAbleToGiveAnotherItem()){
                        block.setAbleToGiveAnotherItem(false);
                        Item item = new Item(ItemType.Coin,block);
                        itemList.add(item);
                        block.setItemLeft(block.getItemLeft()-1);
                        pane.getChildren().add(item);
                    }
            }*/
        }
    }

    public void collisionWithCoinChar() {
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

    public void collisionWithPipeChar() {
        Bounds marioBounds = character.getBoundsInParent();
        for (Pipe pipe : pipeList) {
            Bounds blockBounds = pipe.getBoundsInParent();
            if (blockBounds.intersects(marioBounds)) {
                double dy = character.getY() - pipe.getCurrentY();

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

    public void collisionWithItemsChar() {
        Item collisionItem = null;
        for (Item item : itemList) {
            if (character.intersects(item.getBoundsInParent())) {
                item.setObtained(true);
                collisionItem = item;
                item.getBlock().setAbleToGiveAnotherItem(true);
                if (item.getItemType() == ItemType.Coin) {
                    currentUser.setCoin(currentUser.getCoin() + 1);
                    System.out.println(currentUser.getCoin());
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
    public void collisionItemWithObjects(){
        for (Item i : itemList) {
            collisionItemWithBlock(i);
//            collisionItemWithPipe(i);
        }
    }
    public void collisionItemWithBlock(Item i){
        boolean flag = false;
        for (Block b : blockList) {
            if (i.intersects(b.getBoundsInParent())) {
                double dy = i.getY() - b.getCurrentY();
                if (dy < 0) {
                    i.setOnBlock(true);
                    flag = true;
                }
                double dt = 20.0 / 1000;
                double deltaX = i.getSpeed() * dt;
                double yRunner = i.getY();
                double deltaY = i.getVy() * dt;
                //right of item
                double rightRunner = i.getX() + i.getFitWidth();
                if (rightRunner >= b.getX() && rightRunner < b.getX() + b.getFitWidth() && yRunner >= b.getY() && yRunner + i.getFitHeight() <= b.getY() + b.getFitHeight()) {
//                rightBlock = true;
//                this.setSpeed(this.getSpeed() * -1);
                }
//            b.setImage(new Image(String.valueOf(getClass().getResource("/images/Blocks/empty.PNG"))));
                //left Of item
                double leftRunner = i.getX();
                if (leftRunner > b.getX() && leftRunner < b.getX() + b.getFitWidth() &&
                        yRunner <= b.getY() && yRunner + i.getFitHeight() >= b.getY() + b.getFitHeight()) {
//                this.setSpeed(this.getSpeed() * -1);
                }
                break;
            }
        }
        if(!flag)
            i.setOnBlock(false);
    }
    public void collisionItemWithPipe(Item i){
        for (Pipe p : pipeList) {
            if (i.intersects(p.getBoundsInParent())) {
//                if (this.intersects(this.sceneToLocal(p.localToScene(p.getBoundsInLocal())))) {
                double dy = i.getY() - p.getCurrentY();
                if (dy < 0) {
                    i.setOnBlock(true);
                }
                double dt = 20.0 / 1000;
                double deltaX = i.getSpeed() * dt;
                double yRunner = i.getY();
                double deltaY = i.getVy() * dt;
                //right of item
                double rightRunner = i.getX() + i.getFitWidth();
                if (rightRunner >= p.getX() && rightRunner < p.getX() + p.getFitWidth() && yRunner >= p.getY() && yRunner + i.getFitHeight() <= p.getY() + p.getFitHeight()) {
//                rightBlock = true;
//                this.setSpeed(this.getSpeed() * -1);
                    System.out.println("IIIIIIIIIIIIIIIIIIII");
                }
//            p.setImage(new Image(String.valueOf(getClass().getResource("/images/Blocks/empty.PNG"))));
                //left Of item
                double leftRunner = i.getX();
                if (leftRunner > p.getX() && leftRunner < p.getX() + p.getFitWidth() &&
                        yRunner <= p.getY() && yRunner + i.getFitHeight() >= p.getY() + p.getFitHeight()) {
//                this.setSpeed(this.getSpeed() * -1);
                    System.out.println("IIIIIIIIIIIIIIIIIIII");
                }
                break;
            }
        }
    }

    public static CollisionManager getInstance() {
        if (instance == null)
            instance = new CollisionManager();
        return instance;
    }
}
