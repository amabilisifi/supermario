package project.managers;

import javafx.geometry.Bounds;
import project.Level;
import project.UsersData;
import project.characters.Character;
import project.gameObjects.Block;
import project.gameObjects.BlockType;
import project.gameObjects.Item;
import project.gameObjects.ItemType;

import java.util.ArrayList;
import java.util.List;

public class CollisionManagerCharacter {
    private Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
    private List<Block> blockList = new ArrayList<>();
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
                    //if (!upPressed)
                        character.setVy(0);
                    if (block.getBlockType() == BlockType.Slime) {
                        character.setVy(character.getJumpVelocity() * 1.5);
                    }
                } else if (dy >= 0) {
                    character.setVy(0);
                    if (block.getBlockType() == BlockType.Simple) {
                        //root.getChildren().remove(block);
                        blockList.remove(block);
                    }
                    if (block.getBlockType() == BlockType.Bonus && block.getItemLeft() >= 0 && block.isAbleToGiveAnotherItem()) {
                        block.setAbleToGiveAnotherItem(false);
                        Item item = new Item(block);
                        //itemList.add(item);
                        //root.getChildren().add(item);
                        //block.setItemLeft(block.getItemLeft()-1);
                    }
                    if ((block.getBlockType() == BlockType.ContainCoin || block.getBlockType() == BlockType.ContainManyCoins) && block.getItemLeft() > 0 && block.isAbleToGiveAnotherItem()) {
                        block.setAbleToGiveAnotherItem(false);
                        Item item = new Item(ItemType.Coin, block);
                       // itemList.add(item);
                        block.setItemLeft(block.getItemLeft() - 1);
                       //root.getChildren().add(item);
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
}
