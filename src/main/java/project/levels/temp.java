package project.levels;

import project.gameObjects.*;
import project.gameObjects.bossFight.KingKoopa;
import project.gameObjects.markers.Flag;
import project.gameStuff.Level;
import project.gameStuff.Section;

public class temp extends Level {
    public temp() {
        Section section1 = new Section();
        //section one elements
        {
//            section1.json.addBlockTable(BlockType.Ground, 6, 3, 0);
//            section1.json.addBlockTable(BlockType.Ground, 10, 4, 216);
//            Block block = new Block(BlockType.Bonus, 100, 160);
//            section1.json.addBlock(block);
//            Block block1 = new Block(BlockType.Bonus, 136, 160);
//            section1.json.addBlock(block1);
//            Block block2 = new Block(BlockType.Bonus, 172, 160);
//            section1.json.addBlock(block2);
//            section1.json.addBlockTable(BlockType.Ground,20,5,576);
//
//            Spiny mushroom = new Spiny(600,220);
//            Turtle t = new Turtle(350,250);
//            section1.json.addEnemy(t);
//            section1.json.addEnemy(mushroom);

//            Coin coin = new Coin(95, 270);
//            Coin coin1 = new Coin(135, 270);
//            section1.json.addCoin(coin1);
//            section1.json.addCoin(coin);

//            Pipe pipe = new Pipe(PipeType.Medium, 0, 220);
//            Pipe pipe1 = new Pipe(PipeType.Medium,400,178);
////            section1.json.addPipe(pipe);
//            section1.json.addPipe(pipe1);
//            ToxicPlant toxicPlant = new ToxicPlant(pipe1);
//            section1.json.addEnemy(toxicPlant);

            section1.addBlockTable(BlockType.Ground,50,3,0);
//            section1.json.addBlockTable(BlockType.Ground,15,4,360);

            section1.addEnemy(new KingKoopa(600,120));
            section1.setEndPoint(new Flag(4000,200));
            section1.setBackground("/images/bg/bg1.jpeg");

            section1.addBlock(new Block(BlockType.Simple,100,200));


            section1.setGravity(1.3);
        }

        setLevelNum(0);
        addSection(section1);
    }
}
