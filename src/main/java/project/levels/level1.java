package project.levels;

import project.gameObjects.*;
import project.gameObjects.enemies.Mushroom;
import project.gameObjects.enemies.ToxicPlant;
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

            Pipe pipe = new Pipe(PipeType.Short, 480, 196);
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
            section1.addPipe(new Pipe(PipeType.Medium, 1700, 213));
            section1.addBlock(new Block(BlockType.Simple, 1900, 145));
            section1.addBlock(new Block(BlockType.Simple, 1936, 145));
            section1.addBlock(new Block(BlockType.Bonus, 1972, 145));
            section1.addBlock(new Block(BlockType.Simple, 2008, 145));
            section1.addPipe(new Pipe(PipeType.Short, 2200, 233));
            section1.addBlock(new Block(BlockType.ContainManyCoins, 2350, 140));
            section1.addBlock(new Block(BlockType.Empty, 2386, 140));
            section1.addBlock(new Block(BlockType.ContainCoin, 2420, 140));
            section1.addPipe(new Pipe(PipeType.Long, 2555, 192));
            Pipe pipe1 = new Pipe(PipeType.Short, 2800, 269);
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
            section2.addCoin(new Coin(150, 260));
            section2.addCoin(new Coin(239, 260));
            section2.addBlockTable(BlockType.Ground, 10, 5, 0);
            section2.setEndPoint(new Flag(300, 230));
        }
        addSection(section1);
        addSection(section2);
    }
}
