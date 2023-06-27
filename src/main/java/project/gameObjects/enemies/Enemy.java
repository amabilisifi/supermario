package project.gameObjects.enemies;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.gameStuff.GameData;
import project.managers.CollisionManager;
import project.managers.MovingEntity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Enemy extends MovingEntity {
    private double startX;
    private Direction direction;
    Timeline timelineMove;
    Timeline timeLineFall;

    public Enemy(double startX, double startY) {
        this.startX = startX;
        setVx(4);
        setX(startX);
        setY(startY);
        this.setFitHeight(GameObjectsInfo.getInstance().getBlockHeight());
        timelineMove = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            move();
        }));
        direction = Direction.Left;
        timelineMove.setCycleCount(Animation.INDEFINITE);
        timelineMove.playFromStart();

        timeLineFall = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            move();
        }));
        direction = Direction.Left;
        timeLineFall.setCycleCount(Animation.INDEFINITE);
        timeLineFall.playFromStart();
    }

    @Override
    public void move() {
        // it should turn if it collides sth or when it'll turn around
        //speed is 0.5 block per seconds
        double dt = 20 / 100.0;
        if (direction == Direction.Right) {
            setVx(getVx() - Math.abs(getaX() * dt / 3));
            setX(getX() + Math.abs(getVx()) * dt);
            setScaleX(-1);
        }
        if (direction == Direction.Left) {
            setVx(getVx() - Math.abs(getaX() * dt / 3));
            setX(getX() - Math.abs(getVx()) * dt);
            setScaleX(1);
        }
        CollisionManager.getInstance().collisionWithObjectsInGame(this);
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Timeline getTimelineMove() {
        return timelineMove;
    }

    public void setTimelineMove(Timeline timelineMove) {
        this.timelineMove = timelineMove;
    }
}
