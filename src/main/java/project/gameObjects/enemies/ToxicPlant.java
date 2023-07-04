package project.gameObjects.enemies;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.gameObjects.Pipe;
import project.gameObjects.PipeType;

import java.io.IOException;

@JsonSerialize(using = ToxicPlantSerializer.class)
@JsonDeserialize(using = ToxicPlantDeserializer.class)
public class ToxicPlant extends Enemy {
    private Pipe pipe;
    private boolean isAbleToGoUp = true;

    public ToxicPlant(Pipe pipe) {
        super();
        setType("ToxicPlant");
        this.pipe = pipe;
        this.setFitWidth(GameObjectsInfo.getInstance().getToxicPlantWidth());
        this.setFitHeight(GameObjectsInfo.getInstance().getToxicPlantHeight());
        this.setX(pipe.getX()+(GameObjectsInfo.getInstance().getPipeWidth()-GameObjectsInfo.getInstance().getToxicPlantWidth())/2.0);
        setStartX(getX());
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

    public Pipe getPipe() {
        return pipe;
    }

    public void setPipe(Pipe pipe) {
        this.pipe = pipe;
    }
}
class ToxicPlantSerializer extends JsonSerializer<ToxicPlant> {

    @Override
    public void serialize(ToxicPlant toxicPlant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("pipeX",toxicPlant.getPipe().getX());
        jsonGenerator.writeNumberField("pipeY",toxicPlant.getPipe().getY());
        jsonGenerator.writeStringField("pipeType",toxicPlant.getPipe().getType().toString());

        jsonGenerator.writeEndObject();
    }
}

class ToxicPlantDeserializer extends StdDeserializer<ToxicPlant> {
    public ToxicPlantDeserializer(Class<?> vc) {
        super(vc);
    }

    public ToxicPlantDeserializer() {
        super(ToxicPlant.class);
    }

    @Override
    public ToxicPlant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        JsonNode pipeX = node.get("pipeX");
        JsonNode pipeY = node.get("pipeY");
        JsonNode pipeType = node.get("pipeType");
        PipeType type = PipeType.Short;
        if(pipeType.asText().equals("Medium")) type = PipeType.Medium;
        if(pipeType.asText().equals("Long")) type = PipeType.Long;


        return new ToxicPlant(new Pipe(type,pipeX.asDouble(),pipeY.asDouble(),false));
    }
}


