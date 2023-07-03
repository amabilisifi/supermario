package project.gameStuff;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import project.GameObjectsInfo;
import project.gameObjects.*;
import project.gameObjects.enemies.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private final List<Block> blockList = new ArrayList<>();
    private final List<Pipe> pipeList = new ArrayList<>();
    private final List<Item> itemList = new ArrayList<>();
    private final List<Coin> coinList = new ArrayList<>();
    private double gravity = 1.3;
    private final List<Enemy> enemyList = new ArrayList<>();
    private int sectionNum;

    private int time;
    private int length;

    private ImageView endPoint;
    public Section() {
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

    public void setGravity(double gravity){
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
    public void addBlock(Block block){
        blockList.add(block);
    }
    public void addBlockColumn(double x, double num) {
        for (int i = 0; i < num; i++) {
            Block block = new Block(BlockType.Ground, x, GameData.getInstance().getHeight() - i * GameObjectsInfo.getInstance().getBlockHeight());
            blockList.add(block);
        }
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
    public void addEnemy(Enemy enemy){
        enemyList.add(enemy);
    }

    public void setBackground(String string){
        Image image = new Image(String.valueOf(getClass().getResource(string)));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(GameData.getInstance().getHeight());
        imageView.setFitWidth(GameData.getInstance().getWidth());
        SectionDesigner.getInstance().addToRoot(imageView);
    }

    public ImageView getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(ImageView endPoint) {
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
