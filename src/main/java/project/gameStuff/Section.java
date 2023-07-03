package project.gameStuff;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.GameObjectsInfo;
import project.gameObjects.*;
import project.gameObjects.enemies.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private List<Block> blockList = new ArrayList<>();
    private List<Pipe> pipeList = new ArrayList<>();
    private List<Item> itemList = new ArrayList<>();
    private List<Coin> coinList = new ArrayList<>();
    private double gravity = 1.3;
    private List<Enemy> enemyList = new ArrayList<>();
    private int sectionNum;

    private int time = 400;

    private EndPoint endPoint;
    private List<CheckPoint> checkPointList = new ArrayList<>();

    public Section() {
    }

    public Section(int sectionNum, int time, List<Block> blockList, List<Pipe> pipeList, List<Coin> coinList, List<Item> itemList, List<Enemy> enemyList, List<CheckPoint> checkPointList, EndPoint endPoint) {
        this.sectionNum = sectionNum;
        this.time = time;
        this.blockList = blockList;
        this.pipeList = pipeList;
        this.itemList = itemList;
        this.enemyList = enemyList;
        this.endPoint = endPoint;
        this.coinList = coinList;
        this.checkPointList = checkPointList;
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

    public List<Coin> getCoinList() {
        return coinList;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public void addBlockTable(BlockType type, int column, int row, double startX) {
        for (int i = 1; i <= row; i++) {
            for (int j = 0; j < column; j++) {
                double x = startX + j * GameObjectsInfo.getInstance().getBlockWidth();
                double y = 400 - i * GameObjectsInfo.getInstance().getBlockHeight();
                Block b = new Block(type, x, y);
                blockList.add(b);
            }
        }
    }

    public void addBlock(Block block) {
        blockList.add(block);
    }

    public void addBlockColumn(double x, double num) {
        for (int i = 0; i < num; i++) {
            Block block = new Block(BlockType.Ground, x, GameData.getInstance().getHeight() - i * GameObjectsInfo.getInstance().getBlockHeight());
            blockList.add(block);
        }
    }

    public void addCoin(Coin coin) {
        coinList.add(coin);
    }

    public void addPipe(Pipe pipe) {
        pipeList.add(pipe);
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void addEnemy(Enemy enemy) {
        enemyList.add(enemy);
    }

    public void setBackground(String string) {
        Image image = new Image(String.valueOf(getClass().getResource(string)));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(GameData.getInstance().getHeight());
        imageView.setFitWidth(GameData.getInstance().getWidth());
        SectionDesigner.getInstance().addToRoot(imageView);
    }

    public void addCheckPoint(CheckPoint checkPoint) {
        checkPointList.add(checkPoint);
    }

    // getter setter
    public EndPoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(EndPoint endPoint) {
        this.endPoint = endPoint;
    }

    public int getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(int sectionNum) {
        this.sectionNum = sectionNum;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<CheckPoint> getCheckPointList() {
        return checkPointList;
    }

    public void setCheckPointList(List<CheckPoint> checkPointList) {
        this.checkPointList = checkPointList;
    }
}
