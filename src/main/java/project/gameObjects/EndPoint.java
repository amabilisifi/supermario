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
import javafx.scene.image.ImageView;

import java.io.IOException;

@JsonSerialize(using = EndPointSerializer.class)
@JsonDeserialize(using = EndpointDeserializer.class)
public class EndPoint extends ImageView {
    private double startX;
    private double startY;
    private String type;

    public EndPoint(double startX, double startY) {
        this.startX = startX;
        this.startY = startY;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class EndPointSerializer extends JsonSerializer<EndPoint> {

    @Override
    public void serialize(EndPoint endPoint, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("startX", endPoint.getX());
        jsonGenerator.writeNumberField("startY", endPoint.getY());
        jsonGenerator.writeStringField("type", endPoint.getType());

        jsonGenerator.writeEndObject();
    }
}

class EndpointDeserializer extends StdDeserializer<EndPoint> {
    public EndpointDeserializer(Class<?> vc) {
        super(vc);
    }

    public EndpointDeserializer() {
        super(EndPoint.class);
    }

    @Override
    public EndPoint deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        JsonNode startX = node.get("startX");
        JsonNode startY = node.get("startY");
        JsonNode type = node.get("type");


        if (startX != null && startY != null && type != null) {
            if (type.asText().equals("CheckPoint")) return new CheckPoint(startX.asDouble(), startY.asDouble());
            if (type.asText().equals("Flag")) return new Flag(startX.asDouble(), startY.asDouble());
            if (type.asText().equals("PussyCat")) return new PussyCat(startX.asDouble(), startY.asDouble());
        }

        return null;
    }
}

