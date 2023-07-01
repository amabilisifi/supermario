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
import project.gameStuff.SectionDesigner;

public class KingKoopa extends BossEnemy {
    private boolean triggered;
    public Timeline timelineThrow;

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

        Timeline t = new Timeline(new KeyFrame(Duration.seconds(10), e -> throwFireBall()));
        t.setCycleCount(Animation.INDEFINITE);
        t.playFromStart();

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
            character.setY(getY() + getFitHeight() / 2.0);
        }
        if (getDirection() == Direction.Right) {
            character.setX(getX() + getFitWidth());
            character.setY(getY() + getFitHeight() / 2.0);
        }
        setTimelineGrab(new Timeline(new KeyFrame(Duration.seconds(5), e -> releaseCharacter())));
        getTimelineGrab().playFromStart();
    }

    @Override
    public void releaseCharacter() {
        throwCharacterAway();
    }

    public void throwCharacterAway() {
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        Direction direction = getDirection();
        if (direction == Direction.Left) {
            System.out.println("le");
            character.setVx(50);
            character.setX(getX() + getFitWidth() / 2.0);
            checkThrowingTimeLine();
            double dt = 20 / 1000.0;
            timelineThrow = new Timeline(new KeyFrame(Duration.millis(20), event -> {
                System.out.println(character.getVx() + " le");
                character.setVx(character.getVx() + 1 * dt);
                character.setX(character.getX() + dt * character.getVx());
            }));
            timelineThrow.setCycleCount(Animation.INDEFINITE);
            timelineThrow.playFromStart();
        }
        if (direction == Direction.Right) {
            System.out.println("ri");
            character.setVx(-50);
            checkThrowingTimeLine();
            double dt = 20 / 1000.0;
            timelineThrow = new Timeline(new KeyFrame(Duration.millis(20), event -> {
                System.out.println(character.getVx() + " ri");
                character.setVx(character.getVx() - 1 * dt);
                character.setX(character.getX() + dt * character.getVx());
            }));
            timelineThrow.setCycleCount(Animation.INDEFINITE);
            timelineThrow.playFromStart();
        }
    }

    public void checkThrowingTimeLine() {
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        if(Math.sqrt(character.getX() - getX()) >= 2.5 * GameObjectsInfo.getInstance().getBlockWidth()){
//        if (character.getVy() == 0) {
            timelineThrow.stop();
        }
    }

    public void checkDistanceOfTwoBlock() {
        double blockSize = GameObjectsInfo.getInstance().getBlockWidth();
        if (getX() < 3 * blockSize) {
            setVx(0);
        }
    }
}
