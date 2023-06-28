package project.levels;

import javafx.fxml.Initializable;
import project.gameObjects.Block;
import project.gameObjects.BlockType;
import project.gameObjects.Pipe;
import project.gameObjects.PipeType;
import project.gameObjects.enemies.Mushroom;
import project.gameObjects.enemies.Spiny;
import project.gameObjects.enemies.ToxicPlant;
import project.gameObjects.enemies.Turtle;
import project.gameStuff.Level;
import project.gameStuff.Section;

import java.net.URL;
import java.util.ResourceBundle;

public class temp extends Level implements Initializable {
    public temp() {
        Section section1 = new Section();
        //section one elements
        {
//            section1.addBlockTable(BlockType.Ground, 6, 3, 0);
//            section1.addBlockTable(BlockType.Ground, 10, 4, 216);
            Block block = new Block(BlockType.Bonus, 100, 160);
            section1.addBlock(block);
            Block block1 = new Block(BlockType.Bonus, 136, 160);
            section1.addBlock(block1);
            Block block2 = new Block(BlockType.Bonus, 172, 160);
            section1.addBlock(block2);
//            section1.addBlockTable(BlockType.Ground,20,5,576);
//
            Mushroom mushroom = new Mushroom(400,220);
//            Turtle turtle = new Turtle(350,250);
//            section1.addEnemy(turtle);
            section1.addEnemy(mushroom);

//            Coin coin = new Coin(95, 270);
//            Coin coin1 = new Coin(135, 270);
//            section1.addCoin(coin1);
//            section1.addCoin(coin);

//            Pipe pipe = new Pipe(PipeType.Medium, 0, 220);
//            Pipe pipe1 = new Pipe(PipeType.Medium,700,178);
////            section1.addPipe(pipe);
//            section1.addPipe(pipe1);
//            ToxicPlant toxicPlant = new ToxicPlant(pipe1);
//            section1.addEnemy(toxicPlant);
            section1.addBlockTable(BlockType.Ground,10,3,0);
            section1.addBlockTable(BlockType.Ground,15,4,360);

            section1.setGravity(1.3);
        }

        setLevelNum(0);
        addSection(section1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
