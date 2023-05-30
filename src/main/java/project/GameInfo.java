package project;

import project.gameObjects.Block;

import java.io.IOException;

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

    private double alexandroPrice;
    private double lorenzoPrice;
    private double antonioPrice;
    private double characterWidth;
    private double characterHeight;
    private double alexandroSpeedo;
    private double lorenzoSpeedo;
    private double antonioSpeedo;
    private GameInfo(){
        try {
            ConfigManager manager = new ConfigManager("src/main/java/project/gameObjects/objectsInfo.properties");
            setBlockHeight(manager.getInt("blockHeight"));
            setBlockWidth(manager.getInt("blockWidth"));
            setCoinHeight(manager.getInt("coinHeight"));
            setCoinWidth(manager.getInt("coinWidth"));
            setPipeWidth(manager.getInt("pipeWidth"));
            setShortPipeHeight(manager.getInt("shortPipeHeight"));
            setMediumPipeHeight(manager.getInt("mediumPipeHeight"));
            setLongPipeHeight(manager.getInt("longPipeHeight"));

            ConfigManager configManager = new ConfigManager("src/main/java/project/Characters/characterInfo.properties");
            setAlexandroPrice(configManager.getInt("alexandroPrice"));
            setLorenzoPrice(configManager.getInt("lorenzoPrice"));
            setAntonioPrice(configManager.getInt("antonioPrice"));
            setCharacterWidth(configManager.getInt("characterWidth"));
            setCharacterHeight(manager.getInt("blockHeight")*2);
            setAlexandroSpeedo(configManager.getInt("alexandroSpeedo"));
            setLorenzoSpeedo(configManager.getInt("lorenzoSpeedo"));
            setAntonioSpeedo(configManager.getInt("antonioSpeedo"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static GameInfo getInstance() {
        if(instance==null){
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
}
