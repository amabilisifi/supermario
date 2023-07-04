package project.gameObjects.bossFight;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.UsersData;
import project.characters.Character;
import project.gameStuff.GameData;
import project.gameStuff.SectionDesigner;
import project.managers.CollisionManager;

public class Bomb extends ImageView {
    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
        setY(getY() + 3 * 100 / 100.0);
        CollisionManager.getInstance().collisionBombWithBlock(this);
    }));
    private BossEnemy bossEnemy;

    public Bomb(BossEnemy bossEnemy) {
        this.bossEnemy = bossEnemy;
        setFitWidth(GameObjectsInfo.getInstance().getBombWidth());
        setFitHeight(GameObjectsInfo.getInstance().getBombHeight());
        setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/bomb.PNG"))));
        setX(800*Math.random());
        setY(0);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }
    public void explode(){
        SectionDesigner.getInstance().removeFromRoot(this);
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        double blockSize = GameObjectsInfo.getInstance().getBlockWidth();
        if(Math.abs(character.getX() + character.getFitWidth()/2.0 - this.getX() - this.getFitWidth()/2.0) < 1.5 * blockSize){
            character.damaged();
        }
        if(Math.abs(bossEnemy.getX() + bossEnemy.getFitWidth()/2.0 - this.getX() - this.getFitWidth()/2.0) < 3 * blockSize && !bossEnemy.isDamaged()){
            bossEnemy.damaged(1);
        }
    }
}
