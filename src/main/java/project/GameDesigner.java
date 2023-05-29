package project;

import javafx.scene.layout.Pane;
import project.gameObjects.Block;

import project.gameObjects.BlockType;

import java.util.ArrayList;
import java.util.List;

public abstract class GameDesigner {
    private  Pane pane;
    private  List<Block> blockList = new ArrayList<>();

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public abstract void setBlocks();
    public abstract void setBlockInAirList();

    public void addBlockTable(BlockType type, int row, int column, double startX) {
        for (int i = 1; i <= column; i++) {
            for (int j = 0; j < row; j++) {
                double x = startX + j * Block.getWidth();
                double y = pane.getHeight()- i * Block.getHeight();
                Block b = new Block(type,x, y);
                blockList.add(b);
                pane.getChildren().add(b);
            }
        }
    }
}
