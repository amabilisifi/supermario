package project.levels;

import javafx.fxml.Initializable;
import project.Level;
import project.Section;
import project.gameObjects.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class temp extends Level implements Initializable {
    public temp() {
        Section section1 = new Section();
        //section one elements
        {
            section1.addBlockTable(BlockType.Ground, 6, 3, 0);
            section1.addBlockTable(BlockType.Ground, 10, 4, 216);
            Block block = new Block(BlockType.Slime, 100, 160);
            section1.addBlock(block);

            Coin coin = new Coin(95, 270);
            Coin coin1 = new Coin(135, 270);
            section1.addCoin(coin1);
            section1.addCoin(coin);

            Pipe pipe = new Pipe(PipeType.Medium, 400, 178);
            section1.addPip(pipe);

            section1.setGravity(1.3);
        }

        setLevelNum(0);
        addSection(section1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("jiji");
    }
}
