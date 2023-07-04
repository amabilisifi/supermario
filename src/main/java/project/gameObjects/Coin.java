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

import java.io.IOException;

@JsonSerialize(using = CoinSerializer.class)
@JsonDeserialize(using = CoinDeserializer.class)
public class Coin extends ImageView {
    private Image img = new Image(String.valueOf(getClass().getResource("/images/coin.png")));
    private double startX;
    private double startY;
    private double currentX = startX;
    private double currentY = startY;
    private boolean token;

    public Coin(double X, double Y) {
        this.setImage(img);
        this.setX(X);
        this.setY(Y);
        this.setFitWidth(GameObjectsInfo.getInstance().getCoinWidth());
        this.setFitHeight(GameObjectsInfo.getInstance().getCoinHeight());
    }

    public Coin() {
    }

    /**
     * getter setter
     **/

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean isToken() {
        return token;
    }

    public void setToken(boolean token) {
        this.token = token;
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
}

class CoinSerializer extends JsonSerializer<Coin> {

    @Override
    public void serialize(Coin coin, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("startX", coin.getX());
        jsonGenerator.writeNumberField("startY", coin.getY());
        jsonGenerator.writeBooleanField("token", coin.isToken());

        jsonGenerator.writeEndObject();
    }
}

class CoinDeserializer extends StdDeserializer<Coin> {

    protected CoinDeserializer(Class<?> vc) {
        super(vc);
    }

    public CoinDeserializer() {
        super(Coin.class);
    }

    @Override
    public Coin deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode startX = node.get("startX");
        JsonNode startY = node.get("startY");
        JsonNode coinToken = node.get("token");
        if (startX != null && startY != null && coinToken != null) {
            return  new Coin(startX.asDouble(),startY.asDouble());
        }

        return null;
    }
}