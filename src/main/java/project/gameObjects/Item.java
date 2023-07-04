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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.UsersData;
import project.characters.Character;
import project.gameStuff.GameData;
import project.managers.CollisionManager;
import project.MovingEntity;

import java.io.IOException;


@JsonSerialize(using = ItemSerializer.class)
@JsonDeserialize(using = ItemDeserializer.class)
public class Item extends MovingEntity {
    private Block block;
    private double startX;
    private double startY;
    private ItemType itemType;
    private boolean obtained;

    private double speed =0;
    private double Vy =0;
    private boolean onBlock;
    private Direction direction;
    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> move()));

    public Item(Block block) {
        this.block = block;
        double random = 50 * Math.random(); //5 10 15 20
        if (random <= 5) {
            itemType = ItemType.MagicalStar;
        } else if (5 < random && random <= 15) {
            itemType = ItemType.MagicalMushroom;
        } else if (15 < random && random <= 30) {
            itemType = ItemType.MagicalFlower;
        } else if (30 < random && random <= 50) {
            itemType = ItemType.Coin;
        }
        switch (itemType) {
            case Coin -> {
                Image img = new Image(String.valueOf(getClass().getResource("/images/items/coin.PNG")));
                this.setImage(img);
                this.setX(block.getX() + GameObjectsInfo.getInstance().getBlockWidth() / 2.0 - GameObjectsInfo.getInstance().getCoinWidth() / 2.0);
                startX = this.getX();
                this.setY(block.getY() - GameObjectsInfo.getInstance().getCoinHeight());
                this.setFitWidth(GameObjectsInfo.getInstance().getCoinWidth());
                this.setFitHeight(GameObjectsInfo.getInstance().getCoinHeight() - 5);
            }
            case MagicalFlower -> {
                Image img = new Image(String.valueOf(getClass().getResource("/images/items/magicalFlower.PNG")));
                this.setImage(img);
                this.setX(block.getX() + GameObjectsInfo.getInstance().getBlockWidth() / 2.0 - GameObjectsInfo.getInstance().getMagicalFlowerWidth() / 2.0);
                this.setY(block.getY() - GameObjectsInfo.getInstance().getMagicalFlowerHeight());
                this.setFitWidth(GameObjectsInfo.getInstance().getMagicalFlowerWidth());
                this.setFitHeight(GameObjectsInfo.getInstance().getMagicalFlowerHeight() - 5);
            }
            case MagicalMushroom -> {
                Image img = new Image(String.valueOf(getClass().getResource("/images/items/magicalMushroom.PNG")));
                this.setImage(img);
                this.setX(block.getX() + GameObjectsInfo.getInstance().getBlockWidth() / 2.0 - GameObjectsInfo.getInstance().getMagicalMushroomWidth() / 2.0);
                this.setY(block.getY() - GameObjectsInfo.getInstance().getMagicalMushroomHeight());
                this.setFitWidth(GameObjectsInfo.getInstance().getMagicalMushroomWidth());
                this.setFitHeight(GameObjectsInfo.getInstance().getMagicalMushroomHeight() - 5);
                timeline.setCycleCount(Animation.INDEFINITE);
                Timeline tl = new Timeline(new KeyFrame(Duration.seconds(3), e -> timeline.playFromStart()));
                tl.playFromStart();
                speed = 4*UsersData.getInstance().getCurrentUser().getSelectedCharacter().getSpeedo();
                direction = Direction.Right;
            }
            case MagicalStar -> {
                Image img = new Image(String.valueOf(getClass().getResource("/images/items/magicalStar.PNG")));
                this.setImage(img);
                this.setX(block.getX() + GameObjectsInfo.getInstance().getBlockWidth() / 2.0 - GameObjectsInfo.getInstance().getMagicalStarWidth() / 2.0);
                this.setY(block.getY() - GameObjectsInfo.getInstance().getMagicalStarHeight());
                this.setFitWidth(GameObjectsInfo.getInstance().getMagicalStarWidth());
                this.setFitHeight(GameObjectsInfo.getInstance().getMagicalStarHeight() - 5);
                timeline.setCycleCount(Animation.INDEFINITE);
                Timeline tl = new Timeline(new KeyFrame(Duration.seconds(3), e -> timeline.playFromStart()));
                tl.playFromStart();
                speed = 2.5*UsersData.getInstance().getCurrentUser().getSelectedCharacter().getSpeedo();
                direction = Direction.Right;
            }
        }
    }

    public Item(ItemType itemType, Block block) {
        this.block = block;
        this.itemType = itemType;
        if (itemType == ItemType.Coin) {
            Image img = new Image(String.valueOf(getClass().getResource("/images/items/coin.PNG")));
            this.setImage(img);
            this.setX(block.getX() + GameObjectsInfo.getInstance().getBlockWidth() / 2.0 - GameObjectsInfo.getInstance().getCoinWidth() / 2.0);
            this.setY(block.getY() - GameObjectsInfo.getInstance().getCoinHeight() - 5);
            this.setFitWidth(GameObjectsInfo.getInstance().getCoinWidth());
            this.setFitHeight(GameObjectsInfo.getInstance().getCoinHeight());
        }
    }

    public Item(double startX, double startY, ItemType itemType, double speed, double vy, Direction direction) {
        this.startX = startX;
        this.startY = startY;
        this.itemType = itemType;
        this.speed = speed;
        Vy = vy;
        this.direction = direction;
    }

    public Item() {
    }

    @Override
    public void fall() {
        double dt = 20 / 1000.0;
        if (!onBlock) {
            this.setY(this.getY() + this.getVy() * dt);
            this.setVy(this.getVy() + (35 * dt));
        }
    }

    @Override
    public void move() {
        double dt = 20 / 1000.0;
        // moving
        if (onBlock || (itemType == ItemType.MagicalStar && getVy()<=0)) {
            if (direction == Direction.Right)
                this.setX(this.getX() + speed * dt);
            if (direction == Direction.Left)
                this.setX(this.getX() - speed * dt);
        }
        if (this.itemType == ItemType.MagicalStar && onBlock && !this.intersects(block.getBoundsInParent())) {
            magicalStarJump();
            double gravity = GameData.getInstance().getCurrentSection().getGravity();
            setVy(getVy() + gravity * dt);
            setY(getY()+getVy()*dt);
        }
        CollisionManager.getInstance().collisionItemWithObjects();
        fall();
    }

    public void magicalStarJump() {
        Timeline jumpTimeLine = new Timeline(new KeyFrame(Duration.seconds(1.5), e -> {
            if (onBlock) {
                Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
                setVy(character.getJumpVelocity()*4);
            }
        }));
        jumpTimeLine.playFromStart();
    }

    // getter setter

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public boolean isObtained() {
        return obtained;
    }

    public void setObtained(boolean obtained) {
        this.obtained = obtained;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getVy() {
        return Vy;
    }

    public void setVy(double vy) {
        Vy = vy;
    }

    public boolean isOnBlock() {
        return onBlock;
    }

    public void setOnBlock(boolean onBlock) {
        this.onBlock = onBlock;
    }

    public Direction getDirection() {
        if(direction == null)
            return Direction.Still;
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }
}
class ItemSerializer extends JsonSerializer<Item> {

