package project.managers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import project.MovingEntity;
import project.User;
import project.UsersData;
import project.characters.Character;
import project.characters.CharacterModes;
import project.gameObjects.*;
import project.gameObjects.bossFight.BossEnemy;
import project.gameObjects.bossFight.FireBall;
import project.gameObjects.enemies.*;
import project.gameStuff.GameData;
import project.gameStuff.Section;
import project.gameStuff.SectionDesigner;

import java.util.List;

public class CollisionManager {
    private static CollisionManager instance;
    private final User currentUser = UsersData.getInstance().getCurrentUser();
    private final Character character = UsersData.getInstance().getCurrentUser().getSelectedCharacter();
    private Section section = GameData.getInstance().getCurrentSection();
    private List<Block> blockList = section.getBlockList();
    private List<Pipe> pipeList = section.getPipeList();
    private List<Coin> coinList = section.getCoinList();
    private List<Item> itemList = section.getItemList();
    private final Group root = GameData.getInstance().getRoot();
    private final boolean upPressed = character.isUpPressed();
    private List<Enemy> enemyList = section.getEnemyList();
    private boolean collisionWithEnd = false;
    private double onGroundTime = 0;
    private boolean countedOnCheckUnderBlock = false;

    public void UpdateCollisionManagerList(Section section) {
        blockList = section.getBlockList();
        pipeList = section.getPipeList();
        coinList = section.getCoinList();
        itemList = section.getItemList();
        enemyList = GameData.getInstance().getCurrentSection().getEnemyList();
    }

    public void collisionCharacter() {
        collisionWithBlocksChar();
        collisionWithItemsChar();
        collisionWithCoinChar();
        collisionWithPipeChar();
        collisionWithEnemyChar();
        collisionWithEndPointChar();
//        characterOnGroundTimer();
        if (GameData.getInstance().isBossScene())
            checkUnderBlock();
    }

    public void collisionWithBlocksChar() {
        Bounds marioBounds = character.getBoundsInParent();
        boolean flag = false;
        for (Block block : blockList) {
            Bounds blockBounds = block.getBoundsInParent();
            if (blockBounds.intersects(marioBounds)) {
                double dy = character.getY() - block.getCurrentY();
//                double dx = character.getCurrentX() - block.getCurrentX();

                if (dy <= 0 && !character.isJumping()) {
                    character.setY(block.getCurrentY() - character.getFitHeight());
                    character.setOnBlock(true);
                    flag = true;
                    if (!upPressed)
                        character.setVy(0);
                    if (block.getBlockType() == BlockType.Slime) {
                        character.setVy(character.getJumpVelocity() * 1.5);
                    }
                } else if (dy >= 0) {
                    character.setVy(0);
                    if (block.getBlockType() == BlockType.Simple && character.getMode() != CharacterModes.Mini) {
                        root.getChildren().remove(block);
                        blockList.remove(block);
                    }
                    if (block.getBlockType() == BlockType.Bonus && block.getItemLeft() >= 0 && block.isAbleToGiveAnotherItem()) {
                        block.setAbleToGiveAnotherItem(false);
                        Item item = new Item(block);
                        GameData.getInstance().getCurrentSection().addItem(item);
                        root.getChildren().add(item);
                        //block.setItemLeft(block.getItemLeft()-1);
                    }
                    if ((block.getBlockType() == BlockType.ContainCoin || block.getBlockType() == BlockType.ContainManyCoins) && block.getItemLeft() > 0 && block.isAbleToGiveAnotherItem()) {
                        block.setAbleToGiveAnotherItem(false);
                        Item item = new Item(ItemType.Coin, block);
                        GameData.getInstance().getCurrentSection().addItem(item);
                        block.setItemLeft(block.getItemLeft() - 1);
                        root.getChildren().add(item);
                    }
                }
                break;
            }
        }
        if (!flag) {
            character.setOnBlock(false);
        }
        double dt = 20.0 / 1000;
        double deltaX = character.getVx() * dt;
        double yRunner = character.getY();
        //double deltaY = character.getVy() * dt;
        for (Block block : blockList) {
            //right of mario
            double rightRunner = character.getX() + character.getFitWidth() + deltaX;
            if (rightRunner > block.getX() && rightRunner < block.getX() + block.getFitWidth() &&
                    yRunner <= block.getY() && yRunner + character.getFitHeight() >= block.getY() + block.getFitHeight()) {
                character.setVx(0);
            }
            //left Of mario
            double leftRunner = character.getX() + deltaX;
            if (leftRunner > block.getX() && leftRunner < block.getX() + block.getFitWidth() &&
                    yRunner <= block.getY() && yRunner + character.getFitHeight() >= block.getY() + block.getFitHeight()) {
                character.setVx(0);
            }
        }
    }

