package model;

import controller.EndMenuController;
import controller.GameMenuController;
import controller.UserDatabaseController;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import view.Game;

import java.io.File;

public class Airplane extends Rectangle {

    private float hearts;
    private float hitPercentage;
    private float damagePercentage;
    private Pane pane;
    private Text heartText;
    public static boolean coolDown;


    private static Airplane instance;

    public static Airplane getInstance() {
        return instance;
    }

    public static void updateInstance(float hearts, float hitPercentage, float damagePercentage, Pane pane, Text heartText) {
        instance = new Airplane(hearts, hitPercentage, damagePercentage, pane, heartText);
    }

    private Airplane(float hearts, float hitPercentage, float damagePercentage, Pane pane, Text heartText) {
        super(20,20, 109, 91);
        this.hearts = hearts;
        this.hitPercentage = hitPercentage;
        this.damagePercentage = damagePercentage;
        this.pane = pane;
        this.heartText = heartText;
        coolDown = true;
    }

    public static void removeInstance() {
        instance = null;
    }

    public void moveUp() {
        if (!this.hitUp())
            this.setY(this.getY() - 10);
    }

    public void moveDown() {
        if (!this.hitDown())
            this.setY(this.getY() + 10);
    }

    public void moveRight() {
        if (!this.hitRight())
            this.setX(this.getX() + 10);
    }

    public void moveLeft() {
        if (!this.hitLeft()) {
            this.setX(this.getX() - 10);
        }
    }

    private boolean hitUp() {
        if (this.getY() <= 0)
            return true;
        return false;
    }

    private boolean hitDown() {
        if (this.getY() + this.getWidth() >= 640)
            return true;
        return false;
    }

    private boolean hitRight() {
        if (this.getX() + this.getHeight() >= 1080)
            return true;
        return false;
    }

    private boolean hitLeft() {
        if (this.getX() <= 0)
            return true;
        return false;
    }

    public void shoot(Boss boss) {
        if (coolDown) {
            Media media = new Media(new File("src/main/resources/music/shoot.mp3").toURI().toString());
            AudioClip audioClip = new AudioClip(media.getSource());
            audioClip.play();
            coolDown = false;
            Bullet bullet = new Bullet((int) (this.getX() + this.getWidth()), (int) (this.getY() + (this.getHeight() / 2)), pane);
            pane.getChildren().add(bullet);
            bullet.shoot();
        }
    }

    public void getHit() {
        this.hearts -=  this.hitPercentage / 100;
        this.updateHeartText();

        if (this.getHearts() <= 0) {
            this.pane.getChildren().remove(Airplane.getInstance());
            Airplane.removeInstance();
            Boss.removeInstance();
            EndMenuController.message = "You lose";
            EndMenuController.score = GameMenuController.score;
            int highScore = UserDatabaseController.getInstance().getScore(GameMenuController.username);
            if (highScore < GameMenuController.score) {
                UserDatabaseController.getInstance().setScore(GameMenuController.username, GameMenuController.score);
            }
            EndMenuController.createPane();
        }

    }

    public void setBackGround(String URL) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(URL).toExternalForm())));
    }

    public float getHearts() {
        return hearts;
    }

    public float getDamagePercentage() {
        return damagePercentage;
    }

    public float getHitPercentage() {
        return hitPercentage;
    }

    public void updateHeartText() {
        this.heartText.setText("x" + hearts);
    }
}
