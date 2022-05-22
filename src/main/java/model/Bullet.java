package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import transition.BulletAnimation;

import java.util.ArrayList;

public class Bullet extends Rectangle {

    private Pane pane;
    private boolean done;

    public Bullet(int x, int y, Pane pane) {
        super(x, y, 35, 11);
        this.pane = pane;
        this.done = false;
        this.setBackGround("/view/frames/bullet.png");
    }

    public void shoot(Boss boss, float damagePercentage) {
        BulletAnimation bulletAnimation = new BulletAnimation(this, damagePercentage);
        bulletAnimation.play();
    }

    public boolean hasCollision(Rectangle boss) {
        return boss.getBoundsInParent().intersects(this.getLayoutBounds());
    }

    public void setBackGround(String URL) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(URL).toExternalForm())));
    }

    public Pane getPane() {
        return pane;
    }

    public boolean isDone() {
        return done;
    }

    public void done() {
        this.done = true;
    }
}
