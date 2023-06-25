package project.gameStuff;

import project.managers.LevelManager;

import java.util.ArrayList;

public class Level {
    private int levelNum;
    private ArrayList<Section> sections = new ArrayList<>();

    public Level() {
        LevelManager.getInstance().addLevel(this);
    }

    public int getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }
}
