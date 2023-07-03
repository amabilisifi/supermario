package project.gameStuff;


import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import project.UsersData;


public class HUI {
    private static HUI instance;
    private Text ScoreText = new Text("SCORE");
    private Text score = new Text(String.valueOf("0000"));
    private Text CoinsText = new Text("COINS");
    private Text coins = new Text(String.valueOf(UsersData.getInstance().getCurrentUser().getCoin()));
    private Text WorldText = new Text("WORLD");
    private Text world = new Text(GameData.getInstance().getCurrentLevel().getLevelNum() + " - " + GameData.getInstance().getCurrentSection().getSectionNum() + 1);
    private Text TimeText = new Text("TIME");
    private Text time = new Text("000");
    private Text LivesText = new Text("LIVES");
    private Text hearts = new Text(UsersData.getInstance().getCurrentUser().getSelectedCharacter().getHearts() + "");

    public HUI() {
        makeItRight(ScoreText);
        makeItRight(score);
        makeItRight(CoinsText);
        makeItRight(coins);
        makeItRight(WorldText);
        makeItRight(world);
        makeItRight(TimeText);
        makeItRight(time);
        makeItRight(LivesText);
        makeItRight(hearts);
    }

    public void paintHUI(Group root) {
        double margin = 80; //a
        double Delta = 15; // H
        double spaceBetween = 150; //d

        ScoreText.setX(margin);
        ScoreText.setY(Delta);
        score.setX(margin + 4);
        score.setY(3 * Delta);

        CoinsText.setX(margin + spaceBetween);
        CoinsText.setY(Delta);
        coins.setX(margin + spaceBetween + 5);
        coins.setY(3 * Delta);

        WorldText.setX(margin + 2 * spaceBetween);
        WorldText.setY(Delta);
        world.setX(margin + 2 * spaceBetween + 6);
        world.setY(3 * Delta);

        TimeText.setX(margin + 3 * spaceBetween + 1);
        TimeText.setY(Delta);
        time.setX(margin + 3 * spaceBetween + 4);
        time.setY(3 * Delta);

        LivesText.setX(margin + 4 * spaceBetween);
        LivesText.setY(Delta);
        hearts.setX(margin + 4 * spaceBetween + 23);
        hearts.setY(3 * Delta);

        if (!root.getChildren().contains(ScoreText))
            root.getChildren().add(ScoreText);
        if (!root.getChildren().contains(CoinsText))
            root.getChildren().add(CoinsText);
        if (!root.getChildren().contains(WorldText))
            root.getChildren().add(WorldText);
        if (!root.getChildren().contains(TimeText))
            root.getChildren().add(TimeText);
        if (!root.getChildren().contains(LivesText))
            root.getChildren().add(LivesText);

        if (!root.getChildren().contains(score))
            root.getChildren().add(score);
        if (!root.getChildren().contains(coins))
            root.getChildren().add(coins);
        if (!root.getChildren().contains(world))
            root.getChildren().add(world);
        if (!root.getChildren().contains(time))
            root.getChildren().add(time);
        if (!root.getChildren().contains(hearts))
            root.getChildren().add(hearts);
    }

    public static HUI getInstance() {
        if (instance == null)
            instance = new HUI();
        return instance;
    }

    public void makeItRight(Text text) {
        text.setVisible(true);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextOrigin(VPos.CENTER);
        text.setFont(Font.font("Courier", 20));
    }

    public int CalculateScore() {
//        // 10 for each coin
//        // 20 for each heart left
//        // 15 for each killed enemy
//        // 1 for each second left
//        // power up level 1: x2 / power up level 2: x3
//        int obtainedCoins = controller.obtainedCoin;
//        int heartsLeft = controller.character.hearts;
//        int timeLeft = (int) (timeLimit - timePassed);
//        int enemyKilled = controller.enemyKilled;
//        boolean powerUp = false;
//        int powerUpCoefficient = 1;
//        int scoreCalculated = powerUpCoefficient * (10 * obtainedCoins + 20 * heartsLeft + 15 * enemyKilled + timeLeft);
//        return scoreCalculated;
        return 0;
    }

    public void setScore(int score) {
        this.score.setText(score + "");
    }

    public void setWorld(int level, int section) {
        this.world.setText(level + " - " + section);
    }

    public void setCoins(int coins) {
        this.coins.setText(coins + "");
    }

    public void setTime(int time) {
        this.time.setText(time + "");
    }

    public void setHearts(int hearts) {
        this.hearts.setText(hearts + "");
    }
}
