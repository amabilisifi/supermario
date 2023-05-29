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

import java.io.IOException;

@JsonSerialize(using = PipeSerializer.class)
@JsonDeserialize(using = PipeDeserializer.class)
public class Pipe extends ImageView {
    private Image shortPipe = new Image(String.valueOf(getClass().getResource("shortPipeNormal.png")));
    private Image mediumPipe = new Image(String.valueOf(getClass().getResource("meduimPipeNormal.png")));
    private Image longPipe = new Image(String.valueOf(getClass().getResource("longPipeNormal.png")));
    private int typeOfPipeNum; // 1 short 2 medium 3 long

    private static double width;
    private static double heightShort;
    private static double heightMedium;
    private static double heightLong;
    private double startX;
    private double startY;
    private double currentX = startX;
    private double currentY = startY;

    public Pipe(int type, double X, double Y) {
        this.typeOfPipeNum = type;
        if (type == 1) {
            this.setImage(shortPipe);
            this.setFitHeight(heightShort);
        }
        if (type == 2) {
            this.setImage(mediumPipe);
            this.setFitHeight(heightMedium);
        }
        if (type == 3) {
            this.setImage(longPipe);
            this.setFitHeight(heightLong);
        }
        this.setFitWidth(width);
        this.startX = X;
        this.startY = Y;
        this.setX(X);
        this.setY(Y);
    }

    public Pipe() {
    }

    /** getter setter **/

    public Image getShortPipe() {
        return shortPipe;
    }

    public void setShortPipe(Image shortPipe) {
        this.shortPipe = shortPipe;
    }

    public Image getMediumPipe() {
        return mediumPipe;
    }

    public void setMediumPipe(Image mediumPipe) {
        this.mediumPipe = mediumPipe;
    }

    public Image getLongPipe() {
        return longPipe;
    }

    public void setLongPipe(Image longPipe) {
        this.longPipe = longPipe;
    }

    public int getTypeOfPipeNum() {
        return typeOfPipeNum;
    }

    public void setTypeOfPipeNum(int typeOfPipeNum) {
        this.typeOfPipeNum = typeOfPipeNum;
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        Pipe.width = width;
    }

    public static double getHeightShort() {
        return heightShort;
    }

    public static void setHeightShort(double heightShort) {
        Pipe.heightShort = heightShort;
    }

    public static double getHeightMedium() {
        return heightMedium;
    }

    public static void setHeightMedium(double heightMedium) {
        Pipe.heightMedium = heightMedium;
    }

    public static double getHeightLong() {
        return heightLong;
    }

    public static void setHeightLong(double heightLong) {
        Pipe.heightLong = heightLong;
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

class PipeSerializer extends JsonSerializer<Pipe> {

    @Override
    public void serialize(Pipe pipe, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("xStart", pipe.getStartX());
        jsonGenerator.writeNumberField("yStart", pipe.getStartY());
        jsonGenerator.writeNumberField("xCurrent", pipe.getCurrentX());
        jsonGenerator.writeNumberField("yCurrent", pipe.getCurrentY());
        jsonGenerator.writeNumberField("typeOfPipeNum", pipe.getTypeOfPipeNum());
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

        Pipe p = new Pipe();
        JsonNode startX = node.get("xStart");
        JsonNode startY = node.get("yStart");
        JsonNode currentX = node.get("xCurrent");
        JsonNode currentY = node.get("yCurrent");
        JsonNode type = node.get("typeOfPipeNum");
        if (startX != null && startY != null) {
            p.setStartX(startX.asDouble());
            p.setStartY(startY.asDouble());
        }
        if (currentX != null && currentY != null) {
           p.setCurrentX(currentX.asDouble());
           p.setCurrentY(currentY.asDouble());
        }

         Image shortPipe = new Image(String.valueOf(getClass().getResource("shortPipeNormal.png")));
         Image mediumPipe = new Image(String.valueOf(getClass().getResource("meduimPipeNormal.png")));
         Image longPipe = new Image(String.valueOf(getClass().getResource("longPipeNormal.png")));
        if (type != null) {
            p.setTypeOfPipeNum(type.asInt());
            if (type.asDouble() == 1) {
                p.setImage(shortPipe);
                p.setFitHeight(Pipe.getHeightShort());
            }
            if (type.asDouble() == 2) {
                p.setImage(mediumPipe);
                p.setFitHeight(Pipe.getHeightMedium());
            }
            if (type.asDouble() == 3) {
                p.setImage(longPipe);
                p.setFitHeight(Pipe.getHeightLong());
            }
            p.setFitWidth(Pipe.getWidth());
            p.setStartX (startX.asDouble());
            p.setStartY(startY.asDouble());
            p.setX(startX.asDouble());
            p.setY(startY.asDouble());
        }

//        LoadData.getInstance().getPipeList().add(p);

        return p;
    }
}
