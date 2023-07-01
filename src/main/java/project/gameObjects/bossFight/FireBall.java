package project.gameObjects.bossFight;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.UsersData;
import project.gameObjects.enemies.Direction;
import project.managers.CollisionManager;
import project.MovingEntity;

public class FireBall extends MovingEntity {
    private BossEnemy bossEnemy;
    private Timeline timelineMove = new Timeline(new KeyFrame(Duration.millis(1), e -> move()));

    public FireBall(BossEnemy bossEnemy) {
        this.bossEnemy = bossEnemy;
        double blockSize = GameObjectsInfo.getInstance().getBlockWidth();
        this.setFitWidth(2 * blockSize);
        this.setFitHeight(1.5 * blockSize);
        this.setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/Fireball.png"))));
        int random = (int) (2 * Math.random()); // 1 is upper one
        if (bossEnemy.getDirection() == Direction.Left) {
            setDirection(Direction.Left);
            this.setX(bossEnemy.getX() - getFitWidth());
//            startX = bossEnemy.getX() - getFitWidth();
            this.setScaleX(-1);
        }
        if (bossEnemy.getDirection() == Direction.Right) {
            setDirection(Direction.Right);
            this.setX(bossEnemy.getX() + bossEnemy.getFitWidth());
//            startX = bossEnemy.getX() + bossEnemy.getFitWidth();
            this.setScaleX(1);
        }
        switch (random) {
            case 0:
                this.setY(bossEnemy.getY() + bossEnemy.getFitHeight() - 2 * blockSize);
                break;
            case 1:
                this.setY(bossEnemy.getY() + bossEnemy.getFitHeight() - 3.5 * blockSize);
                break;
        }
        timelineMove.setCycleCount(Animation.INDEFINITE);
        timelineMove.playFromStart();
    }

    public void move() {
        double dt = 10 / 1000.0;
        double v = UsersData.getInstance().getCurrentUser().getSelectedCharacter().getSpeedo();
        if (getDirection() == Direction.Left) {
            this.setX(this.getX() - v * dt);
        }
        if (getDirection() == Direction.Right) {
            this.setX(this.getX() + v * dt);
        }
//        if (Math.abs(getX() - startX) >= 3.5 * GameObjectsInfo.getInstance().getBlockWidth()){
//            timelineMove.stop();
//            SectionDesigner.getInstance().removeFromRoot(this);
//        }
            CollisionManager.getInstance().collisionFireBall(this);
    }

    public Timeline getTimelineMove() {
        return timelineMove;
    }
}
