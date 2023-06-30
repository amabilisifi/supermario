package project.gameStuff;

import javafx.scene.Group;
import project.gameObjects.Block;
import project.gameObjects.Coin;
import project.gameObjects.Pipe;
import project.gameObjects.enemies.Enemy;


public class SectionDesigner {
    private static SectionDesigner instance;
    private Group root;
    private Section section;

    public SectionDesigner(Group root, Section section) {
        this.root = root;
        this.section = section;
    }
    public void paint(Section section){
        GameData.getInstance().setCurrentSection(section);
        for(Block block:section.getBlockList()){
            root.getChildren().add(block);
        }
        for (Enemy enemy:section.getEnemyList()){
            root.getChildren().add(enemy);
        }
        for(Pipe pipe:section.getPipeList()){
            root.getChildren().add(pipe);
        }
        for(Coin coin:section.getCoinList()){
            root.getChildren().add(coin);
        }
        root.getChildren().add(section.getEndPoint());
    }
    public  void clearSection(){
        System.out.println(section.getSectionNum()+"    section num");
        for(Block block:section.getBlockList()){
            root.getChildren().remove(block);
        }
        for (Enemy enemy:section.getEnemyList()){
            root.getChildren().remove(enemy);
        }
        for(Pipe pipe:section.getPipeList()){
            root.getChildren().remove(pipe);
        }
        for(Coin coin:section.getCoinList()){
            root.getChildren().remove(coin);
        }
        root.getChildren().remove(section.getEndPoint());
        System.out.println("done");
    }
    public static SectionDesigner getInstance(){
        if(instance == null)
            instance = new SectionDesigner(GameData.getInstance().getRoot(), GameData.getInstance().getCurrentSection());
        return instance;
    }
}
