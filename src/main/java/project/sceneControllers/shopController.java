package project.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.characters.Character;
import project.characters.*;
import project.managers.JsonManager;
import project.UsersData;
import project.managers.Page.PageType;
import project.managers.Page.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class shopController implements Initializable {
    @FXML
    ImageView characterViewer;
    @FXML
    Text characterNameText;
    @FXML
    Text characterPriceText;
    @FXML
    Text userCoinText;
    @FXML
    Button buySelectButton;
    @FXML
    Text redText;
    private List<Character> characterList = new ArrayList<>();
    private int index = 1;
    private int money = UsersData.getInstance().getCurrentUser().getCoin();
    private Character r = new Alexandro();
    private Character freeChar = UsersData.getInstance().getCurrentUser().getFreeChar();
    private Lorenzo lorenzo = new Lorenzo();
    private Antonio antonio = new Antonio();
    private Diego diego = new Diego();
    private Mateo mateo = new Mateo();
    private Pablo pablo = new Pablo();
    private Pedro pedro = new Pedro();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        characterList.add(freeChar);
        characterList.add(lorenzo);
        characterList.add(antonio);
        characterList.add(mateo);
        characterList.add(pablo);
        characterList.add(pedro);
        characterList.add(mateo);
        Character r = characterList.get(0);
        if (UsersData.getInstance().getCurrentUser().isPurchased(r.getCharacterType())) {
            //select
            buySelectButton.setText("select");
            if (UsersData.getInstance().getCurrentUser().getSelectedCharacter().getCharacterType().equals(r.getCharacterType())) {
                buySelectButton.setText("selected");
            }
        } else {
            //Buy
            buySelectButton.setText("buy");
            UsersData.getInstance().getCurrentUser().getPurchasedCharacters().add(r);
        }
        characterViewer.setImage(r.getProfilePhoto());
        characterNameText.setText(r.getCharacterType());
        characterPriceText.setText("" + r.getPrice());
        userCoinText.setText(String.valueOf(UsersData.getInstance().getCurrentUser().getCoin()));
    }

    public void next() {
        if (index == characterList.size() - 1) {
            index = -1;
        }
        if (index < characterList.size()) {
            index++;
            r = characterList.get(index);
        }
        if (UsersData.getInstance().getCurrentUser().isPurchased(r.getCharacterType())) {
            //select
            buySelectButton.setText("select");
            if (UsersData.getInstance().getCurrentUser().getSelectedCharacter().getCharacterType().equals(r.getCharacterType())) {
                buySelectButton.setText("selected");
            }
        } else {
            //Buy
            buySelectButton.setText("buy");
        }
        characterViewer.setImage(r.getProfilePhoto());
        characterNameText.setText(r.getCharacterType());
        characterPriceText.setText("" + r.getPrice());
        userCoinText.setText(String.valueOf(money));
        redText.setText("");
    }

    public void goHome(ActionEvent event) throws IOException {
        String path = "src/main/resources/GameData/" + UsersData.getInstance().getCurrentUser().getName() + "/Inventory/purchasedCharacters.json";
        JsonManager manager = new JsonManager(path);
        manager.writeArray(UsersData.getInstance().getCurrentUser().getPurchasedCharacters());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.getInstance().goToScene(stage, PageType.HomePage);
    }

    public void buySelectButton() {
        Character r = characterList.get(index);
        if (buySelectButton.getText().toLowerCase() == "select") {
            //select
            UsersData.getInstance().getCurrentUser().setSelectedCharacter(r);
            buySelectButton.setText("Selected");
        } else {
            //Buy
            if (money >= r.getPrice()) {
                money -= r.getPrice();
                r.setCharacterType(characterNameText.getText());
                UsersData.getInstance().getCurrentUser().setCoin(money);
                userCoinText.setText(String.valueOf(money));
                UsersData.getInstance().getCurrentUser().getPurchasedCharacters().add(r);
                buySelectButton.setText("select");
            } else {
                redText.setText("you Don't have enough money");
            }
        }
    }
}
