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

        jumpAttack();
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


}
