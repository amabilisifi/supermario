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

        Timeline timelineP = new Timeline(new KeyFrame(Duration.seconds(3), e -> throwFireBall()));
        timelineP.setCycleCount(Animation.INDEFINITE);
        timelineP.playFromStart();
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
        if (isFireBallCooledDown()) {
            SectionDesigner.getInstance().addToRoot(new FireBall(this));
            setFireBallCooledDown(false);
            Timeline chill = new Timeline(new KeyFrame(Duration.seconds(2), e -> setFireBallCooledDown(true)));
            chill.playFromStart();
//        setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/bossThrowing.PNG"))));
//        if (getDirection() == Direction.Left)  setScaleX(-1);
//        if (getDirection() == Direction.Right) setScaleX(1);
//        if (getDirection() == Direction.Left) System.out.println("le");
//        if (getDirection() == Direction.Right) System.out.println("ri");
//        Timeline change = new Timeline(new KeyFrame(Duration.seconds(1), e -> setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/boss.PNG"))))));
//        change.playFromStart();
        }
    }
}
