package project.gameObjects.bossFight;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.UsersData;
import project.characters.Character;
import project.gameObjects.enemies.Direction;
import project.gameStuff.GameData;
import project.gameStuff.SectionDesigner;

public class KingKoopa extends BossEnemy {
    private boolean triggered;
    private Timeline timelineThrow;
    private boolean stopThrowing = false;
    private boolean leftAndRight = false;
    private Timeline  checkLeftRight;

    public KingKoopa(double startX, double startY) {
        this.setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/boss.PNG"))));
        this.setFitWidth(GameObjectsInfo.getInstance().getKingKoopaWidth());
        this.setFitHeight(4.5 * GameObjectsInfo.getInstance().getBlockHeight());
        this.setX(startX);
        this.setY(startY);
        setHP(20);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> checkTrigger()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

//        Timeline t = new Timeline(new KeyFrame(Duration.seconds(10), e -> throwFireBall()));
//        t.setCycleCount(Animation.INDEFINITE);
//        t.playFromStart();

    }

    public void checkTrigger() {
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        if (Math.abs(character.getX() - this.getX()) <= 550 && !SectionDesigner.getInstance().isBossScene()) {
            triggered = true;
            BossController controller = new BossController(this);
            SectionDesigner.getInstance().setBossScene(true);
        }
    }

    @Override
    public void throwFireBall() {
        if (isFireBallCooledDown() && !isJumping()) {
            setThrowingFireBall(true);
            setFireBallCooledDown(false);
            SectionDesigner.getInstance().addToRoot(new FireBall(this));

            Timeline chill = new Timeline(new KeyFrame(Duration.seconds(2), e -> setFireBallCooledDown(true)));
            chill.playFromStart();

            setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/bossThrowing.jpeg"))));
            if (getDirection() == Direction.Left) setScaleX(-1);

            Timeline change = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/boss.PNG"))));
                setThrowingFireBall(false);
            }));
            change.playFromStart();
        }
        setThrowingFireBall(false);
    }

    @Override
    public void jumpAttack() {
        jump(true);
    }

    @Override
    public void grabAttack() {
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        character.setGrabbed(true);
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
            if (!stopThrowing) releaseCharacter();
        })));
        getTimelineGrab().playFromStart();
         checkLeftRight = new Timeline(new KeyFrame(Duration.millis(200), e -> {
            if(GameData.getInstance().getGameController().check10LeftRight() && !leftAndRight) {releaseCharacter();
            leftAndRight = true;
            GameData.getInstance().getGameController().resetLeftRight();
            }
        }));
        checkLeftRight.setCycleCount(Animation.INDEFINITE);
        checkLeftRight.playFromStart();
    }

    @Override
    public void releaseCharacter() {
        throwCharacterAway(getDirection());
        checkLeftRight.stop();
    }

    public void throwCharacterAway(Direction direction) {
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        if (direction == Direction.Left) {
            character.setVx(100);
            character.setX(getX() + getFitWidth() / 2.0);
            character.setX(getX() + getFitWidth() - character.getFitWidth() / 2.0);
            setDirection(Direction.Right);
            stopThrowing = false;
            double dt = 20 / 1000.0;
            timelineThrow = new Timeline(new KeyFrame(Duration.millis(20), event -> {
                checkThrowingTimeLine();
                character.setVx(character.getVx() - 50 * dt);
                character.setX(character.getX() + dt * character.getVx());
                if (character.getVx() <= 0) {
                    stopThrowing = true;
                }
            }));
            timelineThrow.setCycleCount(Animation.INDEFINITE);
            timelineThrow.playFromStart();
        }
        if (direction == Direction.Right) {
            character.setVx(-100);
            character.setX(getX() + getFitWidth() / 2.0);
            character.setX(getX() - character.getFitWidth() / 2.0);
            setDirection(Direction.Left);
            stopThrowing = false;
            double dt = 20 / 1000.0;
            timelineThrow = new Timeline(new KeyFrame(Duration.millis(20), event -> {
                checkThrowingTimeLine();
                character.setVx(character.getVx() + 50 * dt);
                character.setX(character.getX() + dt * character.getVx());
                if (character.getVx() >= 0) {
                    stopThrowing = true;
                }
            }));
            timelineThrow.setCycleCount(Animation.INDEFINITE);
            timelineThrow.playFromStart();
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
