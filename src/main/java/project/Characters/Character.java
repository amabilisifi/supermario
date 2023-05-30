package project.Characters;

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
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;

@JsonSerialize(using = CharacterSerializer.class)
@JsonDeserialize(using = CharacterDeserializer.class)

public abstract class Character extends ImageView {
    private  Image profilePhoto;
    private String characterType;
    private static double width;
    private static double height;
    private  int price;
    private  double speedo;


    private Image img;
    private Image image1 ;
    private Image image2;
    private Image image3 ;
    private Image image4 ;
    private Image imageSit;
    private int indexOfWalkingFrames = 0;


    private double currentX,currentY;
    private double startX,startY;
    private double speed = speedo;
    private double Vy;
    private double jumpVelocity;
    private boolean ableToJumpAgain = true;
    private boolean isOnBlock;

    public Character() {
    }
    /** useful methods **/
    public void setFrame(){
        if(speed!=0) {
            switch (indexOfWalkingFrames) {
                case 1:
                    setImage(image1);
                    indexOfWalkingFrames++;
                    break;
                case 2:
                    setImage(image2);
                    indexOfWalkingFrames++;
                    break;
                case 3:
                    setImage(image3);
                    indexOfWalkingFrames++;
                    break;
                case 4:
                    setImage(image4);
                    indexOfWalkingFrames++;
                    break;
                default:
                    indexOfWalkingFrames = 1;
                    setImage(image1);
                    indexOfWalkingFrames++;
            }
        }
    }

    /** getter setter **/

    public void setProfilePhoto(Image profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Image getProfilePhoto() {
        return profilePhoto;
    }


    public String getCharacterType() {
        return characterType;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
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

    public Image getImage1() {
        return image1;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageSit(Image imageSit) {
        this.imageSit = imageSit;
    }

    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    public Image getImage2() {
        return image2;
    }

    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    public Image getImage3() {
        return image3;
    }

    public void setImage3(Image image3) {
        this.image3 = image3;
    }

    public Image getImage4() {
        return image4;
    }

    public void setImage4(Image image4) {
        this.image4 = image4;
    }

    public int getIndexOfWalkingFrames() {
        return indexOfWalkingFrames;
    }

    public void setIndexOfWalkingFrames(int indexOfWalkingFrames) {
        this.indexOfWalkingFrames = indexOfWalkingFrames;
    }

    public double getCurrentX() {
        return currentX;
    }

    public void setCurrentX(double currentX) {
        this.currentX = currentX;
        this.setX(currentX);
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

    public  void setSpeed(double speed) {
        this.speed = speed;
    }

    public Image getImageSit() {
        return imageSit;
    }

    public double getCurrentY() {
        return currentY;
    }

    public void setCurrentY(double currentY) {
        this.currentY = currentY;
        this.setY(currentY);
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
        return isOnBlock;
    }

    public void setOnBlock(boolean onBlock) {
        isOnBlock = onBlock;
    }

    public int getPrice() {
        return price;
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
    public Character deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
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
