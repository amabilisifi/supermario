package project.gameObjects.bossFight;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import project.GameObjectsInfo;

public class Bomb extends ImageView {
    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
        setY(getY() + 3 * 100 / 100.0);
    }));

    public Bomb(BossEnemy bossEnemy) {
        setFitWidth(GameObjectsInfo.getInstance().getBombWidth());
        setFitHeight(GameObjectsInfo.getInstance().getBombHeight());
        setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/Bomb.jpeg"))));
        setX(bossEnemy.getX() + (bossEnemy.getFitWidth() - getFitWidth()) / 2.0);
        setY(0);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }
}
