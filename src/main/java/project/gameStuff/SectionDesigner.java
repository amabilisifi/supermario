package project.gameStuff;

import javafx.scene.Group;
import project.gameObjects.Block;
import project.gameObjects.Coin;
import project.gameObjects.Pipe;


public class SectionDesigner {
    private Group root;
    private Section section;

    public SectionDesigner(Group root, Section section) {
        this.root = root;
        this.section = section;
    }
    public void paint(){
        for(Block block:section.getBlockList()){
            root.getChildren().add(block);
        }
        for(Pipe pipe:section.getPipeList()){
            root.getChildren().add(pipe);
        }
        for(Coin coin:section.getCoinList()){
            root.getChildren().add(coin);
        }
    }
}
