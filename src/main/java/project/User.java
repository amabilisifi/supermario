package project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import project.characters.Alexandro;
import project.characters.Character;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String password;
    private int coin;
    private int highScore;
    private int savedLoadCount = 1;
    @JsonIgnore
    private List<Character> purchasedCharacters = new ArrayList<>();
    @JsonIgnore
    private Character freeChar = new Alexandro();
    @JsonIgnore
    Character selectedCharacter = freeChar;

    public User() {
    }

    public User(String name, String password) throws IOException {
        this.name = name;
        this.password = password;
        String folderName = "./src/main/resources/GameData/" + name;
        File folder = new File(folderName);
        folder.mkdir();
        File folderLoad = new File(folderName + "/LoadData");
        folderLoad.mkdir();
        File folderInventory = new File(folderName + "/Inventory");
        folderInventory.mkdir();
        File inventory = new File(folderName + "/Inventory/purchasedCharacters.json");
        inventory.createNewFile();
        UsersData.getInstance().getUsers().add(this);
        this.purchasedCharacters.add(freeChar);
    }

    public static User userOf(String username) {
        for (User u : UsersData.getInstance().getUsers()) {
            if (username.equals(u.getName())) {
                return u;
            }
        }
        return null;
    }

    public static boolean userExist(String username) {
        for (User u : UsersData.getInstance().getUsers()) {
            if (u.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPassword(String username, String password) {
        User u = userOf(username);
        return u.getPassword().equals(password);
    }

    public List<Character> getPurchasedCharacters() {
        return purchasedCharacters;
    }

    public void setPurchasedCharacters(List<Character> purchasedCharacters) {
        this.purchasedCharacters = purchasedCharacters;
    }

    public Character getFreeChar() {
        return freeChar;
    }

    public void setFreeChar(Character freeChar) {
        this.freeChar = freeChar;
    }

    public Character getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setSelectedCharacter(Character selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }

    public boolean isPurchased(String r) {
        for (int i = 0; i < purchasedCharacters.size(); i++) {
            if (purchasedCharacters.get(i) != null && purchasedCharacters.get(i).getCharacterType().equalsIgnoreCase(r)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSavedLoadCount() {
        return savedLoadCount;
    }

    public void setSavedLoadCount(int savedLoadCount) {
        this.savedLoadCount = savedLoadCount;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}