    public void collisionWithCoinChar() {
        Coin collisionCoin = null;
        for (Coin coin : coinList) {
            if (character.intersects(coin.getBoundsInParent())) {
                collisionCoin = coin;
                currentUser.setCoin(currentUser.getCoin() + 1);
                System.out.println(currentUser.getCoin());
            }
        }
        coinList.remove(collisionCoin);
        root.getChildren().remove(collisionCoin);
    }

    public void collisionWithPipeChar() {
        Bounds marioBounds = character.getBoundsInParent();
        for (Pipe pipe : pipeList) {
            Bounds blockBounds = pipe.getBoundsInParent();
            if (blockBounds.intersects(marioBounds)) {
                double dy = character.getY() - pipe.getCurrentY();

                if (dy < character.getFitWidth() / 2.0 && character.getX() + character.getFitWidth() > pipe.getX()) {
                    if (pipe.isSecretPipe()) {
                        LevelManager.getInstance().goToSecretSection(pipe);
                    }
                    character.setOnBlock(true);
                    if (!upPressed)
                        character.setVy(0);
                }
                break;
            } else {
                character.setOnBlock(false);
            }
        }
        double dt = 20.0 / 1000;
        double deltaX = character.getVx() * dt;
        double yRunner = character.getY();
        for (Pipe pipe : pipeList) {
            //right of mario
            double rightRunner = character.getX() + character.getFitWidth() + deltaX;
            if (rightRunner > pipe.getX() && rightRunner < pipe.getX() + pipe.getFitWidth() &&
                    yRunner + character.getFitHeight() <= pipe.getY() + pipe.getFitHeight() && yRunner + character.getFitHeight() > pipe.getY() + 5) {
                character.setVx(0);
            }
            //left Of mario
            double leftRunner = character.getX() + deltaX;
            if (leftRunner > pipe.getX() && leftRunner < pipe.getX() + pipe.getFitWidth() &&
                    yRunner + character.getFitHeight() <= pipe.getY() + pipe.getFitHeight() && yRunner + character.getFitHeight() > pipe.getY() + 5) {
                character.setVx(0);
            }
        }
    }

