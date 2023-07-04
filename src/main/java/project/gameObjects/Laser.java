package project.gameObjects;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.UsersData;
import project.characters.Character;
import project.gameStuff.GameData;
import project.managers.CollisionManager;
import project.MovingEntity;

public class Laser extends MovingEntity {
    private double startX;
    private Timeline timelineMove;
    private Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
    private boolean objectIsDeleted = false;


    public Laser() {
        double blockSize = GameObjectsInfo.getInstance().getBlockHeight();
        if (character.getFitHeight() == blockSize) {
            this.setY(character.getY() + character.getFitHeight() - blockSize / 2.0);
        }
        if (character.getFitHeight() == 2 * blockSize) {
            this.setY(character.getY() + character.getFitHeight() - 1.5 * blockSize);
        }
        this.setImage(new Image(String.valueOf(getClass().getResource("/images/laser.PNG"))));
        this.setFitWidth(GameObjectsInfo.getInstance().getLaserWidth());
        this.setFitHeight(GameObjectsInfo.getInstance().getLaserHeight());


        if (character.getDirection() == Direction.Right) {
            this.setX(character.getX() + character.getFitWidth());
            startX = character.getX() + character.getFitWidth();
            setDirection(Direction.Right);
        }
        if (character.getDirection() == Direction.Left) {
            this.setX(character.getX() - this.getFitWidth());
            startX = character.getX()- this.getFitWidth();
            setDirection(Direction.Left);
        }

        timelineMove = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            move();
        }));
        timelineMove.setCycleCount(Animation.INDEFINITE);
        timelineMove.playFromStart();
    }

    @Override
    public void move() {
        double blockWidth = GameObjectsInfo.getInstance().getBlockHeight();
        double dt = 20 / 100.0;
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        if (Math.abs(getX() - getStartX()) <= 8 * blockWidth) {
            if (getDirection() == Direction.Right) {
                setX(getX() + Math.abs(character.getSpeedo()) * dt);
            }
            if (getDirection() == Direction.Left) {
                setX(getX() - Math.abs(character.getSpeedo()) * dt);
            }
        } else {
            GameData.getInstance().getRoot().getChildren().remove(this);
        }
        CollisionManager.getInstance().collisionWeapon(this);
    }


    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public boolean isObjectIsDeleted() {
        return objectIsDeleted;
    }

    public void setObjectIsDeleted(boolean objectIsDeleted) {
        this.objectIsDeleted = objectIsDeleted;
    }
}
