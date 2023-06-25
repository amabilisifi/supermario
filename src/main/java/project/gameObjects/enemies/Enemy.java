package project.gameObjects.enemies;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.managers.MovingEntity;

public abstract class Enemy extends MovingEntity {
    private double startX;

    public Enemy(double startX,double startY) {
        this.startX = startX;
        setX(startX);
        setY(startY);
        this.setFitHeight(GameObjectsInfo.getInstance().getBlockHeight());
        setVx(5);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            move();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }

    @Override
    public void move() {
        // it should turn if it collides sth or when it'll turn around
        //speed is 0.5 block per seconds
        double dt = 20/100.0;
        setX(getX()+getVx()*dt );
    }
}
