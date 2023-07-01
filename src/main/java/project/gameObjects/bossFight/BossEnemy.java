package project.gameObjects.bossFight;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import project.UsersData;
import project.characters.Character;
import project.gameObjects.enemies.Direction;
import project.gameObjects.enemies.Enemy;

public abstract class BossEnemy extends Enemy {
    private int HP;
    private boolean isAbleToMove = true;
    private boolean isFireBallCooledDown = true;

    public BossEnemy() {
        Timeline timelineCheckDirection = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            checkDirection();
        }));
        timelineCheckDirection.setCycleCount(Animation.INDEFINITE);
        timelineCheckDirection.playFromStart();
    }

    public void checkDirection() {
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        if (character.getX() <= this.getX()) {
            setDirection(Direction.Left);
            setScaleX(1);
        }
        if (character.getX() > this.getX()) {
            setDirection(Direction.Right);
            setScaleX(-1);
        }
    }

    public abstract void throwFireBall();

    public abstract void jumpAttack();

    public void jump(boolean attack) {
        if (!attack)
            setVy(-40);
        if (attack)
            setVy(-60);
        setY(getY() - 4);
        double dt = 25 / 1000.0;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            setVy(getVy() + 10 * dt);
            this.setY(getY() + getVy() * dt);
            setAbleToMove(false);
            if (isOnBlock()) {
                setAbleToMove(true);
            }
            if (getVy() >= 0 && attack) {
                setScaleY(-1);
            }
            if (attack) {
                setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/bossJumping 2.PNG"))));
            }
            if (isOnBlock()) {
                Timeline wait = new Timeline(new KeyFrame(Duration.millis(150), t -> {
                    setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/boss.PNG"))));
                    setScaleY(1);
                    checkDirection();
                }));
                wait.playFromStart();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }

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

    public boolean isAbleToMove() {
        return isAbleToMove;
    }

    public void setAbleToMove(boolean ableToMove) {
        isAbleToMove = ableToMove;
    }
}
