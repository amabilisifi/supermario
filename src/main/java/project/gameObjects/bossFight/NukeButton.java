package project.gameObjects.bossFight;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.gameStuff.SectionDesigner;

public class NukeButton extends ImageView {
    private boolean stopTimeLine = false;
    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
        if(getFitHeight() * getScaleY() > GameObjectsInfo.getInstance().getNukeButtonHeight()/2.0){
        setScaleY(getScaleY() * 0.995);
        }else {
            stopTimeLine = true;
            checkTimeLine();
        }
    }));
    private BossEnemy bossEnemy;

    public NukeButton(BossEnemy bossEnemy) {
        this.bossEnemy = bossEnemy;
        setFitWidth(GameObjectsInfo.getInstance().getNukeButtonWidth());
        setFitHeight(GameObjectsInfo.getInstance().getNukeButtonHeight());
        setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/Button.png"))));
        setX(bossEnemy.getX() + (bossEnemy.getFitWidth() - getFitWidth()) / 2.0);
        setY(bossEnemy.getY() + 2 / 3.0 * bossEnemy.getFitHeight());

        SectionDesigner.getInstance().addToRoot(this);


        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }
    public void checkTimeLine(){
        if(stopTimeLine) {
            timeline.stop();
            SectionDesigner.getInstance().removeFromRoot(this);
            Bomb bomb =new Bomb(bossEnemy);
            SectionDesigner.getInstance().addToRoot(bomb);
        }
    }
}
