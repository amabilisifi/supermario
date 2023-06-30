package project.gameObjects.enemies;


import javafx.scene.image.Image;
import project.GameObjectsInfo;
import project.UsersData;
import project.characters.Character;
import project.managers.CollisionManager;

public class Spiny extends Enemy {
    public boolean isCloseEnough;

    public Spiny(double startX, double startY) {
        super(startX, startY);
        this.setFitWidth(GameObjectsInfo.getInstance().getSpinyWidth());
        this.setImage(new Image(String.valueOf(getClass().getResource("/images/enemies/Spiny.png"))));
    }

    @Override
    public void move() {
        checkDistanceOfSpiny();
        double dt = 20 / 100.0;
        if (isCloseEnough) {
            setVx(getVx() + 1 * dt);
        }
        if (getDirection() == Direction.Right) {
            setX(getX() + Math.abs(getVx()) * dt);
            setScaleX(-1);
        }
        if (getDirection() == Direction.Left) {
            setX(getX() - Math.abs(getVx()) * dt);
            setScaleX(1);
        }
        CollisionManager.getInstance().collisionWithObjectsInGame(this);
    }

    public void checkDistanceOfSpiny() {
        Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
        double deltaX = character.getX() - this.getX();
        double deltaY = character.getY() - this.getY();
        double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        double blockSize = GameObjectsInfo.getInstance().getBlockWidth();
        if (distance <= 4 * blockSize) {
            setCloseEnough(true);
        }
        else {
            setCloseEnough(false);
            setVx(5);
        }
        if (deltaX >= -1 * getFitWidth() && deltaX<character.getFitWidth()) {
            setVx(0);
            setaX(0);
        }
        if(Math.abs(deltaX)>=5 && Math.abs(deltaX)<= 4*blockSize){
            if(deltaX > 0){
                setDirection(Direction.Right);
            }else {
                setDirection(Direction.Left);
            }
            if(getVx()==0)  setVx(9);
            else setVx(getVx() + 1.2 * 20/100.0);
            setCloseEnough(true);

        }
    }

    public boolean isCloseEnough() {
        return isCloseEnough;
    }

    public void setCloseEnough(boolean closeEnough) {
        isCloseEnough = closeEnough;
    }
}
