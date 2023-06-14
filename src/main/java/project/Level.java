package project;

import project.managers.LevelManager;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private int levelNum;
    private ArrayList<Section> sections;

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
