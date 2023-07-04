package project.gameObjects;

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
import javafx.scene.image.Image;
import project.GameObjectsInfo;
import project.gameObjects.enemies.Direction;
import project.MovingEntity;

import java.io.IOException;


@JsonSerialize(using = SwordSerializer.class)
@JsonDeserialize(using = SwordDeserializer.class)
public class Sword extends MovingEntity {
    private double startX;
    private boolean isTurning = false;
    private boolean isObjectDeleted = false;
    private double characterHeight;
    private Direction direction;
    public Sword(double characterX, double characterY, double characterHeight,Direction direction) {
        double width = 2 * GameObjectsInfo.getInstance().getBlockWidth();
        double height = GameObjectsInfo.getInstance().getSwordHeight();
        this.characterHeight = characterHeight;
        this.direction = direction;
        Image img = new Image(String.valueOf(getClass().getResource("/images/sword.PNG")));
        this.setFitHeight(height);
        if(direction == Direction.Right) {
            this.setX(characterX + GameObjectsInfo.getInstance().getCharacterWidth());
            this.startX = characterX + GameObjectsInfo.getInstance().getCharacterWidth();
            setScaleX(1);
        }
        if (direction == Direction.Left) {
            this.setX(characterX - this.getFitWidth());
            this.startX = characterX- this.getFitWidth();
            setScaleX(-1);
        }
        this.setY(characterY + characterHeight - GameObjectsInfo.getInstance().getBlockHeight());
        this.setFitWidth(width);
        this.setImage(img);
        setDirection(Direction.Right);
    }

    public double getStartX() {
        return startX;
    }

    public boolean isTurning() {
        return isTurning;
    }

    public void setTurning(boolean turning) {
        this.isTurning = turning;
    }

    public boolean isObjectDeleted() {
        return isObjectDeleted;
    }

    public void setObjectDeleted(boolean objectDeleted) {
        isObjectDeleted = objectDeleted;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getCharacterHeight() {
        return characterHeight;
    }

    public void setCharacterHeight(double characterHeight) {
        this.characterHeight = characterHeight;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
class SwordSerializer extends JsonSerializer<Sword> {

    @Override
    public void serialize(Sword sword, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("startX", sword.getX());
        jsonGenerator.writeNumberField("startY", sword.getY());
        jsonGenerator.writeNumberField("characterHeight",sword.getCharacterHeight());
        jsonGenerator.writeStringField("direction",sword.getDirection().toString());

        jsonGenerator.writeEndObject();
    }
}

class SwordDeserializer extends StdDeserializer<Sword> {
    public SwordDeserializer(Class<?> vc) {
        super(vc);
    }

    public SwordDeserializer() {
        super(Block.class);
    }

    @Override
    public Sword deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        JsonNode startX = node.get("startX");
        JsonNode startY = node.get("startY");
        JsonNode characterHeight = node.get("characterHeight");
        JsonNode direction = node.get("direction");
        Direction d = null;
        if(direction!=null) {
            if (direction.asText().equals("Left")) d = Direction.Left;
            if (direction.asText().equals("Right")) d = Direction.Right;
        }


        if (startX != null && startY != null && characterHeight != null && d != null )
            return new Sword(startX.asDouble(),startY.asDouble(),characterHeight.asDouble(),d);

        return null;
    }
}

