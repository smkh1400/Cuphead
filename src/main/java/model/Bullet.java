package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import transition.BulletAnimation;

import java.util.ArrayList;

public class Bullet extends Rectangle {

    //private static ArrayList<Bullet> bullets = new ArrayList<>();

    public Bullet(int x, int y) {
        super(x, y, 35, 11);
        this.setBackGround("/view/frames/bullet.png");
    }

    public void shoot() {
        BulletAnimation bulletAnimation = new BulletAnimation(this);
        bulletAnimation.play();
    }

    public void setBackGround(String URL) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(URL).toExternalForm())));
    }
}