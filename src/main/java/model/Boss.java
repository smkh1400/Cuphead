package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Boss extends Rectangle {

    private float health;
    private Pane pane;

    private static Boss instance;

    private Boss(float health, Pane pane) {
        super(750, 250, 651 / 2, 509 / 2);
        this.health = health;
        this.pane = pane;
    }

    public static Boss getInstance() {
        return instance;
    }

    public static void updateInstance(float health, Pane pane) {
        instance = new Boss(health, pane);
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

    public void shootEgg() {
        Egg egg = new Egg(1,2);// TODO find place of the start of shooting
        pane.getChildren().add(egg);
        egg.shoot();
    }

    public void setBackGround(String URL) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(URL).toExternalForm())));
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void getHit(int damagePercentage) {
        this.health -= 1 * (damagePercentage / 100);
    }

    public Pane getPane() {
        return pane;
    }
}
