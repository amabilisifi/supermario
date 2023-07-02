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
    private final BossEnemy bossEnemy;
    private final Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
    private double distance;

    public BossController(BossEnemy bossEnemy) {
        this.bossEnemy = bossEnemy;
        Timeline timelineMove = new Timeline(new KeyFrame(Duration.millis(10), e -> walk()));
        timelineMove.setCycleCount(Animation.INDEFINITE);
        timelineMove.playFromStart();
    }

    public void walk() {
        if (bossEnemy.isAbleToMove() && !bossEnemy.isDamaged()) {
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
            if (isInThisDistance(3)) {
                bossEnemy.setVx(0);
            }
        }
    }

    public boolean isInThisDistance(int num) {
        double blockSize = GameObjectsInfo.getInstance().getBlockWidth();
        bossEnemy.checkDirection();
        Direction direction = bossEnemy.getDirection();
        return (direction == Direction.Left && distance <= num * blockSize + character.getFitWidth()) || (direction == Direction.Right && distance <= (num + 0.5) * blockSize + bossEnemy.getFitWidth());
    }
}
