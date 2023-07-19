package project.sceneControllers;

import javafx.fxml.FXML;
import org.w3c.dom.Text;
import project.StorageController;
import project.UsersData;
import project.gameStuff.GameData;

import java.awt.*;

public class CheckPointAskController {
    public void payed(){
        System.out.println(GameData.getInstance().getMoneyAmount());
        UsersData.getInstance().getCurrentUser().setCoin(UsersData.getInstance().getCurrentUser().getCoin() - GameData.getInstance().getMoneyAmount());
        StorageController.getInstance().save(GameData.getInstance().getCurrentLevel(), GameData.getInstance().getCurrentSection());
    }
}
