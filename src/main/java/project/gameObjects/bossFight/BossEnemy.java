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

    private boolean isThrowingFireBall = false;
    private boolean isFireBallCooledDown = true;

    private Timeline timelineJump;
    private boolean isJumping = false;
    private boolean isJumpAttackCooledDown = true;

    private Timeline  timelineGrab ;

    public BossEnemy() {
        Timeline timelineCheckDirection = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            checkDirection();
        }));
        timelineCheckDirection.setCycleCount(Animation.INDEFINITE);
        timelineCheckDirection.playFromStart();
    }

    public void checkDirection() {
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        if (character.getX() <= this.getX() && isJumping) {
            setDirection(Direction.Left);
            setScaleX(1);
        }
        if (character.getX() > this.getX() && !isJumping) {
            setDirection(Direction.Right);
            setScaleX(-1);
        }
    }

    public abstract void throwFireBall();

    public abstract void jumpAttack();
    public abstract void grabAttack();
    public abstract void releaseCharacter();

    public void jump(boolean attack) {
        setJumping(true);
        if (!attack)
            setVy(-40);
        if (attack && isJumpAttackCooledDown) {
            setVy(-60);
            setJumpAttackCooledDown(false);
        }
        setY(getY() - 4);
        double dt = 25 / 1000.0;
        timelineJump = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            checkTimeLineOfJump();
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
                if (attack) {
                    Timeline wait = new Timeline(new KeyFrame(Duration.millis(150), t -> {
                        setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/boss.PNG"))));
                        setScaleY(1);
                        checkDirection();
                    }));
                    wait.playFromStart();
                }
            }
        }));
        timelineJump.setCycleCount(Animation.INDEFINITE);
        timelineJump.playFromStart();
    }

    public void checkTimeLineOfJump() {
        if (isOnBlock() && getVy() >= 0) {
            timelineJump.stop();
            setJumping(false);

            Timeline chill = new Timeline(new KeyFrame(Duration.seconds(3), e -> setJumpAttackCooledDown(true)));
            chill.playFromStart();
        }
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

    public boolean isThrowingFireBall() {
        return isThrowingFireBall;
    }

    public void setThrowingFireBall(boolean throwingFireBall) {
        isThrowingFireBall = throwingFireBall;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isJumpAttackCooledDown() {
        return isJumpAttackCooledDown;
    }

    public void setJumpAttackCooledDown(boolean jumpAttackCooledDown) {
        isJumpAttackCooledDown = jumpAttackCooledDown;
    }

    public Timeline getTimelineJump() {
        return timelineJump;
    }

    public void setTimelineJump(Timeline timelineJump) {
        this.timelineJump = timelineJump;
    }

    public Timeline getTimelineGrab() {
        return timelineGrab;
    }

    public void setTimelineGrab(Timeline timelineGrab) {
        this.timelineGrab = timelineGrab;
    }
}
