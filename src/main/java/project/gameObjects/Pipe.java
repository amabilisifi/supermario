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
import javafx.scene.image.ImageView;
import project.GameObjectsInfo;
import project.gameStuff.Section;

import java.io.IOException;

@JsonSerialize(using = PipeSerializer.class)
@JsonDeserialize(using = PipeDeserializer.class)
public class Pipe extends ImageView {
    private PipeType type;
    private double startX;
    private double startY;
    private double currentX = startX;
    private double currentY = startY;

    private boolean isSecretPipe;
    private Section section;

    public Pipe(PipeType type, double X, double Y, boolean isSecretPipe) {
        this.isSecretPipe = isSecretPipe;
        this.type = type;

        this.setFitWidth(GameObjectsInfo.getInstance().getPipeWidth());
        switch (type) {
            case Short -> {
                Image img = new Image(String.valueOf(getClass().getResource("/images/pipe/short.png")));
                this.setFitHeight(GameObjectsInfo.getInstance().getShortPipeHeight());
                this.setImage(img);
            }
            case Medium -> {
                Image img = new Image(String.valueOf(getClass().getResource("/images/pipe/medium.png")));
                this.setFitHeight(GameObjectsInfo.getInstance().getMediumPipeHeight());
                this.setImage(img);
            }
            case Long -> {
                Image img = new Image(String.valueOf(getClass().getResource("/images/pipe/long.png")));
                this.setFitHeight(GameObjectsInfo.getInstance().getLongPipeHeight());
                this.setImage(img);
            }
        }
        this.setX(X);
        this.setY(Y);
        this.startX = X;
        this.startY = Y;
        this.currentX = startX;
        this.currentY = startY;
    }

    public Pipe() {
    }

    /**
     * getter setter
     **/

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getCurrentX() {
        return currentX;
    }

    public void setCurrentX(double currentX) {
        this.currentX = currentX;
    }

    public double getCurrentY() {
        return currentY;
    }

    public void setCurrentY(double currentY) {
        this.currentY = currentY;
    }

    public boolean isSecretPipe() {
        return isSecretPipe;
    }

    public void setSecretPipe(boolean secretPipe) {
        isSecretPipe = secretPipe;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public PipeType getType() {
        return type;
    }

    public void setType(PipeType type) {
        this.type = type;
    }
}

class PipeSerializer extends JsonSerializer<Pipe> {

    @Override
    public void serialize(Pipe pipe, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("startX", pipe.getX());
        jsonGenerator.writeNumberField("startY", pipe.getY());
        jsonGenerator.writeBooleanField("isSecretPipe", pipe.isSecretPipe());
        jsonGenerator.writeStringField("type", pipe.getType().toString());

        jsonGenerator.writeEndObject();
    }
}

class PipeDeserializer extends StdDeserializer<Pipe> {
    public PipeDeserializer(Class<?> vc) {
        super(vc);
    }

    public PipeDeserializer() {
        super(Pipe.class);
    }

    @Override
    public Pipe deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        JsonNode startX = node.get("startX");
        JsonNode startY = node.get("startY");
        JsonNode isSecretPipe = node.get("isSecretPipe");
        JsonNode pipeType = node.get("type");
        JsonNode secret = node.get("isSecretPipe");
        PipeType type = null;
        if (pipeType != null) {
            if (pipeType.asText().equals("Long")) {
                type = PipeType.Long;
            }
            if (pipeType.asText().equals("Medium")) {
                type = PipeType.Medium;
            }
            if (pipeType.asText().equals("Short")) {
                type = PipeType.Short;
            }
        }

        if (startX != null && startY != null && isSecretPipe != null && type != null && secret != null) {
            return new Pipe(type, startX.asDouble(), startY.asDouble(), secret.asBoolean());
        }

        return null;
    }
}
