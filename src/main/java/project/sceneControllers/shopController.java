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
import project.Characters.*;
import project.Characters.Character;
import project.JsonManager;
import project.UserData;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class shopController implements Initializable {
    private List<Character> characterList = new ArrayList<>();
    private int index = 1;
    private int money = UserData.getInstance().getCurrentUser().getCoin();
    private Character r = new Alexandro();

    private Character freeChar = UserData.getInstance().getCurrentUser().getFreeChar();
    private Lorenzo lorenzo = new Lorenzo();
    private Antonio antonio = new Antonio();
    private Diego diego = new Diego();
    private Mateo mateo = new Mateo();
    private Pablo pablo = new Pablo();
    private Pedro pedro = new Pedro();
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
        if (UserData.getInstance().getCurrentUser().isPurchased(r.getCharacterType())) {
            //select
            buySelectButton.setText("select");
            if(UserData.getInstance().getCurrentUser().getSelectedCharacter().getCharacterType().equals(r.getCharacterType())){
                buySelectButton.setText("selected");
            }
        } else {
            //Buy
            buySelectButton.setText("buy");
            UserData.getInstance().getCurrentUser().getPurchasedCharacters().add(r);
        }
        characterViewer.setImage(r.getProfilePhoto());
        characterNameText.setText(r.getCharacterType());
        characterPriceText.setText("" + r.getPrice());
        userCoinText.setText(String.valueOf(UserData.getInstance().getCurrentUser().getCoin()));
    }

    public void next() {
        if (index == characterList.size() - 1) {
            index = -1;
        }
        if (index < characterList.size()) {
            index++;
            r = characterList.get(index);
        }
        if (UserData.getInstance().getCurrentUser().isPurchased(r.getCharacterType())) {
            //select
            buySelectButton.setText("select");
            if(UserData.getInstance().getCurrentUser().getSelectedCharacter().getCharacterType().equals(r.getCharacterType())){
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
        String path = "src/main/resources/GameData/" + UserData.getInstance().getCurrentUser().getName() + "/Inventory/purchasedCharacters.json";
        JsonManager manager = new JsonManager(path);
        manager.writeArray(UserData.getInstance().getCurrentUser().getPurchasedCharacters());
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxmls/homePage.fxml"));
        Parent root = homeLoader.load();
        Scene scene = new Scene(root, 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }

    public void buySelectButton() {
        Character r = characterList.get(index);
        if (buySelectButton.getText().toLowerCase() == "select") {
            //select
            UserData.getInstance().getCurrentUser().setSelectedCharacter(r);
            buySelectButton.setText("Selected");
        } else {
            //Buy
            if (money >= r.getPrice()) {
                money -= r.getPrice();
                r.setCharacterType(characterNameText.getText());
                UserData.getInstance().getCurrentUser().setCoin(money);
                userCoinText.setText(String.valueOf(money));
                UserData.getInstance().getCurrentUser().getPurchasedCharacters().add(r);
                buySelectButton.setText("select");
            } else {
                redText.setText("you Don't have enough money");
            }
        }
    }
}
