package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import transition.BulletAnimation;

public class Egg extends Rectangle {

    public Egg(int x, int y) {
        super(x, y, 35, 11);//TODO
        this.setBackGround("/view/frames/bullet.png");//TODO
    }

    public void shoot() {

    }

    public void setBackGround(String URL) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(URL).toExternalForm())));
    }
}
