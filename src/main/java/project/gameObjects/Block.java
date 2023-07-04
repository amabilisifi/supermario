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


@JsonSerialize(using = BlockSerializer.class)
@JsonDeserialize(using = BlockDeserializer.class)
public class Block extends ImageView {
    private BlockType blockType;
    private Image img;
    private double startX;
    private double startY;
    private double currentX = startX;
    private double currentY = startY;
    private int itemLeft;
    private boolean ableToGiveAnotherItem;

    private double blockTimer = 0;


    public Block(BlockType blockType, double xStart, double yStart) {
        this.blockType = blockType;
        switch (blockType) {
            case Ground -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/ground.PNG")));
            }
            case Empty -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/empty.PNG")));
            }
            case Simple -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/simple.PNG")));
                itemLeft = 0;
            }
            case ContainCoin -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/simple.PNG")));
                itemLeft = 1;
                ableToGiveAnotherItem = true;
            }
            case ContainManyCoins -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/simple.PNG")));
                itemLeft = (int) (2 + 3 * Math.random());
                ableToGiveAnotherItem = true;
            }
            case Bonus -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/bonus.PNG")));
                itemLeft = 1;
                ableToGiveAnotherItem = true;
            }
            case Slime -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/slime.jpg")));
            }
        }
        this.setImage(img);
        this.startX = xStart;
        this.startY = yStart;
        this.currentX = startX;
        this.currentY = startY;
        this.setX(xStart);
        this.setY(yStart);
        this.setFitWidth(GameObjectsInfo.getInstance().getBlockWidth());
        this.setFitHeight(GameObjectsInfo.getInstance().getBlockHeight());
    }

    public Block(BlockType blockType, double startX, double startY, int itemLeft, boolean ableToGiveAnotherItem) {
        this.blockType = blockType;
        this.startX = startX;
        setX(startX);
        this.startY = startY;
        setY(startY);
        this.itemLeft = itemLeft;
        this.ableToGiveAnotherItem = ableToGiveAnotherItem;

        switch (blockType) {
            case Ground -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/ground.PNG")));
            }
            case Empty -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/empty.PNG")));
            }
            case Simple -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/simple.PNG")));
                itemLeft = 0;
            }
            case ContainCoin -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/simple.PNG")));
                itemLeft = 1;
                ableToGiveAnotherItem = true;
            }
            case ContainManyCoins -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/simple.PNG")));
                itemLeft = (int) (2 + 3 * Math.random());
                ableToGiveAnotherItem = true;
            }
            case Bonus -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/bonus.PNG")));
                itemLeft = 1;
                ableToGiveAnotherItem = true;
            }
            case Slime -> {
                img = new Image(String.valueOf(getClass().getResource("/images/Blocks/slime.jpg")));
            }
        }
        this.setImage(img);
        this.currentX = startX;
        this.currentY = startY;
        this.setFitWidth(GameObjectsInfo.getInstance().getBlockWidth());
        this.setFitHeight(GameObjectsInfo.getInstance().getBlockHeight());
    }

    public Block() {
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

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

    public int getItemLeft() {
        return itemLeft;
    }

    public void setItemLeft(int itemLeft) {
        this.itemLeft = itemLeft;
    }

    public boolean isAbleToGiveAnotherItem() {
        return ableToGiveAnotherItem;
    }

    public void setAbleToGiveAnotherItem(boolean ableToGiveAnotherItem) {
        this.ableToGiveAnotherItem = ableToGiveAnotherItem;
    }

    public double getBlockTimer() {
        return blockTimer;
    }

    public void setBlockTimer(double blockTimer) {
        this.blockTimer = blockTimer;
    }
}

class BlockSerializer extends JsonSerializer<Block> {

    @Override
    public void serialize(Block block, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("startX", block.getX());
        jsonGenerator.writeNumberField("startY", block.getY());
        jsonGenerator.writeBooleanField("ableToGiveAnotherItem", block.isAbleToGiveAnotherItem());
        jsonGenerator.writeNumberField("itemLeft", block.getItemLeft());
        jsonGenerator.writeStringField("blockType", block.getBlockType().toString());

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

        JsonNode startX = node.get("startX");
        JsonNode startY = node.get("startY");
        JsonNode able = node.get("ableToGiveAnotherItem");
        JsonNode itemLeft = node.get("itemLeft");
        JsonNode blockType = node.get("blockType");

        BlockType type = null;
        if (blockType != null) {
            if (blockType.asText().equals("Bonus")) {
                type = BlockType.Bonus;
            }
            if (blockType.asText().equals("Ground")) {
                type = BlockType.Ground;
            }
            if (blockType.asText().equals("ContainCoin")) {
                type = BlockType.ContainCoin;
            }
            if (blockType.asText().equals("Simple")) {
                type = BlockType.Simple;
            }

            if (blockType.asText().equals("ContainManyCoins")) {
                type = BlockType.ContainManyCoins;
            }
            if (blockType.asText().equals("Empty")) {
                type = BlockType.Empty;
            }
            if (blockType.asText().equals("Slime")) {
                type = BlockType.Slime;
            }
        }

        if (startX != null && startY != null && able != null && itemLeft != null && type != null)
            return new Block(type,startX.asDouble(),startY.asDouble(),itemLeft.asInt(),able.asBoolean());

            return null;
    }
}
