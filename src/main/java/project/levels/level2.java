package project.levels;

import project.gameObjects.*;
import project.gameObjects.enemies.Mushroom;
import project.gameObjects.enemies.ToxicPlant;
import project.gameObjects.enemies.Turtle;
import project.gameStuff.Level;
import project.gameStuff.Section;
import project.managers.LevelManager;

public class level2 extends Level {
    public level2() {
        setLevelNum(2);
        Section section1 = new Section();
        {
            section1.setBackground("/images/bg/bg2.jpeg");
            section1.setSectionNum(0);

            for (int i = 0; i < 6; i++) {
                section1.addBlockTable(BlockType.Ground, 7, 3, 0 + 500*i);
                section1.addBlock(new Block(BlockType.Simple, 280 + i * 300 , 150));
                section1.addBlock(new Block(BlockType.Empty, 316 + i * 300 , 160));
                section1.addBlock(new Block(BlockType.Simple, 352 + i * 300 , 150));
                section1.addBlock(new Block(BlockType.Empty, 388 + i * 300 , 160));
                section1.addBlock(new Block(BlockType.Simple, 424 + i * 300 , 150));
            }


            section1.addBlockTable(BlockType.Ground, 20, 4, 2700); // 1080

            section1.setEndPoint(new Flag(1800,120));
//            section1.setEndPoint(new Flag(300, 230));

        }
        Section section2 = new Section();
        {
            section2.setBackground("/images/bg/bg2.jpeg");
            section2.setSectionNum(1);

            section2.addBlockTable(BlockType.Ground, 10, 3, 0);
            section2.addBlockTable(BlockType.Ground, 5, 4, 360);
            section2.addEnemy(new Mushroom(450,220));
            section2.addBlockTable(BlockType.Ground, 10, 3, 540);
            section2.addBlockTable(BlockType.Ground, 5, 4, 900);
            section2.addEnemy(new Mushroom(1000,220));
            section2.addBlockTable(BlockType.Ground, 10, 3, 1080);
            section2.addBlockTable(BlockType.Ground, 5, 4, 1440);

            section2.addBlockTable(BlockType.Ground, 20, 3, 1800);

            section2.addBlock(new Block(BlockType.ContainCoin, 2000, 155));
            section2.addBlock(new Block(BlockType.Empty, 2036, 155));
            section2.addBlock(new Block(BlockType.ContainCoin, 2072, 155));
            section2.addBlock(new Block(BlockType.Simple, 2108, 155));
            section2.addBlock(new Block(BlockType.Bonus, 2144, 155));
            section2.addBlock(new Block(BlockType.Simple, 2180, 155));
            section2.addBlock(new Block(BlockType.Bonus, 2216, 155));
            section2.addBlock(new Block(BlockType.Simple, 2252, 155));

            section2.addBlockTable(BlockType.Ground,20,3,2700);



//            section2.setEndPoint(new Flag(3126, 286));
            section2.setEndPoint(new Flag(2800, 255));
        }
        Section section3 = new Section();
        {
            section1.setBackground("/images/bg/bg2.jpeg");
            section3.setSectionNum(2);
            section3.addBlockTable(BlockType.Ground, 5, 5, 0);
            section3.addBlockTable(BlockType.Ground, 5, 4, 180);
            section3.addCoin(new Coin(220, 228));
            section3.addCoin(new Coin(300, 228));
            section3.addBlockTable(BlockType.Ground, 5, 3, 360);
            section3.addBlockTable(BlockType.Ground, 5, 2, 540);
            section3.addCoin(new Coin(580, 300));
            section3.addCoin(new Coin(660, 300));
            section3.addBlockTable(BlockType.Ground, 5, 3, 720);
            section3.addBlockTable(BlockType.Ground, 15, 3, 1100);
            section3.addEnemy(new Turtle(1200, 212));
            section3.addBlock(new Block(BlockType.Simple, 1280, 150));
            section3.addBlock(new Block(BlockType.Empty, 1316, 150));
            section3.addBlock(new Block(BlockType.Simple, 1352, 150));
            section3.addBlock(new Block(BlockType.Bonus, 1388, 150));
            section3.addBlock(new Block(BlockType.Simple, 1424, 150));
            section3.addBlockTable(BlockType.Ground, 15, 3, 1800);
            section3.addPipe(new Pipe(PipeType.Medium, 1900, 212, false)); // SECRET PIPE HERE
            section3.addPipe(new Pipe(PipeType.Medium, 2200, 212, false));
            section3.addEnemy(new Mushroom(2050, 240));
            section3.addBlockTable(BlockType.Ground, 5, 3, 2500);
            Pipe pipe = new Pipe(PipeType.Medium, 2560, 212, false);
            section3.addPipe(pipe);
            section3.addEnemy(new ToxicPlant(pipe));

            section3.addBlockTable(BlockType.Ground, 5, 3, 2750);
            Pipe pipe1 = new Pipe(PipeType.Medium, 2810, 212, false);
            section3.addPipe(pipe1);
            section3.addEnemy(new ToxicPlant(pipe1));
            section3.addBlockTable(BlockType.Ground, 15, 2, 3000);


//            section3.json.setEndPoint(new Flag(3126, 286));
            section3.setEndPoint(new Flag(200, 204));

        }
        Section section4 = new Section();
        {
            section1.setBackground("/images/bg/bg2.jpeg");
            section4.setSectionNum(3);

            section4.addBlockTable(BlockType.Ground, 10, 3, 0);
            section4.addBlockTable(BlockType.Ground, 10, 3, 400);

            section4.setEndPoint(new Flag(400, 286));
        }
        Section section5 = new Section();
        {
            section1.setBackground("/images/bg/bg2.jpeg");
            section5.setSectionNum(4);

            section5.addBlockTable(BlockType.Ground, 10, 3, 0);
            section5.addBlockTable(BlockType.Ground, 10, 3, 400);

            section5.setEndPoint(new Flag(400, 286));
        }

        addSection(section1);
        addSection(section2);
        addSection(section3);
        addSection(section4);
        addSection(section5);

        LevelManager.getInstance().addLevel(this);
    }
}
