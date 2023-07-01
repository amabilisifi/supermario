package project.gameObjects.bossFight;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.UsersData;
import project.characters.Character;
import project.gameObjects.enemies.Direction;

public class BossController {
    // if 6 < distance < 10 fireBall and coolDown for 1.5 sec
    private BossEnemy bossEnemy;
    private Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
    private double distance;

    public BossController(BossEnemy bossEnemy) {
        this.bossEnemy = bossEnemy;
        Timeline timelineMove = new Timeline(new KeyFrame(Duration.millis(10), e -> walk()));
        timelineMove.setCycleCount(Animation.INDEFINITE);
        timelineMove.playFromStart();

    }

    public void walk() {
        if (bossEnemy.isAbleToMove()) {
            double blockSize = GameObjectsInfo.getInstance().getBlockWidth();
            double dt = 10 / 1000.0;
            distance = Math.abs(character.getX() - bossEnemy.getX());
            if (distance >= 8 * blockSize) {
                bossEnemy.setVx(0.18 * blockSize);
                if (bossEnemy.getDirection() == Direction.Left) {
                    bossEnemy.setX(bossEnemy.getX() - Math.abs(bossEnemy.getVx() * dt));
                }
                if (bossEnemy.getDirection() == Direction.Right) {
                    bossEnemy.setX(bossEnemy.getX() + Math.abs(bossEnemy.getVx() * dt));
                }
            }
            Direction direction = bossEnemy.getDirection();
            if ( (direction == Direction.Left && distance <= 3 * blockSize + character.getFitWidth()) ||
                    (direction == Direction.Right  && distance <= 3.5 * blockSize + bossEnemy.getFitWidth())) {
                bossEnemy.setVx(0);
            }
        }
    }
}