    @Override
    public void serialize(Item item, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("startX", item.getX());
        jsonGenerator.writeNumberField("startY", item.getY());
        jsonGenerator.writeStringField("itemType",item.getItemType().toString());
        jsonGenerator.writeNumberField("speed",item.getSpeed());
        jsonGenerator.writeNumberField("Vy",item.getVy());
        jsonGenerator.writeStringField("direction",item.getDirection().toString());

        jsonGenerator.writeEndObject();
    }
}

class ItemDeserializer extends StdDeserializer<Item> {
    public ItemDeserializer(Class<?> vc) {
        super(vc);
    }

    public ItemDeserializer() {
        super(Item.class);
    }

    @Override
    public Item deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        JsonNode startX = node.get("startX");
        JsonNode startY = node.get("startY");
        JsonNode speed = node.get("speed");
        JsonNode Vy = node.get("Vy");

        JsonNode direction = node.get("direction");
        Direction d = null;
        if(direction!=null) {
            if (direction.asText().equals("Left")) d = Direction.Left;
            if (direction.asText().equals("Right")) d = Direction.Right;
            if (direction.asText().equals("Still")) d = Direction.Still;
        }

        JsonNode type = node.get("type");
        ItemType itemType = ItemType.Coin;
        if(type!=null) {
            if (type.asText().equals("MagicalStar")) itemType = ItemType.MagicalStar;
            if (type.asText().equals("MagicalMushroom")) itemType =  ItemType.MagicalMushroom;
            if (type.asText().equals("Coin")) itemType = ItemType.Coin;
            if (type.asText().equals("MagicalFlower")) itemType =  ItemType.MagicalFlower;
        }


        if (startX != null && startY != null && itemType != null && d != null && speed!=null && Vy!=null)
            return new Item(startX.asDouble(),startY.asDouble(),itemType,speed.asDouble(),Vy.asDouble(),d);

        return null;
    }
}

