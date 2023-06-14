package project;

import project.gameObjects.*;

import java.util.List;

public abstract class Section {
    private List<Block> blockList;
    private List<Pipe> pipeList;
    private List<Item> itemList;
    private List<Coin> coinList;
    private double gravity;

    public Section() {
    }

    public Section(List<Block> blockList, List<Pipe> pipeList, List<Item> itemList, List<Coin> coinList, double gravity) {
        this.blockList = blockList;
        this.pipeList = pipeList;
        this.itemList = itemList;
        this.coinList = coinList;
        this.gravity = gravity;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public abstract void setBlockList();

    public List<Pipe> getPipeList() {
        return pipeList;
    }

    public abstract void setPipeList();

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList() {
        this.itemList = itemList;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    public abstract void setCoinList();

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity){
        this.gravity = gravity;
    }
    public void addBlockTable(BlockType type, int row, int column, double startX) {
        for (int i = 1; i <= column; i++) {
            for (int j = 0; j < row; j++) {
                double x = startX + j * GameObjectsInfo.getInstance().getBlockWidth();
                double y = 400 - i * GameObjectsInfo.getInstance().getBlockHeight();
                Block b = new Block(type, x, y);
                blockList.add(b);
            }
        }
    }
    public void addBlock(Block block){
        blockList.add(block);
    }
}
