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

import java.io.IOException;
@JsonSerialize(using = ToxicPlantSerializer.class)
@JsonDeserialize(using = ToxicPlantDeserializer.class)
public class ToxicPlant extends Enemy {
    Image plant = new Image(String.valueOf(getClass().getResource("plant.PNG")));
    private static double width;
    private static double height;
    private int plantTurn;
    private int count ;
    private double xStart;
    private double yStart;
    private double xCurrent = xStart;
    private double yCurrent = yStart;

    public ToxicPlant(double X, double Y) {
        super(X,Y);
        this.setImage(plant);
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

    public void move(){
        yStart = this.getY();
            double y = this.getY();
        if(count==37){
                plantTurn++;
                count = 0;
            }
            if(plantTurn%2==0){
                y--;
                count++;
            }
            if (plantTurn%2==1){
                y++;
                count++;
            }
            this.setY(y);
    }

    public double getxCurrent() {
        return xCurrent;
    }

    public double getxStart() {
        return xStart;
    }

    public void setxStart(double xStart) {
        this.xStart = xStart;
    }

    public double getyStart() {
        return yStart;
    }

    public void setyStart(double yStart) {
        this.yStart = yStart;
    }

    public void setxCurrent(double xCurrent) {
        this.xCurrent = xCurrent;
    }

    public double getyCurrent() {
        return yCurrent;
    }

    public void setyCurrent(double yCurrent) {
        this.yCurrent = yCurrent;
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        ToxicPlant.width = width;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        ToxicPlant.height = height;
    }

    public int getPlantTurn() {
        return plantTurn;
    }

    public void setPlantTurn(int plantTurn) {
        this.plantTurn = plantTurn;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ToxicPlant() {
        super();
    }

}
class ToxicPlantSerializer extends JsonSerializer<ToxicPlant> {

    @Override
    public void serialize(ToxicPlant plant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("xStart", plant.getxStart());
        jsonGenerator.writeNumberField("yStart", plant.getyStart());
        jsonGenerator.writeNumberField("xCurrent", plant.getxCurrent());
        jsonGenerator.writeNumberField("yCurrent", plant.getyCurrent());
        jsonGenerator.writeEndObject();
    }
}

class ToxicPlantDeserializer extends StdDeserializer<ToxicPlant> {


    protected ToxicPlantDeserializer(Class<?> vc) {
        super(vc);
    }
    public ToxicPlantDeserializer() {
        super(ToxicPlant.class);
    }

    @Override
    public ToxicPlant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        ToxicPlant p = new ToxicPlant();
        JsonNode startX = node.get("xStart");
        JsonNode startY = node.get("yStart");
        JsonNode currentX = node.get("xCurrent");
        JsonNode currentY = node.get("yCurrent");
        if (startX != null) {
            p.setxStart(startX.asDouble());
        }
        if (startY != null) {
            p.setyStart(startY.asDouble());
        }
        if (currentX != null) {
            p.setxCurrent(currentX.asDouble());
        }
        if (currentY != null) {
            p.setyCurrent(currentY.asDouble());
        }
        Image plant = new Image(String.valueOf(getClass().getResource("plant.PNG")));
        p.setImage(plant);
        p.setFitWidth(ToxicPlant.getWidth());
        p.setFitHeight(ToxicPlant.getHeight());
        p.setX(startX.asDouble());
        p.setY(startY.asDouble());
//        LoadData.getInstance().getToxicPlantList().add(p);

        return p;
    }
}
