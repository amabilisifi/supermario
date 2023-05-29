package project.gameObjects;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;


@JsonSerialize(using = BlockSerializer.class)
@JsonDeserialize(using = BlockDeserializer.class)
public class Block extends ImageView {
    private BlockType blockType;
    private Image img ;
    private static double width;
    private static double height;
    private double startX;
    private double startY;
    private double currentX = startX;
    private double currentY = startY;


    public Block(BlockType blockType,double xStart, double yStart) {
        this.blockType = blockType;
        switch (blockType){
            case Ground -> {
                img  = new Image(String.valueOf(getClass().getResource("/images/block.PNG")));
            }
        }
        this.setImage(img);
        this.startX = xStart;
        this.startY = yStart;
        this.currentX = startX;
        this.currentY = startY;
        this.setX(xStart);
        this.setY(yStart);
        this.setFitWidth(width);
        this.setFitHeight(height);
    }
    public Block(BlockType blockType){
    }
    public Block() {
    }

    /** getter setter **/

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        Block.width = width;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        Block.height = height;
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

class BlockSerializer extends JsonSerializer<Block> {

    @Override
    public void serialize(Block block, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("xStart", block.getStartX());
        jsonGenerator.writeNumberField("yStart", block.getStartY());
        jsonGenerator.writeNumberField("xCurrent", block.getCurrentX());
        jsonGenerator.writeNumberField("yCurrent", block.getCurrentY());
        jsonGenerator.writeNumberField("width", block.getWidth());
        jsonGenerator.writeNumberField("height", block.getHeight());
        jsonGenerator.writeEndObject();
    }
}

class BlockDeserializer extends StdDeserializer<Block> {
    public BlockDeserializer(Class<?> vc) {
        super(vc);
    }
    public BlockDeserializer() {
        super(Block.class);
    }

    @Override
    public Block deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Block b = new Block();
        JsonNode startX = node.get("xStart");
        JsonNode startY = node.get("yStart");
        JsonNode currentX = node.get("xCurrent");
        JsonNode currentY = node.get("yCurrent");
        JsonNode width = node.get("width");
        JsonNode height = node.get("height");
        if (startX != null && startY != null) {
            b.setStartX(startX.asDouble());
            b.setStartY(startY.asDouble());
        }
        if (currentX != null && currentY != null) {
            b.setCurrentX(currentX.asDouble());
            b.setCurrentY(currentY.asDouble());
        }
        if (width != null && height != null) {
            b.setHeight(height.asDouble());
            b.setWidth(width.asDouble());
        }

        Image block = new Image(String.valueOf(getClass().getResource("images/block.PNG")));
        b.setImage(block);

        b.setFitWidth(Block.getWidth());
        b.setFitHeight(Block.getHeight());
        //LoadData.getInstance().getBlockList().add(b);

        return b;
    }
}
