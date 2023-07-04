package project.levels;

import project.gameObjects.*;
import project.gameObjects.bossFight.KingKoopa;
import project.gameObjects.enemies.Mushroom;
import project.gameObjects.enemies.Spiny;
import project.gameObjects.enemies.ToxicPlant;
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
            section3.setBackground("/images/bg/bg2.jpeg");
            section3.setSectionNum(2);

            section3.addBlockTable(BlockType.Ground, 8, 3, 0);
            section3.addBlockTable(BlockType.Ground, 6, 2, 360);
            section3.addEnemy(new Spiny(580, 276));
            section3.addBlockTable(BlockType.Ground, 27, 2, 670);
            section3.addBlock(new Block(BlockType.ContainCoin, 400, 200));
            section3.addBlock(new Block(BlockType.Empty, 436, 200));
            section3.addBlock(new Block(BlockType.ContainCoin, 472, 200));
            section3.addBlock(new Block(BlockType.Simple, 938, 200));
            section3.addBlock(new Block(BlockType.Bonus, 974, 200));
            section3.addBlock(new Block(BlockType.Simple, 1010, 200));
            section3.addBlock(new Block(BlockType.Bonus, 1046, 200));
            section3.addBlock(new Block(BlockType.Simple, 1082, 200));
            Pipe pipe = new Pipe(PipeType.Medium, 1181, 249, false);
            section3.addPipe(pipe);
            section3.addEnemy(new ToxicPlant(pipe));
            section3.addEnemy(new Mushroom(1500, 164));
            section3.addCoin(new Coin(1400, 295));
            section3.addBlockTable(BlockType.Ground, 22, 3, 1642);
            section3.addCoin(new Coin(1750, 255));
            section3.addCoin(new Coin(1800, 255));
            section3.addBlock(new Block(BlockType.Simple, 2000, 150));
            section3.addBlock(new Block(BlockType.Empty, 2036, 150));
            section3.addBlock(new Block(BlockType.ContainCoin, 2072, 150));
            section3.addBlock(new Block(BlockType.Simple, 2108, 150));
            section3.addBlock(new Block(BlockType.Simple, 2144, 150));
            section3.addBlock(new Block(BlockType.Simple, 2180, 150));
            section3.addBlock(new Block(BlockType.Bonus, 2216, 150));
            section3.addBlock(new Block(BlockType.Simple, 2252, 150));
            section3.addEnemy(new Mushroom(2100, 115));
            section3.addCoin(new Coin(2400, 260));
            section3.addBlockTable(BlockType.Ground, 30, 2, 2434);
            section3.addPipe(new Pipe(PipeType.Long, 2500, 228, false));
            Pipe pipe1 = (new Pipe(PipeType.Long, 2700, 228, false));
            section3.addPipe(pipe1);
            section3.addEnemy(new ToxicPlant(pipe1));
            section3.addPipe(new Pipe(PipeType.Long, 2900, 228, false));
            section3.addPipe(new Pipe(PipeType.Long, 3200, 228, false));


            section3.setEndPoint(new Flag(3126, 286));
//            section3.setEndPoint(new Flag(300, 230));

        }
        Section section4 = new Section();
        {
            section4.setBackground("/images/bg/bg2.jpeg");
            section4.setSectionNum(3);

            section4.addBlockTable(BlockType.Ground, 10, 3, 0);
            section4.addBlockTable(BlockType.Ground, 10, 4, 450);
            section4.addBlockTable(BlockType.Ground, 10, 3, 900);
            // gang
            Pipe sr = new Pipe(PipeType.Medium,1100,215,true);
            section4.addPipe(sr);
            Section secretSection = new Section();
            secretSection.setEndPoint(new PussyCat(1000,247));
            secretSection.addBlockTable(BlockType.Ground,10,3,0);
            secretSection.addBlockTable(BlockType.Ground,5,4,360);
            secretSection.addEnemy(new Mushroom(520,140));
            secretSection.addBlockTable(BlockType.Ground,15,3,700);
            sr.setSection(secretSection);

            section4.addBlockTable(BlockType.Ground, 10, 2, 1350);
            section4.addBlockTable(BlockType.Ground, 10, 3, 1800);

            section4.setEndPoint(new Flag(2000, 155));
        }
        Section section5 = new Section();
        {
            section5.setBackground("/images/bg/bg2.jpeg");
            section5.setSectionNum(4);

            section5.addBlockTable(BlockType.Ground, 10, 3, 0);
            section5.addBlockTable(BlockType.Ground, 30, 3, 420);
            section5.addEnemy(new KingKoopa(1100,120));
            section5.setTime(1000);

            section5.addBlock(new Block(BlockType.Bonus, 500, 145));
            section5.addBlock(new Block(BlockType.ContainCoin, 536, 145));
            section5.addBlock(new Block(BlockType.Bonus, 572, 145));
            section5.addBlock(new Block(BlockType.Simple, 608, 145));
            section5.addBlock(new Block(BlockType.Bonus, 644, 145));
            section5.addBlock(new Block(BlockType.ContainManyCoins, 680, 145));


            section5.setEndPoint(new Flag(2000, 286));
        }

        addSection(section1);
        addSection(section2);
        addSection(section3);
        addSection(section4);
        addSection(section5);

        LevelManager.getInstance().addLevel(this);
    }
}
