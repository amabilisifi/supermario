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
    private final BossEnemy bossEnemy;
    private final Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
    private double distance;
    private Timeline timelineControl  = new Timeline(new KeyFrame(Duration.millis(10),e->controlBossMechanism()));

    public BossController(BossEnemy bossEnemy) {
        this.bossEnemy = bossEnemy;
        timelineControl.setCycleCount(Animation.INDEFINITE);
        timelineControl.playFromStart();
    }

    public void controlBossMechanism(){
        System.out.println(character.getVx()+" "+character.getaX());
        walk();
        if(isInThisDistance(6,10) ){
            bossEnemy.throwFireBall();
        }
        if(isInThisDistance(1)){
            bossEnemy.grabAttack();
        }
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
            Direction direction = bossEnemy.getDirection();
            if ((direction == Direction.Left && distance <= 3 * blockSize + character.getFitWidth()) ||
                    (direction == Direction.Right && distance <= 3.5 * blockSize + bossEnemy.getFitWidth())) {
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
    public boolean isInThisDistance(int num1,int num2) {
        double blockSize = GameObjectsInfo.getInstance().getBlockWidth();
        bossEnemy.checkDirection();
        Direction direction = bossEnemy.getDirection();
        return ((direction == Direction.Left && distance <= num2 * blockSize + character.getFitWidth()) || (direction == Direction.Right && distance <= (num2 + 0.5) * blockSize + bossEnemy.getFitWidth()))
                && ((direction == Direction.Left && distance > num1 * blockSize + character.getFitWidth()) || (direction == Direction.Right && distance > (num1 + 0.5) * blockSize + bossEnemy.getFitWidth()));
    }
}
