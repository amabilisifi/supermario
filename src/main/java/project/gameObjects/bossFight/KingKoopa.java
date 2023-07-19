package project.gameObjects.bossFight;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.UsersData;
import project.characters.Character;
import project.gameObjects.Direction;
import project.gameStuff.GameData;
import project.gameStuff.SectionDesigner;

public class KingKoopa extends BossEnemy {
    private boolean triggered;
    private Timeline timelineThrow;
    private boolean stopThrowing = false;
    private boolean leftAndRight = false;
    private Timeline checkLeftRight;

    public KingKoopa(double startX, double startY) {
        this.setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/boss.PNG"))));
        this.setFitWidth(GameObjectsInfo.getInstance().getKingKoopaWidth());
        this.setFitHeight(4.8 * GameObjectsInfo.getInstance().getBlockHeight());
        this.setX(startX);
        this.setY(startY);
        setHP(20);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> checkTrigger()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

        if (triggered) {
            timeline.stop();
        }
    }

    public void checkTrigger() {
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        if (Math.abs(character.getX() - this.getX()) <= 200 && !SectionDesigner.getInstance().isBossScene()) {
            GameData.getInstance().setBossEnemy(this);
            triggered = true;
            BossController controller = new BossController(this);
            SectionDesigner.getInstance().setBossScene(true);
        }
    }

    @Override
    public void throwFireBall() {
        if (isFireBallCooledDown() && !isJumping() && !isDamaged()) {
            setThrowingFireBall();
            setFireBallCooledDown(false);
            SectionDesigner.getInstance().addToRoot(new FireBall(this));

            Timeline chill = new Timeline(new KeyFrame(Duration.seconds(2), e -> setFireBallCooledDown(true)));
            chill.playFromStart();

            setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/bossThrowing.PNG"))));
            if (getDirection() == Direction.Left) setScaleX(-1);

            Timeline change = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/boss.PNG"))));
                if (isSuper())
                    this.setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/boss2.PNG"))));
                setThrowingFireBall();
            }));
            change.playFromStart();
        }
        setThrowingFireBall();
    }

    @Override
    public void jumpAttack() {
        if (!isDamaged() && isJumpAttackCooledDown())
            jump(true);
    }

    @Override
    public void grabAttack() {
        if (!isDamaged() && isGrabAttackCooledDown()) {
            Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
            character.setGrabbed(true);
            setGrabAttackCooledDown(false);
            if (getDirection() == Direction.Left) {
                character.setX(getX() - character.getFitWidth() / 2.0);
                character.setY(getY() + getFitHeight() / 3.0);
                character.setDirection(Direction.Right);
            }
            if (getDirection() == Direction.Right) {
                character.setX(getX() + getFitWidth() - character.getFitWidth() / 2.0);
                character.setY(getY() + getFitHeight() / 3.0);
                character.setDirection(Direction.Left);
            }
            setTimelineGrab(new Timeline(new KeyFrame(Duration.seconds(5), e -> {
                if (!stopThrowing) {
                    releaseCharacter();
                    character.damaged();
                }
            })));
            getTimelineGrab().playFromStart();
            checkLeftRight = new Timeline(new KeyFrame(Duration.millis(200), e -> {
                if (GameData.getInstance().getGameController().check10LeftRight() && !leftAndRight) {
                    releaseCharacter();
                    leftAndRight = true;
                    GameData.getInstance().getGameController().resetLeftRight();
                }
            }));
            checkLeftRight.setCycleCount(Animation.INDEFINITE);
            checkLeftRight.playFromStart();
        }
    }

    @Override
    public void releaseCharacter() {
        throwCharacterAway(getDirection());
        checkLeftRight.stop();
    }

    public void throwCharacterAway(Direction direction) {
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        if (direction == Direction.Left && !stopThrowing) {
            character.setVx(20);
            character.setX(getX() + getFitWidth() / 2.0);
            character.setX(getX() + getFitWidth() - character.getFitWidth() / 2.0);
            setDirection(Direction.Right);
            stopThrowing = false;
            character.setGrabbed(false);
            double dt = 20 / 1000.0;
            timelineThrow = new Timeline(new KeyFrame(Duration.millis(20), event -> {
                checkThrowingTimeLine();
                character.setVx(character.getVx() - 50 * dt);
                character.setX(character.getX() + dt * character.getVx());
                character.setImage(character.getProfilePhoto());
                if (character.getVx() <= 1) {
                    stopThrowing = true;
                }
            }));
            timelineThrow.setCycleCount(Animation.INDEFINITE);
            timelineThrow.playFromStart();
        }
        if (direction == Direction.Right && !stopThrowing) {
            character.setVx(-20);
            character.setX(getX() + getFitWidth() / 2.0);
            character.setX(getX() - character.getFitWidth() / 2.0);
            setDirection(Direction.Left);
            stopThrowing = false;
            character.setGrabbed(false);
            double dt = 20 / 1000.0;
            timelineThrow = new Timeline(new KeyFrame(Duration.millis(20), event -> {
                checkThrowingTimeLine();
                character.setVx(character.getVx() + 50 * dt);
                character.setX(character.getX() + dt * character.getVx());
                character.setImage(character.getProfilePhoto());
                if (character.getVx() >= -1) {
                    stopThrowing = true;
                }
            }));
            timelineThrow.setCycleCount(Animation.INDEFINITE);
            timelineThrow.playFromStart();
        }
        if (!character.isGrabbed()) {
            Timeline chill = new Timeline(new KeyFrame(Duration.seconds(4.5), e -> {
                setGrabAttackCooledDown(true);
                stopThrowing = false;
            }));
            chill.playFromStart();
        }
    }

    public void checkThrowingTimeLine() {
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        if (stopThrowing) {
            timelineThrow.stop();
            character.setGrabbed(false);
        }
    }

    public void checkDistanceOfTwoBlock() {
        double blockSize = GameObjectsInfo.getInstance().getBlockWidth();
        if (getX() < 3 * blockSize) {
            setVx(0);
        }
    }

}
