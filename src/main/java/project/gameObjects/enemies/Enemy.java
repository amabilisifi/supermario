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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import project.GameObjectsInfo;
import project.MovingEntity;
import project.gameObjects.Direction;
import project.gameStuff.GameData;
import project.managers.CollisionManager;

import java.io.IOException;


@JsonSerialize(using = EnemySerializer.class)
@JsonDeserialize(using = EnemyDeserializer.class)
public abstract class Enemy extends MovingEntity {
    private String type;
    private double startX;
    private double startY;
    private Direction direction;
    Timeline timelineMove;
    Timeline timeLineFall;

    public Enemy(double startX, double startY) {
        this.startX = startX;
        this.startY = startY;
        setVx(4);
        setX(startX);
        setY(startY);
        this.setFitHeight(GameObjectsInfo.getInstance().getBlockHeight());
        timelineMove = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            move();
        }));
        direction = Direction.Left;
        timelineMove.setCycleCount(Animation.INDEFINITE);
        timelineMove.playFromStart();

        timeLineFall = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            fall();
        }));
        GameData.getInstance().addTimeLine(timelineMove);
        GameData.getInstance().addTimeLine(timeLineFall);
        timeLineFall.setCycleCount(Animation.INDEFINITE);
        timeLineFall.playFromStart();
    }

    public Enemy() {
        timelineMove = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            move();
        }));
        direction = Direction.Left;
        timelineMove.setCycleCount(Animation.INDEFINITE);
        timelineMove.playFromStart();
        GameData.getInstance().addTimeLine(timelineMove);
    }


    @Override
    public void move() {
        double dt = 20 / 100.0;
        if (direction == Direction.Right) {
            setVx(getVx() - Math.abs(getaX() * dt / 3));
            setX(getX() + Math.abs(getVx()) * dt);
            setScaleX(-1);
        }
        if (direction == Direction.Left) {
            setVx(getVx() - Math.abs(getaX() * dt / 3));
            setX(getX() - Math.abs(getVx()) * dt);
            setScaleX(1);
        }
        CollisionManager.getInstance().collisionWithObjectsInGame(this);
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Timeline getTimelineMove() {
        return timelineMove;
    }

    public void setTimelineMove(Timeline timelineMove) {
        this.timelineMove = timelineMove;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }
}

class EnemySerializer extends JsonSerializer<Enemy> {

    @Override
    public void serialize(Enemy enemy, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("startX", enemy.getX());
        jsonGenerator.writeNumberField("startY", enemy.getY());
        jsonGenerator.writeStringField("type", enemy.getType());
        jsonGenerator.writeNumberField("Vx", enemy.getVx());
        jsonGenerator.writeNumberField("Vy", enemy.getVy());


        jsonGenerator.writeEndObject();
    }
}

class EnemyDeserializer extends StdDeserializer<Enemy> {
    public EnemyDeserializer(Class<?> vc) {
        super(vc);
    }

    public EnemyDeserializer() {
        super(Enemy.class);
    }

    @Override
    public Enemy deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        JsonNode startX = node.get("startX");
        JsonNode startY = node.get("startY");
        JsonNode type = node.get("type");
        JsonNode Vx = node.get("Vx");
        JsonNode Vy = node.get("Vy");
        if (startX != null && startY != null && Vx != null && Vy != null) {
            Enemy enemy = null;
            if (type != null) {
                if (type.asText().equals("Mushroom")) enemy = new Mushroom(startX.asDouble(), startY.asDouble());
                if (type.asText().equals("Spiny")) enemy = new Spiny(startX.asDouble(), startY.asDouble());
                if (type.asText().equals("Turtle")) enemy = new Turtle(startX.asDouble(), startY.asDouble());
            }
            return enemy;
        }

        return null;
    }
}

