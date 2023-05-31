package project;

import project.gameObjects.Block;
import project.gameObjects.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameInfo {
    private static GameInfo instance;
    private double blockHeight;
    private double blockWidth;
    private double coinHeight;
    private double coinWidth;
    private double pipeWidth;
    private double shortPipeHeight;
    private double mediumPipeHeight;
    private double longPipeHeight;

    private double characterWidth;
    private double characterHeight;

    private double alexandroPrice;
    private double lorenzoPrice;
    private double antonioPrice;
    private double diegoPrice;
    private double pabloPrice;
    private double pedroPrice;
    private double mateoPrice;

    private double alexandroSpeedo;
    private double lorenzoSpeedo;
    private double antonioSpeedo;
    private double pabloSpeedo;
    private double pedroSpeedo;
    private double diegoSpeedo;
    private double mateoSpeedo;

    private double alexandroJumpVelocity;
    private double lorenzoJumpVelocity;
    private double antonioJumpVelocity;
    private double pabloJumpVelocity;
    private double pedroJumpVelocity;
    private double diegoJumpVelocity;
    private double mateoJumpVelocity;

    private double magicalFlowerWidth;
    private double magicalFlowerHeight;
    private double magicalMushroomWidth;
    private double magicalMushroomHeight;
    private double magicalStarWidth;
    private double magicalStarHeight;

    private List<Block> blockList = new ArrayList<>();
    private List<Item> itemList = new ArrayList<>();

    private GameInfo() {
        try {
            ConfigManager manager = new ConfigManager("src/main/java/project/gameObjects/objectsInfo.properties");
            setBlockHeight(manager.getInt("blockHeight"));
            setBlockWidth(manager.getInt("blockWidth"));
            setCoinHeight(manager.getInt("coinHeight"));
            setCoinWidth(manager.getInt("coinWidth"));
            setMagicalFlowerWidth(manager.getInt("magicalFlowerWidth"));
            setMagicalFlowerHeight(manager.getInt("magicalFlowerHeight"));
            setMagicalMushroomWidth(manager.getInt("magicalMushroomWidth"));
            setMagicalMushroomHeight(manager.getInt("magicalMushroomHeight"));
            setMagicalStarWidth(manager.getInt("magicalStarWidth"));
            setMagicalStarHeight(manager.getInt("magicalStarHeight"));
            setPipeWidth(manager.getInt("pipeWidth"));
            setShortPipeHeight(manager.getInt("shortPipeHeight"));
            setMediumPipeHeight(manager.getInt("mediumPipeHeight"));
            setLongPipeHeight(manager.getInt("longPipeHeight"));

            ConfigManager configManager = new ConfigManager("src/main/java/project/Characters/characterInfo.properties");
            setAlexandroPrice(configManager.getInt("alexandroPrice"));
            setLorenzoPrice(configManager.getInt("lorenzoPrice"));
            setAntonioPrice(configManager.getInt("antonioPrice"));
            setDiegoPrice(configManager.getInt("diegoPrice"));
            setPabloPrice(configManager.getInt("pabloPrice"));
            setPedroPrice(configManager.getInt("pedroPrice"));
            setMateoPrice(configManager.getInt("mateoPrice"));

            setCharacterWidth(configManager.getInt("characterWidth"));
            setCharacterHeight(manager.getInt("blockHeight") * 2);

            setAlexandroSpeedo(configManager.getInt("alexandroSpeedo"));
            setLorenzoSpeedo(configManager.getInt("lorenzoSpeedo"));
            setAntonioSpeedo(configManager.getInt("antonioSpeedo"));
            setPabloSpeedo(configManager.getInt("pabloSpeedo"));
            setPedroSpeedo(configManager.getInt("pedroSpeedo"));
            setMateoSpeedo(configManager.getInt("mateoSpeedo"));
            setDiegoSpeedo(configManager.getInt("diegoSpeedo"));

            setAlexandroJumpVelocity(configManager.getInt("alexandroJumpVelocity"));
            setLorenzoJumpVelocity(configManager.getInt("lorenzoJumpVelocity"));
            setAntonioJumpVelocity(configManager.getInt("antonioJumpVelocity"));
            setPabloJumpVelocity(configManager.getInt("pabloJumpVelocity"));
            setPedroJumpVelocity(configManager.getInt("pedroJumpVelocity"));
            setMateoJumpVelocity(configManager.getInt("mateoJumpVelocity"));
            setDiegoJumpVelocity(configManager.getInt("diegoJumpVelocity"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static GameInfo getInstance() {
        if (instance == null) {
            instance = new GameInfo();
        }
        return instance;
    }

    public double getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(double blockHeight) {
        this.blockHeight = blockHeight;
    }

    public double getBlockWidth() {
        return blockWidth;
    }

    public void setBlockWidth(double blockWidth) {
        this.blockWidth = blockWidth;
    }

    public double getCoinHeight() {
        return coinHeight;
    }

    public void setCoinHeight(double coinHeight) {
        this.coinHeight = coinHeight;
    }

    public double getCoinWidth() {
        return coinWidth;
    }

    public void setCoinWidth(double coinWidth) {
        this.coinWidth = coinWidth;
    }

    public double getPipeWidth() {
        return pipeWidth;
    }

    public void setPipeWidth(double pipeWidth) {
        this.pipeWidth = pipeWidth;
    }

    public double getShortPipeHeight() {
        return shortPipeHeight;
    }

    public void setShortPipeHeight(double shortPipeHeight) {
        this.shortPipeHeight = shortPipeHeight;
    }

    public double getMediumPipeHeight() {
        return mediumPipeHeight;
    }

    public void setMediumPipeHeight(double mediumPipeHeight) {
        this.mediumPipeHeight = mediumPipeHeight;
    }

    public double getLongPipeHeight() {
        return longPipeHeight;
    }

    public void setLongPipeHeight(double longPipeHeight) {
        this.longPipeHeight = longPipeHeight;
    }

    public double getAlexandroPrice() {
        return alexandroPrice;
    }

    public void setAlexandroPrice(double alexandroPrice) {
        this.alexandroPrice = alexandroPrice;
    }

    public double getLorenzoPrice() {
        return lorenzoPrice;
    }

    public void setLorenzoPrice(double lorenzoPrice) {
        this.lorenzoPrice = lorenzoPrice;
    }

    public double getAntonioPrice() {
        return antonioPrice;
    }

    public void setAntonioPrice(double antonioPrice) {
        this.antonioPrice = antonioPrice;
    }

    public double getCharacterWidth() {
        return characterWidth;
    }

    public void setCharacterWidth(double characterWidth) {
        this.characterWidth = characterWidth;
    }

    public double getCharacterHeight() {
        return characterHeight;
    }

    public void setCharacterHeight(double characterHeight) {
        this.characterHeight = characterHeight;
    }

    public double getAlexandroSpeedo() {
        return alexandroSpeedo;
    }

    public void setAlexandroSpeedo(double alexandroSpeedo) {
        this.alexandroSpeedo = alexandroSpeedo;
    }

    public double getLorenzoSpeedo() {
        return lorenzoSpeedo;
    }

    public void setLorenzoSpeedo(double lorenzoSpeedo) {
        this.lorenzoSpeedo = lorenzoSpeedo;
    }

    public double getAntonioSpeedo() {
        return antonioSpeedo;
    }

    public void setAntonioSpeedo(double antonioSpeedo) {
        this.antonioSpeedo = antonioSpeedo;
    }

    public double getDiegoPrice() {
        return diegoPrice;
    }

    public void setDiegoPrice(double diegoPrice) {
        this.diegoPrice = diegoPrice;
    }

    public double getPabloPrice() {
        return pabloPrice;
    }

    public void setPabloPrice(double pabloPrice) {
        this.pabloPrice = pabloPrice;
    }

    public double getPedroPrice() {
        return pedroPrice;
    }

    public void setPedroPrice(double pedroPrice) {
        this.pedroPrice = pedroPrice;
    }

    public double getMateoPrice() {
        return mateoPrice;
    }

    public void setMateoPrice(double mateoPrice) {
        this.mateoPrice = mateoPrice;
    }

    public double getPabloSpeedo() {
        return pabloSpeedo;
    }

    public void setPabloSpeedo(double pabloSpeedo) {
        this.pabloSpeedo = pabloSpeedo;
    }

    public double getPedroSpeedo() {
        return pedroSpeedo;
    }

    public void setPedroSpeedo(double pedroSpeedo) {
        this.pedroSpeedo = pedroSpeedo;
    }

    public double getDiegoSpeedo() {
        return diegoSpeedo;
    }

    public void setDiegoSpeedo(double diegoSpeedo) {
        this.diegoSpeedo = diegoSpeedo;
    }

    public double getMateoSpeedo() {
        return mateoSpeedo;
    }

    public void setMateoSpeedo(double mateoSpeedo) {
        this.mateoSpeedo = mateoSpeedo;
    }

    public double getAlexandroJumpVelocity() {
        return alexandroJumpVelocity;
    }

    public void setAlexandroJumpVelocity(double alexandroJumpVelocity) {
        this.alexandroJumpVelocity = alexandroJumpVelocity;
    }

    public double getLorenzoJumpVelocity() {
        return lorenzoJumpVelocity;
    }

    public void setLorenzoJumpVelocity(double lorenzoJumpVelocity) {
        this.lorenzoJumpVelocity = lorenzoJumpVelocity;
    }

    public double getAntonioJumpVelocity() {
        return antonioJumpVelocity;
    }

    public void setAntonioJumpVelocity(double antonioJumpVelocity) {
        this.antonioJumpVelocity = antonioJumpVelocity;
    }

    public double getPabloJumpVelocity() {
        return pabloJumpVelocity;
    }

    public void setPabloJumpVelocity(double pabloJumpVelocity) {
        this.pabloJumpVelocity = pabloJumpVelocity;
    }

    public double getPedroJumpVelocity() {
        return pedroJumpVelocity;
    }

    public void setPedroJumpVelocity(double pedroJumpVelocity) {
        this.pedroJumpVelocity = pedroJumpVelocity;
    }

    public double getDiegoJumpVelocity() {
        return diegoJumpVelocity;
    }

    public void setDiegoJumpVelocity(double diegoJumpVelocity) {
        this.diegoJumpVelocity = diegoJumpVelocity;
    }

    public double getMateoJumpVelocity() {
        return mateoJumpVelocity;
    }

    public void setMateoJumpVelocity(double mateoJumpVelocity) {
        this.mateoJumpVelocity = mateoJumpVelocity;
    }

    public double getMagicalFlowerWidth() {
        return magicalFlowerWidth;
    }

    public void setMagicalFlowerWidth(double magicalFlowerWidth) {
        this.magicalFlowerWidth = magicalFlowerWidth;
    }

    public double getMagicalFlowerHeight() {
        return magicalFlowerHeight;
    }

    public void setMagicalFlowerHeight(double magicalFlowerHeight) {
        this.magicalFlowerHeight = magicalFlowerHeight;
    }

    public double getMagicalStarWidth() {
        return magicalStarWidth;
    }

    public void setMagicalStarWidth(double magicalStarWidth) {
        this.magicalStarWidth = magicalStarWidth;
    }

    public double getMagicalStarHeight() {
        return magicalStarHeight;
    }

    public void setMagicalStarHeight(double magicalStarHeight) {
        this.magicalStarHeight = magicalStarHeight;
    }

    public double getMagicalMushroomWidth() {
        return magicalMushroomWidth;
    }

    public void setMagicalMushroomWidth(double magicalMushroomWidth) {
        this.magicalMushroomWidth = magicalMushroomWidth;
    }

    public double getMagicalMushroomHeight() {
        return magicalMushroomHeight;
    }

    public void setMagicalMushroomHeight(double magicalMushroomHeight) {
        this.magicalMushroomHeight = magicalMushroomHeight;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<Block> blockList) {
        this.blockList = blockList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
