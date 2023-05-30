package project.levels;

import javafx.fxml.Initializable;
import project.GameController;
import project.GameDesigner;
import project.gameObjects.BlockType;

import java.net.URL;
import java.util.ResourceBundle;

public class tempLevel extends GameDesigner implements Initializable {
    private GameController gameController = new GameController();


    @Override
    public void setBlocks() {
        addBlockTable(BlockType.Ground,4,2,0);
    }

    @Override
    public void setBlockInAirList() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setPane(gameController.pane);
        //pane is empty thou
        setBlocks();
    }
}
