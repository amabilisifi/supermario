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
import javafx.scene.shape.Circle;
import project.GameObjectsInfo;
import project.MovingEntity;
import project.gameObjects.Direction;
import project.gameStuff.GameData;
import project.gameStuff.HUI;
import project.managers.CollisionManager;

import java.io.IOException;

@JsonSerialize(using = CharacterSerializer.class)
@JsonDeserialize(using = CharacterDeserializer.class)

public abstract class Character extends MovingEntity {
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
    private double jumpVelocity;
    private double Vy = jumpVelocity;
    private boolean ableToJumpAgain = true;
    private boolean onBlock;
    private boolean ableToMove = true;
    private boolean jumping = false;
    private boolean moving = false;
    private boolean isUpPressed;

    private boolean isSwordCooledDown = true;

    private CharacterModes mode;
    private int hearts = 3;
    private boolean isAntiKnock;
    private Circle electricShield;

    private boolean isNearBossEnemy = false;
    private boolean isGrabbed = false;
    private boolean isDizzy = false;

    private boolean onGround4seconds = false;
    private boolean isDamaged = false;

    public Character() {
        setMode(CharacterModes.Mega);
        setDirection(Direction.Right);
    }

    @Override
    public void move() {
        double dt = 20 / 1000.0;
        setVx(getVx() + getaX() * dt);
        // moving
        if (this.isAbleToMove())
            this.setX(this.getX() + this.getVx() * dt);
        // collision blocks
        CollisionManager.getInstance().collisionCharacter();
        fall();
        // electric shield move
        if (electricShield != null) {
            electricShield.setOpacity(0.5);
            electricShield.setCenterX(this.getX() + this.getFitWidth() / 2.0);
            electricShield.setCenterY(this.getY() + this.getFitHeight() / 2.0);
        }
    }

    @Override
    public void fall() {
        //fall
        double dt = 20 / 1000.0;
        if (!this.isOnBlock())
            Vy += GameData.getInstance().getCurrentSection().getGravity() * dt;
        this.setY(this.getY() + this.getVy() * dt);
    }

    public void damaged() {
        if (mode == CharacterModes.Mini) {
            hearts--;
            isDamaged = true;
            CollisionManager.getInstance().reset();
            HUI.getInstance().setHearts(hearts);
            if (hearts <= 0) {
                System.out.println("lose");
                // gameOver mechanism
            }
        }
        if (mode == CharacterModes.Mega) {
            setMode(CharacterModes.Mini);
        }
        if (mode == CharacterModes.Fiery) {
            setMode(CharacterModes.Mega);
        }
    }

    public void upgradeMode() {
        if (mode == CharacterModes.Mini) {
            setMode(CharacterModes.Mega);
        }
        if (mode == CharacterModes.Mega) {
            setMode(CharacterModes.Fiery);
        }
    }

    public void setFrame() {
        if (getVx() != 0 && !isGrabbed) {
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

    public void dizzied() {
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

    public CharacterModes getMode() {
        return mode;
    }

    public void setMode(CharacterModes mode) {
        this.mode = mode;
        double characterSize = GameObjectsInfo.getInstance().getCharacterWidth();
        double blockSize = GameObjectsInfo.getInstance().getBlockHeight();
        if (mode == CharacterModes.Mini) {
            this.setFitHeight(blockSize);
            this.setFitWidth(0.75 * characterSize);
        }
        if (mode == CharacterModes.Mega || mode == CharacterModes.Fiery) {
            this.setFitHeight(blockSize * 2);
            this.setFitWidth(characterSize);
        }
    }

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public boolean isAntiKnock() {
        return isAntiKnock;
    }

    public void setAntiKnock(boolean antiKnock) {
        isAntiKnock = antiKnock;
    }

    public Circle getElectricShield() {
        return electricShield;
    }

    public void setElectricShield(Circle electricShield) {
        this.electricShield = electricShield;
    }

    public boolean isUpPressed() {
        return isUpPressed;
    }

    public void setUpPressed(boolean upPressed) {
        isUpPressed = upPressed;
    }

    public boolean isNearBossEnemy() {
        return isNearBossEnemy;
    }

    public void setNearBossEnemy(boolean nearBossEnemy) {
        isNearBossEnemy = nearBossEnemy;
    }

    public boolean isGrabbed() {
        return isGrabbed;
    }

    public void setGrabbed(boolean grabbed) {
        isGrabbed = grabbed;
    }

    public boolean isDizzy() {
        return isDizzy;
    }

    public void setDizzy(boolean dizzy) {
        isDizzy = dizzy;
    }

    public boolean isOnGround4seconds() {
        return onGround4seconds;
    }

    public void setOnGround4seconds(boolean onGround4seconds) {
        this.onGround4seconds = onGround4seconds;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public void setDamaged(boolean damaged) {
        isDamaged = damaged;
    }

}

class CharacterSerializer extends JsonSerializer<Character> {

    @Override
    public void serialize(Character character, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("characterType", character.getCharacterType());
        jsonGenerator.writeNumberField("startX", character.getX());
        jsonGenerator.writeNumberField("startY", character.getY());
        jsonGenerator.writeNumberField("hearts", character.getHearts());
        jsonGenerator.writeStringField("mode", character.getMode().toString());

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
        JsonNode character = node.get("characterType");
        Character r = null;
        {
            if (character.asText().equals("Alexandro")) {
                r = new Alexandro();
            }
            if (character.asText().equals("Lorenzo")) {
                r = new Lorenzo();
            }
            if (character.asText().equals("Antonio")) {
                r = new Antonio();
            }
            if (character.asText().equals("Diego")) {
                r = new Diego();
            }

            if (character.asText().equals("Mateo")) {
                r = new Mateo();
            }
            if (character.asText().equals("Pablo")) {
                r = new Pablo();
            }
            if (character.asText().equals("Pedro")) {
                r = new Pedro();
            }
        }

        JsonNode startX = node.get("startX");
        JsonNode startY = node.get("startY");
        ;

        JsonNode hearts = node.get("hearts");
        JsonNode mode = node.get("mode");

        if (startX != null && startY != null) {
            r.setStartX(startX.asDouble());
            r.setX(startX.asDouble());
            r.setStartY(startY.asDouble());
            r.setY(startY.asDouble());
        }
        if (hearts != null) {
            r.setHearts(hearts.asInt());
        }
        if (mode != null)
            switch (mode.asText()){
                case("Mega") -> r.setMode(CharacterModes.Mega);
                case("Fiery") -> r.setMode(CharacterModes.Fiery);
                case ("Mini") -> r.setMode(CharacterModes.Mini);
            }

        return r;
    }

}
