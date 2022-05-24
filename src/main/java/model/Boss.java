package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import transition.BossAnimation;
import transition.BossShootAnimation;


public class Boss extends Rectangle {

    private BossAnimation bossAnimation;
    private BossShootAnimation bossShootAnimation;
    private int shootCoolDown;
    private Rectangle healthBar;
    private Text healthText;

    private float health;
    private Pane pane;

    private static Boss instance;

    private Boss(float health, Pane pane, Rectangle healthBar, Text healthText) {
        super(750, 250, 651 / 2, 509 / 2);
        this.health = health;
        this.pane = pane;
        this.shootCoolDown = 0;
        this.healthBar = healthBar;
        this.healthText = healthText;
    }

    public static Boss getInstance() {
        return instance;
    }

    public static void updateInstance(float health, Pane pane, Rectangle healthBar, Text healthText) {
        instance = new Boss(health, pane, healthBar, healthText);
    }

    public static void removeInstance() {
        instance = null;
    }

    public boolean moveUp() {
        if (!this.hitUp()) {
            this.setY(this.getY() - 2);
            return true;
        }
        return false;
    }

    public boolean moveDown() {
        if (!this.hitDown()) {
            this.setY(this.getY() + 2);
            return true;
        }
        return false;
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

    public void shootEgg(int x, int y) {
        Egg egg = new Egg(x,y);// TODO find place of the start of shooting
        pane.getChildren().add(egg);
        egg.shoot();
    }

    public void setBackGround(String URL) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(URL).toExternalForm())));
    }

    public float getHealth() {
        return health;
    }

    public void getHit() {
        this.health -= (Airplane.getInstance().getDamagePercentage()) / 100;
    }

    public Pane getPane() {
        return pane;
    }

    public void setBossAnimation(BossAnimation bossAnimation) {
        this.bossAnimation = bossAnimation;
    }

    public BossAnimation getBossAnimation() {
        return bossAnimation;
    }

    public BossShootAnimation getBossShootAnimation() {
        return bossShootAnimation;
    }

    public void setBossShootAnimation(BossShootAnimation bossShootAnimation) {
        this.bossShootAnimation = bossShootAnimation;
    }

    public boolean hasCollision(Rectangle airplane) {
        return airplane.getBoundsInParent().intersects(this.getLayoutBounds());
    }

    public int getShootCoolDown() {
        return shootCoolDown;
    }

    public void setShootCoolDown(int shootCoolDown) {
        this.shootCoolDown = shootCoolDown;
    }

    public void updateHealthBarLength() {
        this.healthBar.setWidth(this.health * 8);
        this.healthText.setText(String.valueOf(this.health));
    }
}
