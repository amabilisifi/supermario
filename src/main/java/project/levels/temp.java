package project.levels;

import project.Level;
import project.Section;
import project.gameObjects.Block;
import project.gameObjects.BlockType;
import project.gameObjects.Coin;
import project.gameObjects.Pipe;

import java.util.ArrayList;
import java.util.List;

public class temp extends Level {
    public temp() {
        Section section1 = new Section() {
            @Override
            public void setBlockList() {
                addBlockTable(BlockType.Ground, 6, 3, 0);
                addBlockTable(BlockType.Ground, 10, 4, 216);
                Block block = new Block(BlockType.Slime, 100, 160);
                addBlock(block);
            }

            @Override
            public void setPipeList() {

            }

            @Override
            public void setCoinList() {

            }
        };
        section1.setGravity(2.5);

        setLevelNum(0);
        addSection(section1);
    }
}
