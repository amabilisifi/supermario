package project.gameObjects.bossFight;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.GameObjectsInfo;

public class NukeButton extends ImageView {
    public NukeButton(BossEnemy bossEnemy) {
        setFitWidth(GameObjectsInfo.getInstance().getNukeButtonWidth());
        setFitHeight(GameObjectsInfo.getInstance().getNukeButtonHeight());
        setImage(new Image(String.valueOf(getClass().getResource("/images/bossFight/Button.png"))));
        setX(bossEnemy.getX() + (bossEnemy.getFitWidth() - getFitWidth()) / 2.0);
        setY(bossEnemy.getY() + 2 / 3.0 * bossEnemy.getFitHeight());
    }
}
