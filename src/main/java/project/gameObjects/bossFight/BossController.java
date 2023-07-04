package project.gameObjects.bossFight;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.UsersData;
import project.characters.Character;
import project.gameObjects.Direction;

public class BossController {
    private final BossEnemy bossEnemy;
    private final Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
    private double distance;
    private boolean jumpAttack = false;
    private final Timeline timeLineRoam = new Timeline(new KeyFrame(Duration.millis(20),e->roam()));
    private double startXRoam;

    public BossController(BossEnemy bossEnemy) {
        this.bossEnemy = bossEnemy;
        Timeline timelineControl = new Timeline(new KeyFrame(Duration.millis(10), e -> controlBossMechanism()));
        timelineControl.setCycleCount(Animation.INDEFINITE);
        timelineControl.playFromStart();
    }

    public void controlBossMechanism() {
        walk();
        boolean flag = false;
        if (isInThisDistance(6, 10)) {
            bossEnemy.throwFireBall();
            flag = true;
            timeLineRoam.stop();
        }
        if (isInThisDistance(1) && !character.isDizzy()) {
            bossEnemy.grabAttack();
            flag = true;
            timeLineRoam.stop();
        }
        if (character.isOnGround4seconds() && !jumpAttack) {
            bossEnemy.jumpAttack();
            jumpAttack = true;
            flag = true;
            timeLineRoam.stop();
        }
        if (!flag) {
            roam();
            startXRoam = bossEnemy.getX();
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

    public void roam() {
        double random = (int) (3 * Math.random());
        double blockSize = GameObjectsInfo.getInstance().getBlockWidth();
        Direction direction = Direction.Right;
        if (random == 0) {
            direction = Direction.Left;
        }
        double dt = 20/1000.0;
        bossEnemy.setVx(4);
        switch (direction) {
            case Right -> {
                bossEnemy.setX(bossEnemy.getX() + Math.abs(bossEnemy.getVx() * dt));
                if(Math.abs(bossEnemy.getX() - startXRoam) >= 1.5 * blockSize){
                    direction = Direction.Left;
                }
            }
            case Left -> {
                bossEnemy.setX(bossEnemy.getX() - Math.abs(bossEnemy.getVx() * dt));
                if(Math.abs(bossEnemy.getX() - startXRoam) >= 1.5 * blockSize){
                    direction = Direction.Right;
                }
            }
        }
//         bossEnemy.setDirection(direction);
    }

    public boolean isInThisDistance(int num) {
        double blockSize = GameObjectsInfo.getInstance().getBlockWidth();
        bossEnemy.checkDirection();
        Direction direction = bossEnemy.getDirection();
        return (direction == Direction.Left && distance <= num * blockSize + character.getFitWidth()) || (direction == Direction.Right && distance <= (num + 0.5) * blockSize + bossEnemy.getFitWidth());
    }


    public boolean isInThisDistance(int num1, int num2) {
        double blockSize = GameObjectsInfo.getInstance().getBlockWidth();
        bossEnemy.checkDirection();
        Direction direction = bossEnemy.getDirection();
        return ((direction == Direction.Left && distance <= num2 * blockSize + character.getFitWidth()) || (direction == Direction.Right && distance <= (num2 + 0.5) * blockSize + bossEnemy.getFitWidth()))
                && ((direction == Direction.Left && distance > num1 * blockSize + character.getFitWidth()) || (direction == Direction.Right && distance > (num1 + 0.5) * blockSize + bossEnemy.getFitWidth()));
    }
}
