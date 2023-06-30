package project.gameObjects.bossFight;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import project.UsersData;
import project.characters.Character;
import project.gameObjects.enemies.Direction;
import project.gameObjects.enemies.Enemy;

public abstract class BossEnemy extends Enemy {
    private int HP;
    private boolean isFireBallCooledDown = true;

    public BossEnemy() {
        Timeline timelineCheckDirection = new Timeline(new KeyFrame(Duration.millis(100),e->{checkDirection();
        }));
        timelineCheckDirection.setCycleCount(Animation.INDEFINITE);
        timelineCheckDirection.playFromStart();
    }
    public void checkDirection(){
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        if (character.getX() <= this.getX()) {
            setDirection(Direction.Left);
            setScaleX(1);
        }
        if(character.getX() > this.getX()){
            setDirection(Direction.Right);
            setScaleX(-1);
        }
    }

    public abstract void throwFireBall();

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public boolean isFireBallCooledDown() {
        return isFireBallCooledDown;
    }

    public void setFireBallCooledDown(boolean fireBallCooledDown) {
        isFireBallCooledDown = fireBallCooledDown;
    }
}
