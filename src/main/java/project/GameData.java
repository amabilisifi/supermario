package project;

import project.gameObjects.Block;

import java.util.ArrayList;
import java.util.List;

public class GameData {
    private List<Block> blockList = new ArrayList<>();

    public GameData() {
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<Block> blockList) {
        this.blockList = blockList;
    }
}
