package controller;

import com.sun.org.apache.bcel.internal.generic.NameSignatureInstruction;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Airplane;
import model.Boss;
import model.MiniBoss;
import transition.BossAnimation;
import transition.BossShootAnimation;
import transition.MiniBossAnimation;
import view.Game;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GameMenuController {

    //TODO make this class singleton

    public static HashMap<String, Transition> animations = new HashMap<>();

    public static String username;
    public static int score;
    public static Text scoreText = new Text();
    public static Pane pane;
    private static float hearts = 5;
    private static float hitPercentage = 100;
    private static float damagePercentage = 100;

    public static void setupDifficulty(int difficulty) {
        switch (difficulty) {
            case (0):
                hearts = 10;
                hitPercentage = 50;
                damagePercentage = 150;
                break;
            case (1):
                hearts = 5;
                hitPercentage = 100;
                damagePercentage = 100;
                break;
            case (2):
                hearts = 2;
                hitPercentage = 150;
                damagePercentage = 50;
                break;
        }
    }

    public static Pane createPane() {
        score = 0;
        Pane pane = new Pane();
        pane.setPrefHeight(640);
        pane.setPrefWidth(1080);
        Text heartText = new Text();
        heartText.setText("x" + hearts);
        heartText.setX(60);
        heartText.setY(630);


        Airplane.updateInstance(hearts, hitPercentage, damagePercentage, pane, heartText);
        Airplane airplane = Airplane.getInstance();
        airplane.setBackGround("/view/frames/red.png");


        MiniBoss miniBoss1 = new MiniBoss(1600, 50, 12, pane);
        MiniBoss miniBoss2 = new MiniBoss(1800, 50, 12, pane);
        MiniBoss miniBoss3 = new MiniBoss(2000, 50, 12, pane);
        MiniBossAnimation miniBossAnimation1 = new MiniBossAnimation(miniBoss1);
        MiniBossAnimation miniBossAnimation2 = new MiniBossAnimation(miniBoss2);
        MiniBossAnimation miniBossAnimation3 = new MiniBossAnimation(miniBoss3);
        miniBossAnimation1.play();
        miniBossAnimation2.play();
        miniBossAnimation3.play();

        animations.put("miniBoss1", miniBossAnimation1);
        animations.put("miniBoss2", miniBossAnimation2);
        animations.put("miniBoss3", miniBossAnimation3);

        Rectangle backgroundHealthBar = new Rectangle();
        backgroundHealthBar.setX(420);
        backgroundHealthBar.setY(618);
        backgroundHealthBar.setWidth(220);
        backgroundHealthBar.setHeight(15);
        backgroundHealthBar.setStyle("-fx-fill: black");

        Rectangle healthBar = new Rectangle();
        healthBar.setX(430);
        healthBar.setY(620);
        healthBar.setWidth(200);
        healthBar.setHeight(10);
        healthBar.setStyle("-fx-fill: red");

        Text healthText = new Text();
        healthText.setX(650);
        healthText.setY(630);
        healthText.setText(String.valueOf(25));


        Boss.updateInstance(25, pane, healthBar, healthText);//TODO configure numbers
        Boss boss = Boss.getInstance();
        BossAnimation bossAnimation = new BossAnimation();
        bossAnimation.play();
        boss.setBossAnimation(bossAnimation);
        BossShootAnimation bossShootAnimation = new BossShootAnimation();
        boss.setBossShootAnimation(bossShootAnimation);

        animations.put("bossAnimation", bossAnimation);
        animations.put("bossShootAnimation", bossShootAnimation);

        Rectangle heart = new Rectangle(10, 600,79 / 2, 78 / 2);
        heart.setFill(new ImagePattern(new Image(String.valueOf(Boss.getInstance().getClass().getResource("/view/frames/Heart.png")))));
        airplane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String keyName = event.getCode().getName();
                switch (keyName){
                    case ("Up"):
                        airplane.moveUp();
                        airplane.setBackGround("/view/frames/redUp.png");
                        break;
                    case ("Down"):
                        airplane.moveDown();
                        airplane.setBackGround("/view/frames/redDown.png");
                        break;
                    case ("Right"):
                        airplane.moveRight();
                        break;
                    case ("Left"):
                        airplane.moveLeft();
                        break;
                    case ("Space"):
                        airplane.shoot(boss);
                        break;
                }
            }
        });
        airplane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                airplane.setBackGround("/view/frames/red.png");
            }
        });

        Rectangle background = new Rectangle(1080, 640);
        background.setFill(new ImagePattern(new Image("/view/frames/background.png")));

        Rectangle sky = new Rectangle(1080, 200);
        sky.setFill(new ImagePattern(new Image("/view/frames/sky.png")));

        Rectangle pause = new Rectangle(1030,590, 50 , 50);
        pause.setFill(new ImagePattern(new Image("/view/frames/pause.png")));
        pause.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PauseMenuController.createPane();
            }
        });


        scoreText.setStyle("-fx-font-size: 18");
        updateScore();
        scoreText.setX(900);
        scoreText.setY(630);

        pane.getChildren().add(background);
        pane.getChildren().add(sky);
        pane.getChildren().add(airplane);
        pane.getChildren().add(boss);
        pane.getChildren().add(miniBoss1);
        pane.getChildren().add(miniBoss2);
        pane.getChildren().add(miniBoss3);
        pane.getChildren().add(backgroundHealthBar);
        pane.getChildren().add(healthBar);
        pane.getChildren().add(healthText);
        pane.getChildren().add(heart);
        pane.getChildren().add(heartText);
        pane.getChildren().add(pause);
        pane.getChildren().add(scoreText);

        return pane;
    }

    public static void updateScore() {
        scoreText.setText(String.valueOf(score));
    }
}
