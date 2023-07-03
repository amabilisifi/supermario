package project.levels;

import project.gameObjects.*;
import project.gameObjects.enemies.Mushroom;
import project.gameObjects.enemies.ToxicPlant;
import project.gameObjects.enemies.Turtle;
import project.gameStuff.Level;
import project.gameStuff.Section;

public class level1 extends Level {
    public level1() {
        setLevelNum(1);
        Section section1 = new Section();
        {
            section1.setBackground("/images/bg/bg1.jpeg");
            section1.setSectionNum(0);
            section1.addCoin(new Coin(200, 260));
            section1.addCoin(new Coin(130, 260));
            section1.addBlockTable(BlockType.Ground, 10, 3, 0);
            section1.addBlockTable(BlockType.Ground, 8, 4, 360);
            section1.addBlockTable(BlockType.Ground, 12, 2, 540);
            section1.addBlockTable(BlockType.Ground, 10, 4, 1111);
            section1.addCoin(new Coin(1230, 230));
            section1.addCoin(new Coin(1260, 230));
            section1.addBlockTable(BlockType.Ground, 32, 3, 1471);
            section1.addBlockTable(BlockType.Ground, 30, 2, 2623);

            section1.addBlock(new Block(BlockType.ContainManyCoins, 146, 150));
            section1.addBlock(new Block(BlockType.Bonus, 182, 150));
            section1.addBlock(new Block(BlockType.ContainCoin, 218, 150));

            Pipe pipe = new Pipe(PipeType.Short, 480, 196, false);
            section1.addPipe(pipe);
            section1.addEnemy(new ToxicPlant(pipe));

            section1.addBlock(new Block(BlockType.Empty, 600, 100));
            section1.addBlock(new Block(BlockType.ContainCoin, 636, 100));
            section1.addCoin(new Coin(650, 70));
            section1.addBlock(new Block(BlockType.Empty, 672, 100));

            section1.addEnemy(new Mushroom(800, 290));
            section1.addCoin(new Coin(850, 300));

            section1.addBlock(new Block(BlockType.Simple, 1220, 145));
            section1.addBlock(new Block(BlockType.Simple, 1256, 145));
            section1.addCoin(new Coin(1274, 130));
            section1.addBlock(new Block(BlockType.Simple, 1292, 145));
            section1.addBlock(new Block(BlockType.Bonus, 1256, 80));

            section1.addEnemy(new Mushroom(2000, 254));
            section1.addPipe(new Pipe(PipeType.Medium, 1700, 213, false));
            section1.addBlock(new Block(BlockType.Simple, 1900, 145));
            section1.addBlock(new Block(BlockType.Simple, 1936, 145));
            section1.addBlock(new Block(BlockType.Bonus, 1972, 145));
            section1.addBlock(new Block(BlockType.Simple, 2008, 145));
            section1.addPipe(new Pipe(PipeType.Short, 2200, 233, false));
            section1.addBlock(new Block(BlockType.ContainManyCoins, 2350, 140));
            section1.addBlock(new Block(BlockType.Empty, 2386, 140));
            section1.addBlock(new Block(BlockType.ContainCoin, 2420, 140));
            section1.addPipe(new Pipe(PipeType.Long, 2555, 192, false));
            Pipe pipe1 = new Pipe(PipeType.Short, 2800, 269, false);
            section1.addPipe(pipe1);
            section1.addEnemy(new ToxicPlant(pipe1));
            section1.addCoin(new Coin(2822, 248));

            section1.addBlock(new Block(BlockType.Empty, 3000, 150));
            section1.addBlock(new Block(BlockType.ContainCoin, 3036, 150));
            section1.addBlock(new Block(BlockType.Empty, 3072, 150));
            section1.addBlock(new Block(BlockType.ContainCoin, 3108, 150));
            section1.addBlock(new Block(BlockType.Empty, 3144, 150));

//            section1.setEndPoint(new Flag(3126,105));
            section1.setEndPoint(new Flag(300, 230));

        }
        Section section2 = new Section();
        {
            section2.setBackground("/images/bg/bg1.jpeg");
            section2.setSectionNum(1);

            section2.addBlockTable(BlockType.Ground, 8, 3, 0);
            section2.addBlockTable(BlockType.Ground, 6, 2, 360);
            section2.addEnemy(new Mushroom(600, 280));
            section2.addBlockTable(BlockType.Ground, 27, 2, 670);
            section2.addPipe(new Pipe(PipeType.Medium, 800, 249, false));
            section2.addBlock(new Block(BlockType.ContainCoin, 400, 200));
            section2.addBlock(new Block(BlockType.Empty, 436, 200));
            section2.addBlock(new Block(BlockType.ContainCoin, 472, 200));
            section2.addBlock(new Block(BlockType.Simple, 938, 200));
            section2.addBlock(new Block(BlockType.Bonus, 974, 200));
            section2.addBlock(new Block(BlockType.Simple, 1010, 200));
            section2.addBlock(new Block(BlockType.Bonus, 1046, 200));
            section2.addBlock(new Block(BlockType.Simple, 1082, 200));
            Pipe pipe = new Pipe(PipeType.Medium, 1181, 249, false);
            section2.addPipe(pipe);
            section2.addEnemy(new ToxicPlant(pipe));
            section2.addEnemy(new Mushroom(1500, 164));
            section2.addCoin(new Coin(1400, 295));
            section2.addBlockTable(BlockType.Ground, 22, 3, 1642);
            section2.addCoin(new Coin(1750, 255));
            section2.addCoin(new Coin(1800, 255));
            section2.addBlock(new Block(BlockType.Simple, 2000, 150));
            section2.addBlock(new Block(BlockType.Empty, 2036, 150));
            section2.addBlock(new Block(BlockType.ContainCoin, 2072, 150));
            section2.addBlock(new Block(BlockType.Simple, 2108, 150));
            section2.addBlock(new Block(BlockType.Simple, 2144, 150));
            section2.addBlock(new Block(BlockType.Simple, 2180, 150));
            section2.addBlock(new Block(BlockType.Bonus, 2216, 150));
            section2.addBlock(new Block(BlockType.Simple, 2252, 150));
            section2.addEnemy(new Mushroom(2100, 120));
            section2.addCoin(new Coin(2400, 260));
            section2.addBlockTable(BlockType.Ground, 30, 2, 2434);
            section2.addPipe(new Pipe(PipeType.Long, 2500, 228, false));
            section2.addPipe(new Pipe(PipeType.Long, 2700, 228, false));
            section2.addPipe(new Pipe(PipeType.Long, 2900, 228, false));
            section2.addPipe(new Pipe(PipeType.Long, 3200, 228, false));


//            section2.setEndPoint(new Flag(3126, 286));
            section2.setEndPoint(new Flag(300, 230));
        }
        Section section3 = new Section();
        {
            section3.setBackground("/images/bg/bg1.jpeg");
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


//            section3.setEndPoint(new Flag(3126, 286));
            section3.setEndPoint(new Flag(200, 220));

        }
        Section section4 = new Section();
        {
            section4.setBackground("/images/bg/bg1.jpeg");
            section4.setSectionNum(3);

            section4.addBlockTable(BlockType.Ground,7,3,0);
            section4.setEndPoint(new Flag(3126, 286));
        }
        addSection(section1);
        addSection(section2);
        addSection(section3);
        addSection(section4);
    }
}
