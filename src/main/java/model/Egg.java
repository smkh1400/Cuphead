package model;

import controller.GameMenuController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import transition.BulletAnimation;
import transition.EggAnimation;

public class Egg extends Rectangle {

    private boolean done;

    public Egg(int x, int y) {
        super(x, y, 136 / 2 , 116 / 2);
        this.setBackGround("/view/frames/egg.png");
        this.done = false;
    }

    public void shoot() {
        EggAnimation eggAnimation = new EggAnimation(this);
        eggAnimation.play();
    }

    public boolean hasCollision(Airplane airplane) {
        return airplane.getBoundsInParent().intersects(this.getLayoutBounds());
    }

    public void setBackGround(String URL) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(URL).toExternalForm())));
    }

    public boolean isDone() {
        return done;
    }

    public void done() {
        done = true;
    }
}