    public void collisionWithItemsChar() {
        Item collisionItem = null;
        for (Item item : itemList) {
            if (character.intersects(item.getBoundsInParent())) {
                item.setObtained(true);
                collisionItem = item;
                item.getBlock().setAbleToGiveAnotherItem(true);
                if (item.getItemType() == ItemType.Coin) {
                    currentUser.setCoin(currentUser.getCoin() + 1);
                    System.out.println(currentUser.getCoin());
                }
                if (item.getBlock().getBlockType() == BlockType.ContainCoin) {
                    item.getBlock().setBlockType(BlockType.Simple);
                }
                if (item.getBlock().getBlockType() == BlockType.ContainManyCoins && item.getBlock().getItemLeft() <= 0) {
                    item.getBlock().setBlockType(BlockType.Empty);
                    item.getBlock().setImage(new Image(String.valueOf(getClass().getResource("/images/Blocks/empty.PNG"))));
                }
                if (item.getItemType() == ItemType.MagicalFlower) {
                    // +20 score
                    GameData.getInstance().increaseScore(20);
                    character.upgradeMode();
                }
                if (item.getItemType() == ItemType.MagicalMushroom) {
                    // +30 score
                    GameData.getInstance().increaseScore(30);
                    character.upgradeMode();
                }
                if (item.getItemType() == ItemType.MagicalStar) {
                    // +40 score
                    GameData.getInstance().increaseScore(40);
                    character.upgradeMode();
                    // antiKnock
                    character.setAntiKnock(true);
                    Circle electricShield = new Circle(character.getX() + character.getFitWidth() / 2.0, character.getY() + character.getFitHeight() / 2.0, character.getFitHeight() / 1.8);
                    character.setElectricShield(electricShield);
                    root.getChildren().add(electricShield);
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(15), e -> {
                        character.setAntiKnock(false);
                        character.setElectricShield(null);
                        root.getChildren().remove(electricShield);
                    }));
                    timeline.playFromStart();
                }
                root.getChildren().remove(item);
            }
        }
        itemList.remove(collisionItem);
    }

    public void collisionWithEnemyChar() {
        Enemy e = null;
        for (Enemy enemy : enemyList) {
            if (character.intersects(enemy.getBoundsInParent()) && !(enemy instanceof BossEnemy)) {
                if (character.isAntiKnock()) {
                    e = enemy;
                    root.getChildren().remove(enemy);
                    enemyList.remove(enemy);
                    character.setAntiKnock(false);
                    root.getChildren().remove(character.getElectricShield());
                    character.setElectricShield(null);
                    break;
                } else {
                    if (character.getY() + character.getFitHeight() <= enemy.getY() + enemy.getFitHeight() / 2.0) {
                        if (enemy instanceof Mushroom) {
                            e = enemy;
                            root.getChildren().remove(e);
                            // score ++
                            GameData.getInstance().increaseScore(1);
                            currentUser.setCoin(currentUser.getCoin() + 3);
                        }
                        if (enemy instanceof Turtle) {
                            if (((Turtle) enemy).isAbleToBeThrown()) {
                                ((Turtle) enemy).setAbleToBeThrown(false);
                                int random = (int) (2 * Math.random());
                                if (random == 0) enemy.setDirection(Direction.Right);
                                else enemy.setDirection(Direction.Left);
                                enemy.setaX(-1);
                                enemy.setVx(13);
                                ((Turtle) enemy).setCrazyRN(true);
                            }
                            if (((Turtle) enemy).isBeenCrazy()) {
                                e = enemy;
                                root.getChildren().remove(e);
                                // score + 2
                                GameData.getInstance().increaseScore(2);
                                currentUser.setCoin(currentUser.getCoin() + 3);
                            }
                        }
                    } else {
                        character.damaged();
                        System.out.println("die");
                        System.out.println(character.getHearts());
                    }
                    if (enemy instanceof Spiny || enemy instanceof ToxicPlant) {
                        System.out.println("die");
                        character.damaged();
                        System.out.println(character.getHearts());
                    }
                }
            }
        }
        if (e != null)
            enemyList.remove(e);
    }

    public void collisionWithEndPointChar() {
        section = GameData.getInstance().getCurrentSection();
        if (character.intersects(section.getEndPoint().getBoundsInParent()) && !collisionWithEnd) {
            collisionWithEnd = true;
            if (section.getEndPoint() instanceof Flag)
                LevelManager.getInstance().goToNextSection();
            if(section.getEndPoint() instanceof PussyCat)
                LevelManager.getInstance().turningBackFromSecretLevel();
        }
    }


    public void collisionWeapon(MovingEntity weapon) {
        // enemy
        Enemy e = null;
        for (Enemy enemy : enemyList) {
            if (weapon.intersects(enemy.getBoundsInParent()) && !(enemy instanceof BossEnemy)) {
                e = enemy;
                root.getChildren().remove(enemy);
                enemyList.remove(enemy);
                character.setAntiKnock(false);
                root.getChildren().remove(character.getElectricShield());
                character.setElectricShield(null);
                root.getChildren().remove(weapon);
                break;
            }
            if (weapon.intersects(enemy.getBoundsInParent()) && enemy instanceof BossEnemy && !((BossEnemy) enemy).isDamaged() && !weapon.isObjectDeleted()) {
                ((BossEnemy) enemy).damaged(2);
                ((BossEnemy) enemy).setDamaged(true);
                System.out.println("mast sh");
                root.getChildren().remove(weapon);
                weapon.setObjectDeleted(true);
                break;
            }
        }
        if (e != null) {
            enemyList.remove(e);
            if (e instanceof Spiny) GameData.getInstance().increaseScore(3);
            if (e instanceof ToxicPlant) GameData.getInstance().increaseScore(1);
            if (e instanceof Turtle) GameData.getInstance().increaseScore(2);
            if (e instanceof Mushroom) GameData.getInstance().increaseScore(1);

        }
        // gameObjects
        collisionWeaponsWithGameObjects(weapon);
    }

    public void collisionWeaponsWithGameObjects(MovingEntity entity) {
        for (Pipe pipe : pipeList) {
            if (entity.intersects(pipe.getLayoutBounds())) {
                if (entity.getY() >= pipe.getY()) {
                    if (entity.getX() + entity.getFitWidth() >= pipe.getX()) {
                        if (entity instanceof Sword)
                            ((Sword) entity).setTurning(true);
                    }
                    if (entity.getX() >= pipe.getX()) {
                        if (entity instanceof Sword)
                            ((Sword) entity).setTurning(false);
                    }
                }
            }
        }
        for (Block block : blockList) {
            if (entity.intersects(block.getLayoutBounds())) {
                if (entity.getY() >= block.getY()) {
                    if (entity.getX() + entity.getFitWidth() >= block.getX()) {
                        if (entity instanceof Sword)
                            ((Sword) entity).setTurning(true);
                    }
                    if (entity.getX() >= block.getX() + block.getFitWidth()) {
                        if (entity instanceof Sword)
                            ((Sword) entity).setTurning(false);
                    }
                }
            }
        }
    }


    public void collisionItemWithObjects() {
        for (Item i : itemList) {
            collisionWithObjectsInGame(i);
        }
    }

    public void collisionWithObjectsInGame(MovingEntity entity) {
        boolean flag = false;
        for (Pipe pipe : pipeList) {
            if (entity.intersects(pipe.getLayoutBounds())) {
                if (entity.getY() >= pipe.getY()) {
                    if (entity.getX() + entity.getFitWidth() >= pipe.getX()) {
                        entity.setDirection(Direction.Left);
                    }
                    if (entity.getX() >= pipe.getX()) {
                        entity.setDirection(Direction.Right);
                    }
                } else {
                    entity.setOnBlock(true);
                    if (entity instanceof BossEnemy)
                        entity.setY(pipe.getCurrentY() - entity.getFitHeight());
                    entity.setVy(0);
                    flag = true;
                }
            }
        }
        for (Block block : blockList) {
            if (entity.intersects(block.getLayoutBounds())) {
                if (entity.getY() >= block.getY()) {
                    if (entity.getX() + entity.getFitWidth() >= block.getX()) {
                        entity.setDirection(Direction.Left);
                    }
                    if (entity.getX() >= block.getX() + block.getFitWidth()) {
                        entity.setDirection(Direction.Right);
                    }
                } else {
                    entity.setOnBlock(true);
                    if (entity instanceof BossEnemy) {
                        entity.setY(block.getCurrentY() - entity.getFitHeight());
                        ((BossEnemy) entity).setAbleToMove(true);
                    }
                    entity.setVy(0);
                    flag = true;
                }
            }
        }
        if (entity instanceof Enemy && !(entity instanceof Turtle))
            hasBlockUnder(entity);
        if (entity instanceof Turtle && !((Turtle) entity).isCrazyRN())
            hasBlockUnder(entity);
        if (!flag) {
            entity.setOnBlock(false);
        }
    }

    public void setCollisionWithEnd(boolean collisionWithEnd) {
        this.collisionWithEnd = collisionWithEnd;
    }

    public void hasBlockUnder(MovingEntity entity) {
        if (entity.getDirection() == Direction.Left && getBlockOf(entity.getX() - 10, entity.getY() + entity.getFitHeight()) == null) {
            entity.setDirection(Direction.Right);
        }
        if (entity.getDirection() == Direction.Right && getBlockOf(entity.getX() + entity.getFitWidth() + 10, entity.getY() + entity.getFitHeight()) == null) {
            entity.setDirection(Direction.Left);
        }
    }

    public Block getBlockOf(double x, double y) {
        for (Block block : blockList) {
            if (block.getX() <= x && block.getX() + block.getFitWidth() >= x &&
                    block.getY() <= y && block.getY() + block.getFitHeight() >= y) {
                return block;
            }
        }
        return null;
    }

    public static CollisionManager getInstance() {
        if (instance == null)
            instance = new CollisionManager();
        return instance;
    }

    public void collisionFireBall(FireBall fireBall) {
        collisionFireBallWithObjects(fireBall);
        collisionFireBallWithCharacter(fireBall);
    }

    public void collisionFireBallWithObjects(FireBall fireBall) {
        for (Pipe pipe : pipeList) {
            if (fireBall.intersects(pipe.getLayoutBounds())) {
                SectionDesigner.getInstance().removeFromRoot(fireBall);
                fireBall.getTimelineMove().stop();
            }
        }
        for (Block block : blockList) {
            if (fireBall.intersects(block.getLayoutBounds())) {
                SectionDesigner.getInstance().removeFromRoot(fireBall);
                fireBall.getTimelineMove().stop();
            }
        }
    }

    public void collisionFireBallWithCharacter(FireBall fireBall) {
        if (fireBall.intersects(character.getBoundsInParent())) {
            character.damaged();
            SectionDesigner.getInstance().removeFromRoot(fireBall);
        }
    }


    public void collisionBossEnemy(BossEnemy bossEnemy) {
        collisionBossEnemyWithCharacter(bossEnemy);
    }

    public void collisionBossEnemyWithCharacter(BossEnemy bossEnemy) {
        if (character.intersects(bossEnemy.getBoundsInParent())) {
            if (bossEnemy.isJumping() && character.getY() + character.getFitHeight() / 2.0 <= bossEnemy.getY() + bossEnemy.getFitHeight()) {
                // character dizziness
                character.setDizzy(true);
                System.out.println("dizzy");
                Timeline dizziness = new Timeline(new KeyFrame(Duration.seconds(5), e -> character.setDizzy(false)));
                dizziness.playFromStart();
            }
            if (!bossEnemy.isJumping()) {
                if (character.getY() + character.getFitHeight() <= bossEnemy.getY() + 1 / 5.0 * bossEnemy.getFitHeight() && !bossEnemy.isDamaged()) {
                    // strike
                    bossEnemy.damaged(1);
                }
            }
        }
    }

    public void checkUnderBlock() {
        for (Block b : blockList) {
            if (character.intersects(b.getBoundsInParent()) && character.getY() + character.getFitHeight() <= b.getY()) {
                if (b.getBlockType() == BlockType.Ground && !countedOnCheckUnderBlock) {
                    countedOnCheckUnderBlock = true;
                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
                        if (countedOnCheckUnderBlock) {
                            onGroundTime += 0.01;
                            countedOnCheckUnderBlock = false;
                        }
                    }));
                    timeline.playFromStart();
                    System.out.println(onGroundTime);
                    if (onGroundTime >= 4) {
                        character.setOnGround4seconds(true);
                    }
                }
            }
        }
        if (!character.isOnBlock())
            onGroundTime = 0;
    }
}
