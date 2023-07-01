package project.gameObjects.enemies;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.gameObjects.Pipe;

public class ToxicPlant extends Enemy {
    private Pipe pipe;
    private boolean isAbleToGoUp = true;

    public ToxicPlant(Pipe pipe) {
        super();
        this.pipe = pipe;
        this.setFitWidth(GameObjectsInfo.getInstance().getToxicPlantWidth());
        this.setFitHeight(GameObjectsInfo.getInstance().getToxicPlantHeight());
        this.setX(pipe.getX()+(GameObjectsInfo.getInstance().getPipeWidth()-GameObjectsInfo.getInstance().getToxicPlantWidth())/2.0);
        this.setY(pipe.getY());
        this.setImage(new Image(String.valueOf(getClass().getResource("/images/enemies/plant.PNG"))));
    }
    @Override
    public void move() {
        // duration is 30 millis .and we should go 42 pixel in 1.5 seconds so 28 pixel per second or 28000 per millis
        if(isAbleToGoUp){
            setY(getY()-0.84*30/100.0);
        }else {
            setY(getY()+0.84*30/100.0);
        }
        if(getY()+this.getFitHeight() <= pipe.getY()){
            setAbleToGoUp(false);
        }
        if(getY()>=pipe.getY()){
            setY(pipe.getY());
            Timeline chill = new Timeline(new KeyFrame(Duration.seconds(2), e -> setAbleToGoUp(true)));
            chill.setCycleCount(1);
            chill.playFromStart();
        }
    }

    public boolean isAbleToGoUp() {
        return isAbleToGoUp;
    }

    public void setAbleToGoUp(boolean ableToGoUp) {
        isAbleToGoUp = ableToGoUp;
    }
}
