package project.levels;

import javafx.fxml.Initializable;
import project.gameObjects.*;
import project.gameObjects.bossFight.BossEnemy;
import project.gameObjects.bossFight.FireBall;
import project.gameObjects.bossFight.KingKoopa;
import project.gameObjects.bossFight.NukeButton;
import project.gameObjects.enemies.Spiny;
import project.gameObjects.enemies.ToxicPlant;
import project.gameStuff.Level;
import project.gameStuff.Section;
import project.gameStuff.SectionDesigner;

import java.net.URL;
import java.util.ResourceBundle;

public class temp extends Level {
    public temp() {
        Section section1 = new Section();
        //section one elements
        {
//            section1.addBlockTable(BlockType.Ground, 6, 3, 0);
//            section1.addBlockTable(BlockType.Ground, 10, 4, 216);
//            Block block = new Block(BlockType.Bonus, 100, 160);
//            section1.addBlock(block);
//            Block block1 = new Block(BlockType.Bonus, 136, 160);
//            section1.addBlock(block1);
//            Block block2 = new Block(BlockType.Bonus, 172, 160);
//            section1.addBlock(block2);
//            section1.addBlockTable(BlockType.Ground,20,5,576);
//
//            Spiny mushroom = new Spiny(600,220);
//            Turtle t = new Turtle(350,250);
//            section1.addEnemy(t);
//            section1.addEnemy(mushroom);

//            Coin coin = new Coin(95, 270);
//            Coin coin1 = new Coin(135, 270);
//            section1.addCoin(coin1);
//            section1.addCoin(coin);

//            Pipe pipe = new Pipe(PipeType.Medium, 0, 220);
//            Pipe pipe1 = new Pipe(PipeType.Medium,400,178);
////            section1.addPipe(pipe);
//            section1.addPipe(pipe1);
//            ToxicPlant toxicPlant = new ToxicPlant(pipe1);
//            section1.addEnemy(toxicPlant);
            section1.addBlockTable(BlockType.Ground,50,3,0);
//            section1.addBlockTable(BlockType.Ground,15,4,360);
            section1.setEndPoint(new Flag(4000,200));
            section1.setBackground("/images/bg/bg1.jpeg");

            BossEnemy kingKoopa = new KingKoopa(620,130);
            section1.addEnemy(kingKoopa);
            SectionDesigner.getInstance().addToRoot(new FireBall(kingKoopa));

            section1.setGravity(1.3);
        }

        setLevelNum(0);
        addSection(section1);
    }
}
