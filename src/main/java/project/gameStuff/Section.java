package project.gameStuff;

import project.GameObjectsInfo;
import project.gameObjects.*;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private List<Block> blockList = new ArrayList<>();
    private List<Pipe> pipeList = new ArrayList<>();
    private List<Item> itemList = new ArrayList<>();
    private List<Coin> coinList = new ArrayList<>();
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

    public List<Pipe> getPipeList() {
        return pipeList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList() {
        this.itemList = itemList;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

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

    public void addCoin(Coin coin){
        coinList.add(coin);
    }
    public void addPipe(Pipe pipe){
        pipeList.add(pipe);
    }
    public void addItem(Item item){
        itemList.add(item);
    }
}
