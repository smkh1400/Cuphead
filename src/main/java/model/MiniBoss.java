package model;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import transition.MiniBossAnimation;

import java.util.ArrayList;

public class MiniBoss extends Rectangle {

    private static ArrayList<MiniBoss> miniBosses = new ArrayList<>();

    private float health;
    private Pane pane;

    public MiniBoss(int x, int y, float health, Pane pane) {
        super(x, y, 159 , 109);
        this.health = health;
        this.pane = pane;
        miniBosses.add(this);
    }

    public static ArrayList<MiniBoss> getMiniBosses() {
        return miniBosses;
    }

    public void moveLeft() {
        this.setX(this.getX() - 8);
    }

    public void setBackGround(String URL) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(URL).toExternalForm())));
    }

    public float getHealth() {
        return health;
    }

    public void getHit(float damagePercentage) {
        this.health -= damagePercentage / 100;
    }

    public Pane getPane() {
        return pane;
    }

    public boolean hasCollision(Airplane airplane) {
        return airplane.getBoundsInParent().intersects(this.getLayoutBounds());
    }

}
