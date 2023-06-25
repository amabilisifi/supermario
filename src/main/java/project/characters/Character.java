package project.characters;

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
import project.gameStuff.GameData;
import project.managers.CollisionManager;
import project.managers.MovingEntity;

import java.io.IOException;

@JsonSerialize(using = CharacterSerializer.class)
@JsonDeserialize(using = CharacterDeserializer.class)

public abstract class Character extends MovingEntity {
    private static double width;
    private static double height;
    private Image profilePhoto;
    private String characterType;
    private int price;
    private double speedo;


    private Image img;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private Image imageSit;
    private int indexOfWalkingFrames = 0;


    private double startX, startY;
    private double speed = speedo;
    private double jumpVelocity;
    private double Vy = jumpVelocity;
    private boolean ableToJumpAgain = true;
    private boolean onBlock;
    private boolean ableToMove = true;
    private boolean jumping = false;
    private boolean moving = false;

    private boolean isSwordCooledDown = true;

    public Character() {
    }
    @Override
    public void move() {
        double dt = 20 / 1000.0;
        // moving
        if (this.isAbleToMove())
            this.setX(this.getX() + this.getSpeed() * dt);
        // collision blocks
        CollisionManager.getInstance().collisionCharacter();
        fall();
    }

    @Override
    public void fall() {
        //fall
        double dt = 20 / 1000.0;
        if (!this.isOnBlock())
            Vy += GameData.getInstance().getCurrentSection().getGravity() * dt;
        this.setY(this.getY() + this.getVy() * dt);
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        Character.width = width;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        Character.height = height;
    }

    /**
     * useful methods
     **/
    public void setFrame() {
        if (speed != 0) {
            switch (indexOfWalkingFrames) {
                case 1 -> {
                    setImage(image1);
                    indexOfWalkingFrames++;
                }
                case 2 -> {
                    setImage(image2);
                    indexOfWalkingFrames++;
                }
                case 3 -> {
                    setImage(image3);
                    indexOfWalkingFrames++;
                }
                case 4 -> {
                    setImage(image4);
                    indexOfWalkingFrames++;
                }
                default -> {
                    indexOfWalkingFrames = 1;
                    setImage(image1);
                    indexOfWalkingFrames++;
                }
            }
        }
    }

    /**
     * getter setter
     **/


    public boolean isAbleToMove() {
        return ableToMove;
    }

    public void setAbleToMove(boolean ableToMove) {
        this.ableToMove = ableToMove;
    }
    public Image getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Image profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getCharacterType() {
        return characterType;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }

    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    public void setImage2(Image image2) {
        this.image2 = image2;
    }


    public void setImage3(Image image3) {
        this.image3 = image3;
    }


    public void setImage4(Image image4) {
        this.image4 = image4;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Image getImageSit() {
        return imageSit;
    }

    public void setImageSit(Image imageSit) {
        this.imageSit = imageSit;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getVy() {
        return Vy;
    }

    public void setVy(double vy) {
        Vy = vy;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public double getJumpVelocity() {
        return jumpVelocity;
    }

    public void setJumpVelocity(double jumpVelocity) {
        this.jumpVelocity = jumpVelocity;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean isAbleToJumpAgain() {
        return ableToJumpAgain;
    }

    public void setAbleToJumpAgain(boolean ableToJumpAgain) {
        this.ableToJumpAgain = ableToJumpAgain;
    }

    public boolean isOnBlock() {
        return onBlock;
    }

    public void setOnBlock(boolean onBlock) {
        this.onBlock = onBlock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getSpeedo() {
        return speedo;
    }

    public void setSpeedo(double speedo) {
        this.speedo = speedo;
    }

    public boolean isSwordCooledDown() {
        return isSwordCooledDown;
    }

    public void setSwordCooledDown(boolean swordCooledDown) {
        isSwordCooledDown = swordCooledDown;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}

class CharacterSerializer extends JsonSerializer<Character> {

    @Override
    public void serialize(Character character, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
//        jsonGenerator.writeNumberField("xStart", character.getStartX());
//        jsonGenerator.writeNumberField("yStart", character.getStartY());
//        jsonGenerator.writeNumberField("xCurrent", character.getCurrentX());
//        jsonGenerator.writeNumberField("yCurrent", character.getCurrentY());
//        jsonGenerator.writeNumberField("deltaY", character.getDeltaY());
//        jsonGenerator.writeNumberField("deltaX", character.getDeltaX());
//        jsonGenerator.writeNumberField("hearts", character.getHearts());
//        jsonGenerator.writeBooleanField("ableToMove", character.isAbleToMove());
        jsonGenerator.writeStringField("character", character.getCharacterType());

        jsonGenerator.writeEndObject();
    }
}

class CharacterDeserializer extends StdDeserializer<Character> {
    public CharacterDeserializer(Class<?> vc) {
        super(vc);
    }

    public CharacterDeserializer() {
        super(Character.class);
    }

    @Override
    public Character deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode character = node.get("character");
        Character r = null;
        if (character.asText().equals("Alexandro")) {
            r = new Alexandro();
        }
        if (character.asText().equals("Lorenzo")) {
            r = new Lorenzo();
        }

//        JsonNode startX = node.get("xStart");
//        JsonNode startY = node.get("yStart");
//        JsonNode currentX = node.get("xCurrent");
//        JsonNode currentY = node.get("yCurrent");
//
//        JsonNode deltaY = node.get("deltaY"
//        );
//        JsonNode deltaX = node.get("deltaX");
//        JsonNode hearts = node.get("hearts");
//        JsonNode ableToMove = node.get("ableToMove");
//        if (startX != null && startY != null) {
//            r.setStartX(startX.asDouble());
//            r.setStartY(startY.asDouble());
//        }
//        if (currentX != null && currentY != null) {
//            r.setCurrentX(currentX.asDouble());
//            r.setCurrentY(currentY.asDouble());
//        }
//        if (deltaX != null) {
//            r.setDeltaX((int) deltaX.asDouble());
//        }
//        if (deltaY != null) {
//            r.setDeltaY((int) deltaY.asDouble());
//        }
//        if (hearts != null) {
//            r.setHearts(hearts.asInt());
//        }
//        if (ableToMove != null) {
//            r.setAbleToMove(ableToMove.asBoolean());
//        }
//        r.setX(0);
//        r.setY(100);

        return r;
    }

}
